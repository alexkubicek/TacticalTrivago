package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import static edu.baylor.ecs.csi3471.hotelReservationSystem.backend.DateHelper.*;



public class Room {
  private Integer roomNumber, bedCount;
  private Boolean smoking;
  
  //associations
  private List<Date> unavailable = null; //to track booked reservations
  protected QualityLevel quality;
  private BedType bedSizes;
  public CleanStatus clean_status;
  
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
    // headers: roomNumber,numberOfBeds,smoking,qualityLevel,bedType,currentCleanStatus,datesBooked
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
    switch(line[5]){
      case "CLEAN": clean_status = CleanStatus.CLEAN; break;
      case "DIRTY": clean_status = CleanStatus.DIRTY; break;
      case "OCCUPIED": clean_status = CleanStatus.OCCUPIED; break;
    }

    // room is not booked at all
    if(unavailable == null){
      unavailable = new ArrayList<>();
    }
    // get any dates this room is booked for
    for(int i = 6; i < line.length; i++) {
      SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
      Date date;
      try {
        date = formatter.parse(line[i]);
      } catch (ParseException e) {
        System.err.println("Error in Room(String[]): invalid date format from csv");
        throw new RuntimeException(e);
      }
      unavailable.add(date);
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
    return s;
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
    List<Date> daysToBook = getDaysInBetween(start, end);
    // room has no reservations yet
    if(unavailable == null){
      return true;
    }
    // loop through unavailable dates
    for(Date date : unavailable) {
      // if we want to book any unavailable date
      if(daysToBook.contains(date)){
        return false;
      }
    }
    return true;
  }

  public void bookRoom(Date start, Date end) {
    if(unavailable == null){
      unavailable = new ArrayList<>();
    }
    List<Date> daysToBook = getDaysInBetween(start, end);
    for(Date d : daysToBook){
      if(unavailable.contains(d)){
        System.err.println("Error in Room bookRoom(): " +
                "trying to book room when it's unavailable");
        return;
      }
      unavailable.add(d);
    }
  }
  public Room(){}

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public void setBedCount(Integer bedCount) {
    this.bedCount = bedCount;
  }

  public void setSmoking(Boolean smoking) {
    this.smoking = smoking;
  }

  public void setUnavailable(List<Date> unavailable) {
    this.unavailable = unavailable;
  }
  public List<Date> getUnavailable(){ return this.unavailable; }

  public void setQuality(QualityLevel quality) {
    this.quality = quality;
  }

  public void setBedSizes(BedType bedSizes) {
    this.bedSizes = bedSizes;
  }

  public void setClean_status(CleanStatus clean_status) {
    this.clean_status = clean_status;
  }
}
