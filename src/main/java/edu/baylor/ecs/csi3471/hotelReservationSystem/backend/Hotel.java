/*
 * A class representing a Hotel. This class is the information expert as it's associated
 * with various other domain objects.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.JTextArea;

import aesthetics.fonts.fonts;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.LoginFailurePopupGUI;

public class Hotel {

    // associations
  public static List<Room> rooms = new ArrayList<>();
  public static List<Reservation> reservations = new ArrayList<>();
  private static List<Payment> pastPayments = new ArrayList<>();
  public static List<User> accounts = new ArrayList<>();

  public void setUsers(List<Guest> guests) {this.accounts = accounts;}

  public static List<Room> getRooms() {return rooms;}
  public void setRooms(List<Room> rooms) {this.rooms = rooms;}
  public static List<Reservation> getReservations() {return reservations;}
  public static void setReservations(List<Reservation> r) { reservations = r;}
  public List<Payment> getPastPayments() {return pastPayments;}
  public void setPastPayments(List<Payment> pastPayments) {this.pastPayments = pastPayments;}
  public static List<User> getAccounts() {return accounts;}
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

  public static void reserveRoom(Room r, Date start, Date end, Guest g){
      // create reservation
      System.out.println(r);
      Reservation reservation = new Reservation(start, end, g, r);
      System.out.println(reservation.getRoomsString());
      // add to hotel's reservation list
      if (reservations == null) {
          reservations = new ArrayList<>();
      }
      reservations.add(reservation);
      // update room's unavailable dates
      r.bookRoom(start, end);
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
		      reservations = new ArrayList<>();
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
  
  public static List<Reservation> getReservationsByGuestName(String name) {
	  System.out.println(name);
	    // Find and return reservations with the matching guest name
	    List<Reservation> guestReservations = new ArrayList<>();
	    for (Reservation reservation : reservations) {
	        if (reservation.getGuest().getAccountUsername().equalsIgnoreCase(name)) {
	            guestReservations.add(reservation);
	        }
	    }
	    return guestReservations;
	}

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
//  public static void applyExtendedStayDiscount(Reservation reservation) {
//    Integer stayLength = reservation.getNights();
//    Double baseRate = reservation.getRate();
//    Double discountRate = 1.0;
//
//    // Apply a 10% discount for stays of 5 nights or more, and a 20% discount for stays of 7 nights or more
//    if (stayLength >= 5 && stayLength < 7) {
//      discountRate = 0.9;
//    } else if (stayLength >= 7) {
//      discountRate = 0.8;
//    }
//    reservation.setRate(baseRate * discountRate);
//  }

  public static void addRoom(Room room) {
    rooms.add(room);
  }

  public static void addAccount(User account) {
    Hotel.accounts.add(account);
  }

  public static boolean isUsernameUnique(String username) {
    try {
      accounts.forEach(u->{
        if(Objects.equals(u.getAccountInformation().getUsername(), username)) {
          throw new RuntimeException();
        }
      });
    } catch(Exception e) {
      return false;
    }
    return true;

  }

  public static User login(final String user, final String pass) {
      if(user == null || pass == null){
          new LoginFailurePopupGUI();
      }

      for(User u: accounts){
          System.out.println(u);
          AccountInformation cur_account = u.getAccountInformation();
          if(Objects.equals(cur_account.getUsername(), user)) {
              if(Objects.equals(cur_account.getPassword(), pass)) {
                  return u;
          } else {
              new LoginFailurePopupGUI();
              return null;
          }
        }
      }
      return null;
  }

    public static List<Guest> getGuestAccounts(){
        List<Guest> list = new ArrayList<>();
        for(User u : accounts){
            if(u.getClass() == Guest.class){
                list.add((Guest)u);
            }
        }
        return list;
    }
    public static List<Clerk> getClerkAccounts(){
        List<Clerk> list = new ArrayList<>();
        for(User u : accounts){
            if(u.getClass() == Clerk.class){
                list.add((Clerk)u);
            }
        }
        return list;
    }
    public static List<Admin> getAdminAccounts(){
        List<Admin> list = new ArrayList<>();
        for(User u : accounts){
            if(u.getClass() == Admin.class){
                list.add((Admin)u);
            }
        }
        return list;
    }

    public static Vector<String> getGuests(){
      Vector<String> myGuests = new Vector<>();
      accounts.stream().filter(u->u.getClass() == Guest.class).forEach(user->{
          System.out.println(user.getAccountUsername());
          myGuests.add(user.getAccountUsername());
      });
      return myGuests;
    }
    public static List<Clerk> getClerks(){
        List<Clerk> myClerks = new ArrayList<>();
        accounts.stream().filter(u->u.getClass() == Clerk.class).forEach(user->{
            myClerks.add((Clerk)user);
        });
        return myClerks;
    }

    public static User searchForAccountByUsername(String username){
        for(User u : accounts){
            if(u.getAccountUsername().equalsIgnoreCase(username)){
                return u;
            }
        }
        return null;
    }
    
    public static void deleteClerk(String username) {
    	accounts.removeIf(obj -> {
            if (obj instanceof Clerk) {
                User user = (Clerk) obj;
                return user.getAccountUsername().equals(username);
            }
            return false;
        });
    }
    
}
