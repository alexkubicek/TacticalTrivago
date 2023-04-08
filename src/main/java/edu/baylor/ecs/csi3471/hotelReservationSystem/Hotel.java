package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Hotel {
  // associations
  private List<Room> rooms;
  private List<Reservation> reservations;
  private List<Payment> pastPayments;

  //TODO Decide if we are going to make everything extend user or have clerk, guest, and admin be separate
  //depending on choice DELETE the list of guests below
  //Alex thinks keep it as Users because we can check which it is with the .class function
  private List<Guest> guests;
  private List<User> accounts;

  public List<Guest> getGuests() {return guests;}
  public void setGuests(List<Guest> guests) {this.guests = guests;}

  public List<Room> getRooms() {return rooms;}
  public void setRooms(List<Room> rooms) {this.rooms = rooms;}
  public List<Reservation> getReservations() {return reservations;}
  public void setReservations(List<Reservation> reservations) {this.reservations = reservations;}
  public List<Payment> getPastPayments() {return pastPayments;}
  public void setPastPayments(List<Payment> pastPayments) {this.pastPayments = pastPayments;}
  public List<User> getAccounts() {return accounts;}
  public void setAccounts(List<User> accounts) {this.accounts = accounts;}
  
  public void addAccounts(List<? extends User> accounts) {
	  if(this.accounts == null) {
		  this.accounts = new ArrayList<User>();
	  }
      this.accounts.addAll(accounts);
  }

  public void displayAllRooms() {
    for (Room r : rooms) {
      System.out.println(r.toString());
    }
  }
  // operation contracts
  void displayAvailableRooms(Date start, Date end) {
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

  public void reserveRoom(Room r, Date start, Date end, Guest g, Hotel h) {
    // create reservation
    Reservation reservation = new Reservation(start, end, g, r, h);
    if(reservations == null) {
      reservations = new ArrayList<Reservation>();
    }
    reservations.add(reservation);
    g.addUpcomingReservations(reservation);
  }
  void reserveRooms(List<Integer> roomNumbers, Date start, Date end, Guest g, Hotel h) {

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
      Reservation reservation = new Reservation(start, end, g, selectedRooms, h);
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

  public Reservation getReservation(String name, Date d) {
    // find and return reservation with matching info
    for (Reservation reservation : reservations) {
      if (reservation.getGuest().getFullName().equals(name) && reservation.containsDate(d)) {
        return reservation;
      }
    }
    return null;
  }

  public void printRecords() {
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

  public Room getRoom(int roomNumber) {
    Room found = null;
    for (Room r : rooms) {
      if (r.getRoomNumber() == roomNumber) {
        found = r;
      }
    }
    return found;
  }
  public void applyExtendedStayDiscount(Reservation reservation) {
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
  
  public void addRoom(Room room) {
      rooms.add(room);
  }

  public void addAccount(User account) {
    this.accounts.add(account);
  }
}
