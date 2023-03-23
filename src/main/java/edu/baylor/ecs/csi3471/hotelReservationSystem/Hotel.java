import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.naming.spi.ResolveResult;

public class Hotel {
  // associations
  List<Room> rooms;
  List<Reservation> reservations;
  List<Payment> pastPayments;
  List<User> accounts;

  // operation contracts
  void displayRooms(Date start, Date end) {
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
        System.out.println("Room number: " + room.getroomNumber() + " - " + room.getBedSize());
      }
    }

  }

  void reserveRoom(List<Integer> roomNumbers, Date start, Date end, Guest g, Hotel h) {

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
      reservations.add(reservation);
      System.out.println("Reservation created successfully.");
    } else {
      System.out.println("Failed to create reservation. The selected rooms are not available for the selected dates.");
    }

  }

  Reservation getReservation(String name, Date d) {
    // find and return reservation with matching info
    for (Reservation reservation : reservations) {
      if (reservation.getGuest().getFullName().equals(name) && reservation.containsDate(d)) {
        return reservation;
      }
    }

    return null;
  }

  void printRecords() {
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

  private Room getRoom(int roomNumber) {
    Room found = null;
    for (Room r : rooms) {
      if (r.getroomNumber() == roomNumber) {
        found = r;
      }
    }
    return found;
  }
}
