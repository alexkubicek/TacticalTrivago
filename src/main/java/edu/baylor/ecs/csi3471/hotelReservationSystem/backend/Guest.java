package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.util.ArrayList;
import java.util.List;

import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.GuestOptionsGUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.UserProfileGUI;

public class Guest extends User {
    public Guest(String nameFirst, String nameLast, AccountInformation info) {
		    super(nameFirst, nameLast, info);
	  }
    private Boolean isCorporate;

    public Guest(String[] line){
        // header labels assumed: nameFirst, nameLast, username, password
    	super(line[0], line[1], new AccountInformation(line[2], line[3]));
    	isCorporate = Boolean.parseBoolean(line[4]);
    	
      }
  
    //associations
    private List<Reservation> upcomingReservations;
    private List<CreditCard> paymentMethods; //index 0 being default to use

    public void setCorporate(Boolean corporate) {isCorporate = corporate;}

    public void setUpcomingReservations(List<Reservation> upcomingReservations) {this.upcomingReservations = upcomingReservations;}
    
    public void addUpcomingReservations(Reservation reservation) {
    	if(this.upcomingReservations == null) {
    		this.upcomingReservations = new ArrayList<Reservation>();
    	}
    	this.upcomingReservations.add(reservation);
    }


    public void setPaymentMethods(List<CreditCard> paymentMethods) {this.paymentMethods = paymentMethods;}

    public Boolean corporate() {return isCorporate;}

    public List<Reservation> getUpcomingReservations() {return upcomingReservations;}

    public List<CreditCard> getPaymentMethods() {return paymentMethods;}

    public Guest(){}

    @Override
    public void launchOptions() {
        new GuestOptionsGUI(this);
    }

    @Override
	public void launchProfile() {
        new UserProfileGUI(this);
    }

    public void login(){
        this.gui = new GuestOptionsGUI(this);
    }
}
