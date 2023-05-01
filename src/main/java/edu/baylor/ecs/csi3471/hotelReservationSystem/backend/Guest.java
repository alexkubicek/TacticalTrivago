/*
 * A class representing a guest.
 * Extends the User class.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.util.ArrayList;
import java.util.List;

import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.GuestOptionsGUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.UserProfileGUI;

import javax.naming.spi.ResolveResult;

public class Guest extends User {

    private Boolean isCorporate = false;
    //associations
    private List<Reservation> upcomingReservations;

    //updated the credit card list to be a single card payment method for each guest
    private CreditCard paymentMethod = null;

    public Guest(String nameFirst, String nameLast, AccountInformation info) {
        super(nameFirst, nameLast, info);
        isCorporate = false;
        upcomingReservations = new ArrayList<>();
    }

    public Guest(String[] line){
        // header labels assumed: nameFirst, nameLast, username, password
        super(line[0], line[1], new AccountInformation(line[2], line[3]));
        isCorporate = Boolean.parseBoolean(line[4]);
        upcomingReservations = new ArrayList<Reservation>();
        if(line.length == 10){
            paymentMethod = new CreditCard(line);
        }
    }
    public Guest(){
        super();
        isCorporate = false;
        upcomingReservations = new ArrayList<Reservation>();
    }

    public void setCorporate(Boolean corporate) {isCorporate = corporate;}

    public void setUpcomingReservations(List<Reservation> upcomingReservations) {this.upcomingReservations = upcomingReservations;}

    public void addUpcomingReservations(Reservation reservation) {
    	System.out.println(reservation.getRoomsString());
        if(this.upcomingReservations == null) {
            this.upcomingReservations = new ArrayList<Reservation>();
        }
        this.upcomingReservations.add(reservation);
    }

    //takes in a card to set current guest payment to that card
    public void setPaymentInfo(CreditCard card){
        if(paymentMethod == null){
            paymentMethod = new CreditCard();
        }
        this.paymentMethod.setCreditCard(card);
    }
    public void setPaymentMethods(CreditCard paymentMethod) {this.paymentMethod = paymentMethod;}

    public Boolean corporate() {return isCorporate;}

    public List<Reservation> getUpcomingReservations() {return upcomingReservations;}

    public CreditCard getPaymentMethod() {return paymentMethod;}

    @Override
    public void launchOptions() {
        System.out.println("launching guest options");
        GuestOptionsGUI myOptions = new GuestOptionsGUI(this);
    }

    @Override
    public void launchProfile() {
        new UserProfileGUI(this);
    }

    public void login(){
        this.gui = new GuestOptionsGUI(this);
    }

    @Override
    public void updateFromProfileGUI(UserProfileGUI myGUI) {
        myGUI.updateUser(this);
    }

    public String toStringForUI(){
        return getFullName() + " (" + getAccountUsername()+ ")";
    }
}