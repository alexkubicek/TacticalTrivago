public class Room {
  Integer roomNumber, bedCount;
  Boolean smoking;
  
  //associations
  List<Date> unavailable; //to track booked reservations
  QualityLevel quality;
  BedType bedSizes;
  CleanStatus clean_status;
}
