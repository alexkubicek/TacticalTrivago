package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.List;

public class Guest extends User {
    public Guest(String nameFirst, String nameLast, AccountInformation info) {
		super(nameFirst, nameLast, info);
	}
    private Boolean isCorporate;
  
    //associations
    private List<Reservation> upcomingReservations;
    private List<Address> addresses; //index 0 being default to use
    private List<CreditCard> paymentMethods; //index 0 being default to use

    public void setCorporate(Boolean corporate) {isCorporate = corporate;}

    public void setUpcomingReservations(List<Reservation> upcomingReservations) {this.upcomingReservations = upcomingReservations;}

    public void setAddresses(List<Address> addresses) {this.addresses = addresses;}

    public void setPaymentMethods(List<CreditCard> paymentMethods) {this.paymentMethods = paymentMethods;}

    public Boolean corporate() {return isCorporate;}

    public List<Reservation> getUpcomingReservations() {return upcomingReservations;}

    public List<Address> getAddresses() {return addresses;}

    public List<CreditCard> getPaymentMethods() {return paymentMethods;}
}
