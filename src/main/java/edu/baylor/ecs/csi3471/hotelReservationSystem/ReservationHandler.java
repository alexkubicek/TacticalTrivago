package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ReservationHandler{
    private List<Reservation> reservationList;
    public ReservationHandler(){
        reservationList = new ArrayList<>();
        reservationList.add(new Reservation());
    }
    public ReservationHandler(Date startDate, Date endDate, Guest guest, List<Room> rooms, Hotel h) {
        reservationList = new ArrayList<>();
        reservationList.add(new Reservation(startDate, endDate, guest, rooms, h));
    }

    public void applyExtendedStayDiscount(Reservation reservation) {
        int stayLength = reservation.getNights();
        double baseRate = reservation.getRate();
        double discountRate = 1.0;

        // Apply a 10% discount for stays of 5 nights or more, and a 20% discount for stays of 7 nights or more
        if (stayLength >= 5 && stayLength < 7) {
            discountRate = 0.9;
        } else if (stayLength >= 7) {
            discountRate = 0.8;
        }
        reservation.setRate(baseRate * discountRate);
    }

}
