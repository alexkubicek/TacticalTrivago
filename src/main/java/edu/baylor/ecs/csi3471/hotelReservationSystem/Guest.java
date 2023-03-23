package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.List;

public class Guest extends User {
  public Guest(String nameFirst, String nameLast, AccountInformation info) {
		super(nameFirst, nameLast, info);
	}
Boolean isCorporate;
  
  //associations
  List<Reservation> upcomingReservations;
  List<Address> addresses; //index 0 being default to use
  List<CreditCard> paymentMethods; //index 0 being default to use
}
