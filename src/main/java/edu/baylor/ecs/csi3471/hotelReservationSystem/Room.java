package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.ArrayList;
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
  
  public Room(Integer roomNumber, Integer bedCount, Boolean smoking, QualityLevel quality, BedType bedSizes) {
	    this.roomNumber = roomNumber;
	    this.bedCount = bedCount;
	    this.smoking = smoking;
	    this.quality = quality;
	    this.bedSizes = bedSizes;
	    this.unavailable = new ArrayList<>();
	    this.clean_status = CleanStatus.CLEAN; // default clean status
	}

  public Room(String[] line){
    // header labels assumed: roomNumber,numberOfBeds,smoking,qualityLevel,bedType
    roomNumber = Integer.parseInt(line[0]);
    bedCount = Integer.parseInt(line[1]);
    smoking = Boolean.parseBoolean(line[2]);
    switch(line[3]){
      case "EXECUTIVE": quality = QualityLevel.EXECUTIVE; break;
      case "BUSINESS": quality = QualityLevel.BUSINESS; break;
      case "COMFORT": quality = QualityLevel.COMFORT; break;
      case "ECONOMY": quality = QualityLevel.ECONOMY; break;
    }
    switch(line[4]){
      case "TWIN": bedSizes = BedType.TWIN; break;
      case "FULL": bedSizes = BedType.FULL; break;
      case "QUEEN": bedSizes = BedType.QUEEN; break;
      case "KING": bedSizes = BedType.KING; break;
    }
  }
  @Override
  public String toString() {
    return "Room{" +
            "roomNumber=" + roomNumber +
            ", bedCount=" + bedCount +
            ", smoking=" + smoking +
            ", quality=" + quality +
            ", bedSizes=" + bedSizes +
            '}';
  }
  public String toStringForUI() {
    String s = "Room number " + roomNumber + "<br/>" +
            bedCount + " " + bedSizes + " size bed(s)<br/>" +
            quality + " level with a rate of $" +
            String.format("%.2f", quality.getRate()) + " per night<br/>";
    if(smoking){
      s = s + "Smoking IS allowed";
    } else{
      s = s + "Smoking is NOT allowed";
    }
    return "<html>" + s + "</html>";
  }
  public Integer getRoomNumber(){
    return roomNumber;
  }
  public Integer getBedCount() { return bedCount; }
  public Boolean getSmoking() { return smoking; }
  public QualityLevel getQuality() { return quality; }
  public String getBedSize(){
    return bedSizes.toString();
  }
  public String getStatus(){
    return clean_status.toString();
  }
  public boolean isAvailable(Date start, Date end) {
    // Check if the room is available for the given dates
	  if(unavailable != null) {
		  for (Date date : unavailable) {
		      if (date.compareTo(start) >= 0 && date.compareTo(end) <= 0) {
		        return false;
		      }
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
  public Room(){}
}
