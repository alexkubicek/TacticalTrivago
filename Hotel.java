public class Hotel {
  //associations
  List<Room> rooms;
  List<Reservation> upcomingReservations;
  List<Payment> pastPayments;
  List<Person> accounts;
  
  //operation contracts
  void displayRooms(Date start, Date end){
    //display all rooms available for given dates
  }
  void reserveRoom(List<Integer> roomNumbers, Date start, Date end, Guest g){
    //create reservation
  }
  Reservation getReservation(String name, Date d){
    //find and return reservation with matching info
  }
}
