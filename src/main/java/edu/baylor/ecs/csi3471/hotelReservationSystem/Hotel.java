package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JTextArea;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "Hotel")
public class Hotel {
  // associations
  @XmlElementWrapper(name = "Rooms")
  @XmlElement(name = "Room")
  public static List<Room> rooms;
  public static List<Reservation> reservations;
  private static List<Payment> pastPayments;


  public void setUsers(List<Guest> guests) {this.accounts = accounts;}

  
  public static List<User> accounts;

  public List<Room> getRooms() {return rooms;}
  public void setRooms(List<Room> rooms) {this.rooms = rooms;}
  public List<Reservation> getReservations() {return reservations;}
  public void setReservations(List<Reservation> reservations) {this.reservations = reservations;}
  public List<Payment> getPastPayments() {return pastPayments;}
  public void setPastPayments(List<Payment> pastPayments) {this.pastPayments = pastPayments;}
  public List<User> getAccounts() {return accounts;}
  public void setAccounts(List<User> accounts) {this.accounts = accounts;}

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


  public static void reserveRooms(List<Integer> roomNumbers, Date start, Date end, Guest g) {
	  List<Room> selectedRooms = new ArrayList<>();
	  
	  for(int NumberRoom : roomNumbers) {
		  Room selectedRoom = getRoom(NumberRoom);
		  if(selectedRoom != null && selectedRoom.isAvailable(start, end)) {
			  selectedRooms.add(selectedRoom);
		  }
	  }
	  
	  if(!selectedRooms.isEmpty()) {
		  Reservation reservation;
		  if(selectedRooms.size() == 1) {
			  reservation = new Reservation(start, end, g, selectedRooms.get(0));
		  } 
		  else {
		      // reserve multiple rooms
		      reservation = new Reservation(start, end, g, selectedRooms);
		  }
		  
		  if (reservations == null) {
		      reservations = new ArrayList<Reservation>();
		  }
		  reservations.add(reservation);
		  g.addUpcomingReservations(reservation);
		  System.out.println("Reservation created successfully.");
		  
	  }
	  else {
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

//  public static void printRecords() {
//    // print past reservations and payments sorted by date
//    List<Reservation> sortedReservations = new ArrayList<>(reservations);
//    List<Payment> sortedPayments = new ArrayList<>(pastPayments);
//
//    // Sort reservations and payments by date
//    Comparator<Reservation> reservationComparator = Comparator.comparing(Reservation::getStartDate);
//
//    Comparator<Payment> paymentComparator = Comparator.comparing(Payment::getDate);
//
//    sortedReservations.sort(reservationComparator);
//    sortedPayments.sort(paymentComparator);
//
//    // Print the sorted reservations
//    System.out.println("Reservations:");
//    for (Reservation reservation : sortedReservations) {
//      System.out.println(reservation.toString());
//    }
//
//    // Print the sorted payments
//    System.out.println("\nPayments:");
//    for (Payment payment : sortedPayments) {
//      System.out.println(payment.toString());
//    }
//  }
  
  public static void printRecords(JTextArea textArea) {
	    // print past reservations and payments sorted by date
	    List<Reservation> sortedReservations = new ArrayList<>(reservations);
	    List<Payment> sortedPayments = new ArrayList<>(pastPayments);

	    // Sort reservations and payments by date
	    Comparator<Reservation> reservationComparator = Comparator.comparing(Reservation::getStartDate);
	    Comparator<Payment> paymentComparator = Comparator.comparing(Payment::getDate);
	    sortedReservations.sort(reservationComparator);
	    sortedPayments.sort(paymentComparator);

	    // Display the sorted reservations
	    textArea.append("Reservations:\n");
	    for (Reservation reservation : sortedReservations) {
	        textArea.append(reservation.toString() + "\n");
	    }

	    // Display the sorted payments
	    textArea.append("\nPayments:\n");
	    for (Payment payment : sortedPayments) {
	        textArea.append(payment.toString() + "\n");
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

  public static User login(final String user, final String pass) {
      User toReturn = null;
    try {
      for(User u: accounts){
        AccountInformation cur_account = u.getAccountInformation();
        if(Objects.equals(cur_account.getUsername(), user)) {
          if(Objects.equals(cur_account.getPassword(), pass)) {
            toReturn = u;
          } else {
            new LoginFailurePopupGUI();
          }
          throw new RuntimeException(); //to break out of loop
        }
      }
    } catch(Exception ignored) {} //catch but ignore to break
    return toReturn;
  }

  public static void main(String[] args) {
      accounts = new ArrayList<>();
      accounts.add(new Guest("", "", new AccountInformation("user", "pass")));
      LoginPageGUI lp = new LoginPageGUI();
  }
}
