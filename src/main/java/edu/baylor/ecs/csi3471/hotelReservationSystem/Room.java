package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Room {
  private Integer roomNumber, bedCount;
  private Boolean smoking;
  
  //associations
  private List<Date> unavailable; //to track booked reservations
  QualityLevel quality;
  private BedType bedSizes;
  CleanStatus clean_status;

  public Integer getroomNumber(){
    return roomNumber;
  }

  public String getBedSize(){
    return bedSizes.toString();
  }

  public String getStatus(){
    return clean_status.toString();
  }
  public boolean isAvailable(Date start, Date end) {
    // Check if the room is available for the given dates
    for (Date date : unavailable) {
      if (date.compareTo(start) >= 0 && date.compareTo(end) <= 0) {
        return false;
      }
    }
    return true;
  }

  public void bookRoom(Date start, Date end) {
    // Mark the room as unavailable for the given dates
    Calendar c = new GregorianCalendar();
    c.setTime(start);
    while(!c.after(end)){
      unavailable.add(c.getTime());
      c.add(Calendar.DATE, 1);
    }
  }
}
