package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Reservation {
  	private Date startDate;
  	private Date endDate;
  	private Integer nights; //derived from start and end dates
  	private Double rate; //derived from individual room rates
  
  	//associations
  	private Guest guest;
  	private List<Room> rooms;
  	private Hotel tacticalTrigavo;
  
  	//constructors
	public Reservation() {}
	public Reservation(Date s, Date e, Guest g, List<Room> r, Hotel h) {
		startDate = s;
		endDate = e;
		rooms = r;
		tacticalTrigavo = h;
		guest = g;
		
		nights = (int)((endDate.getTime() - startDate.getTime()) / (1000*60*60*24));
		rate = 0.0;
		rooms.forEach(t->{rate += t.quality.getRate();});
	}
	
	
	//getters and setters
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getNights() {
		return nights;
	}
	public void setNights(Integer nights) {
		this.nights = nights;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public Hotel getTacticalTrivago() {
		return tacticalTrigavo;
	}
	public void setTacticalTrivago(Hotel tacticalTrivago) {
		this.tacticalTrigavo = tacticalTrivago;
	}
	
	//operation contracts
	public void modifyTime(Date start, Date end) {
		startDate = start;
		endDate = end;
	}

	public void checkIn() {
		rooms.forEach(r -> {
			r.clean_status = CleanStatus.OCCUPIED;
		});
	}
	
	void checkOut() {
		rooms.forEach(r->{
			r.clean_status = CleanStatus.DIRTY;
		});
	}
	
	public Double calculateTotal(){
		if(rate == null) {
			rate = 0.0;
			rooms.forEach(r->{rate += r.quality.getRate();});
		}
		if(nights == null){
			nights = (int)((endDate.getTime() - startDate.getTime()) / (1000*60*60*24));
		}
		return rate * nights;
	}
	
	public void cancel(){
		Date today = new Date();
		if(today.after(startDate)) {
			tacticalTrigavo.getReservations().remove(this);
		} else {
			System.err.println("Cannot cancel past reservation");
		}
	}
	public boolean containsDate(Date date) {
		return (date.compareTo(startDate) >= 0 && date.compareTo(endDate) < 0);
	  }


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reservation that = (Reservation) o;
		return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(guest, that.guest) && Objects.equals(rooms, that.rooms);
	}

	@Override
	public String toString() {
		return "Reservation [startDate=" + startDate + ", endDate=" + endDate + ", nights=" + nights + ", rate=" + rate
				+ ", guest=" + guest.getFullName() + ", rooms=" + rooms + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(startDate, endDate, guest, rooms);
	}
}
