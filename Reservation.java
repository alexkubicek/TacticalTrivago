public class Reservation {
  private Date startDate;
  private Date endDate;
  private Integer nights; //derived from start and end dates
  private Double rate;
  
  //associations
  private Guest guest;
  private List<Room> rooms;
}
