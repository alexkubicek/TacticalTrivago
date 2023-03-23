package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Date;
import java.util.List;

public class Room {
  Integer roomNumber, bedCount;
  Boolean smoking;
  
  //associations
  List<Date> unavailable; //to track booked reservations
  QualityLevel quality;
  BedType bedSizes;
  CleanStatus clean_status;

  int getroomNumber(){
    return roomNumber;
  }

  String getBedSize(){
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
    unavailable.add(start);
    unavailable.add(end);
  }
  
}
