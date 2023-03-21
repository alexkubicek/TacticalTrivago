import java.util.Date;
import java.util.List;
Public class ReservationHandler{
    private List<Reservation> reservationList;

    public ReservationHandler(){
    };

    public ReservationHandler(Date startDate, Date endDate, Guest guest, List<Room> rooms, Hotel h) {
        reservation = new Reservation(startDate, endDate, guest, rooms, h);
    }

    public void modifyTime(Date start, Date end) {
        reservation.modifyTime(start, end);
    }

    public void checkIn(Reservation reservation) {
        Reservation r = null;
        for (Reservation res : reservationsList) {
            if (res.getId() == r.getId()) {
                r = res;
                break;
            }
        }
        r.checkIn();
    }

    public void checkOut(Reservation reservation) {
        Reservation r = null;
        for (Reservation res : reservationsList) {
            if (res.getId() == r.getId()) {
                r = res;
                break;
            }
        }
        r.checkOut();
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
