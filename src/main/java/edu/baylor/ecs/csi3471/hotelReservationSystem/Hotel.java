package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.*;

public class Hotel {
  // associations
  public static List<Room> rooms;
  public static List<Reservation> reservations;
  public static List<Payment> pastPayments;
  public static List<Guest> guests;
  public static List<User> accounts;

  public static void addAccounts(List<? extends User> accounts) {
    if(Hotel.accounts == null) {
      Hotel.accounts = new ArrayList<>();
    }
    Hotel.accounts.addAll(accounts);
  }

  public static void displayAllRooms() {
    for (Room r : rooms) {
      System.out.println(r.toString());
    }
  }
  // operation contracts
  public static void displayAvailableRooms(Date start, Date end) {
    // display all rooms available for given dates
    List<Room> availableRooms = new ArrayList<>();
    // Find all available rooms for the given dates
    for (Room room : rooms) {
      if (room.isAvailable(start, end)) {
        availableRooms.add(room);
      }
    }

    if (availableRooms.isEmpty()) {
      System.out.println("No rooms available for the selected dates.");
    } else {
      System.out.println("Available rooms for " + start + " to " + end + ":");
      for (Room room : availableRooms) {
        System.out.println("Room number: " + room.getRoomNumber() + " - " + room.getBedSize());
      }
    }

  }

  public static void reserveRoom(Room r, Date start, Date end, Guest g) {
    // create reservation
    Reservation reservation = new Reservation(start, end, g, r);
    if(reservations == null) {
      reservations = new ArrayList<Reservation>();
    }
    reservations.add(reservation);
    g.addUpcomingReservations(reservation);
  }
  public static void reserveRooms(List<Integer> roomNumbers, Date start, Date end, Guest g) {

    List<Room> selectedRooms = new ArrayList<>();

    // Find the rooms to be reserved
    for (int NumberRoom : roomNumbers) {
      Room selectedRoom = getRoom(NumberRoom);
      if (selectedRoom != null && selectedRoom.isAvailable(start, end)) {
        selectedRooms.add(selectedRoom);
      }
    }

    // create reservation
    if (!selectedRooms.isEmpty()) {
      Reservation reservation = new Reservation(start, end, g, selectedRooms);
      if(reservations == null) {
        reservations = new ArrayList<Reservation>();
      }
      reservations.add(reservation);
      g.addUpcomingReservations(reservation);
      System.out.println("Reservation created successfully.");
    } else {
      System.out.println("Failed to create reservation. The selected rooms are not available for the selected dates.");
    }

  }

  public static Reservation getReservation(String name, Date d) {
    // find and return reservation with matching info
    for (Reservation reservation : reservations) {
      if (reservation.getGuest().getFullName().equals(name) && reservation.containsDate(d)) {
        return reservation;
      }
    }
    return null;
  }

  public static void printRecords() {
    // print past reservations and payments sorted by date
    List<Reservation> sortedReservations = new ArrayList<>(reservations);
    List<Payment> sortedPayments = new ArrayList<>(pastPayments);

    // Sort reservations and payments by date
    Comparator<Reservation> reservationComparator = Comparator.comparing(Reservation::getStartDate);

    Comparator<Payment> paymentComparator = Comparator.comparing(Payment::getDate);

    sortedReservations.sort(reservationComparator);
    sortedPayments.sort(paymentComparator);

    // Print the sorted reservations
    System.out.println("Reservations:");
    for (Reservation reservation : sortedReservations) {
      System.out.println(reservation.toString());
    }

    // Print the sorted payments
    System.out.println("\nPayments:");
    for (Payment payment : sortedPayments) {
      System.out.println(payment.toString());
    }
  }

  public static Room getRoom(int roomNumber) {
    Room found = null;
    for (Room r : rooms) {
      if (r.getRoomNumber() == roomNumber) {
        found = r;
      }
    }
    return found;
  }
  public static void applyExtendedStayDiscount(Reservation reservation) {
    Integer stayLength = reservation.getNights();
    Double baseRate = reservation.getRate();
    Double discountRate = 1.0;

    // Apply a 10% discount for stays of 5 nights or more, and a 20% discount for stays of 7 nights or more
    if (stayLength >= 5 && stayLength < 7) {
      discountRate = 0.9;
    } else if (stayLength >= 7) {
      discountRate = 0.8;
    }
    reservation.setRate(baseRate * discountRate);
  }

  public static void addRoom(Room room) {
    rooms.add(room);
  }

  public static void addAccount(User account) {
    Hotel.accounts.add(account);
  }

  public static boolean isUsernameUnique(String username) {
    try {
      accounts.forEach(u->{
        if(u.getAccountInformation().getUsername() == username) {
          throw new RuntimeException();
        }
      });
    } catch(Exception e) {
      return false;
    }
    return true;

  }

  public static void login(final String user, final String pass) {
    try {
      accounts.forEach(u -> {
        AccountInformation cur_account = u.getAccountInformation();
        if(Objects.equals(cur_account.getUsername(), user)) {
          if(Objects.equals(cur_account.getPassword(), pass)) {
            if(u.getClass() == Guest.class) {
              GuestGUI gg = new GuestGUI(u.getNameFirst(), u);
              gg.setVisible(true);
            } else if(u.getClass() == Clerk.class) {
              ClerkGUI cg = new ClerkGUI(u.getNameFirst(), u);
              cg.setVisible(true);
            } else if(u.getClass() == Admin.class) {
              AdminGUI ag = new AdminGUI(u.getNameFirst(), u);
              ag.setVisible(true);
            }
          } else {
            new LoginFailurePopupGUI();
          }
          throw new RuntimeException(); //to break out of loop
        }
      });
    } catch(Exception ignored) {} //catch but ignore to break

  }

  public static void main(String[] args) {
    LoginPageGUI lp = new LoginPageGUI();
    lp.setVisible(true);
  }
}