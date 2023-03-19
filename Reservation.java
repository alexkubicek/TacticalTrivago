public class Reservation {
  private Date startDate;
  private Date endDate;
  private Integer nights; //derived from start and end dates
  private Double rate; //derived from individual room rates
  
  //associations
  private Guest guest;
  private List<Room> rooms;
  private Hotel tacticalTrigavo;
  
  //operation contracts
  void modifyTime(Date start, Date end) {}
  void modifyRoom(List<Room> newRooms) {}
  void checkIn() {
    //change status of associated rooms
  }
  void checkOut() {
    //change status of associated rooms
  }
  Double calculateTotal(){
    //return rate * nights
  }
  void cancel(){
    //verify date and delete reservation from hotel->reservations and guest->reservations
  }
}
