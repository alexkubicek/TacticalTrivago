import java.util.List;

public class Guest extends User {
  Boolean isCorporate;

  Boolean isExtendedStay;
  //associations
  List<Reservation> upcomingReservations;
  List<Address> addresses; //index 0 being default to use
  List<CreditCard> paymentMethods; //index 0 being default to use
}
