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



}
