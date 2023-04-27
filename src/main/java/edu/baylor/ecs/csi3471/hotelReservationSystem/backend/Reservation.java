package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
  	private List<Room> rooms = null;
	public String getRoomsString(){
		StringBuilder line = new StringBuilder();
		for (Room r : rooms) {
			line.append(String.valueOf(r.getRoomNumber())).append(": ").append(String.valueOf(r.getBedCount())).append(" ").append(String.valueOf(r.getBedSize())).append("s\n");
		}
		return String.valueOf(line);
	}
	public void setGuest(String username){
		for(User u: Hotel.accounts){
			if(Objects.equals(u.getAccountUsername(), username)){
				this.guest = (Guest)u;
			}
		}
	}
  	//constructors
	public Reservation() {}
	public Reservation(Date s, Date e, Guest g, List<Room> r) {
		startDate = s;
		endDate = e;
		rooms = r;
		guest = g;
		
		nights = (int)((endDate.getTime() - startDate.getTime()) / (1000*60*60*24));
		rate = 0.0;
		rooms.forEach(t->{rate += t.quality.getRate();});
	}
	public Reservation(String[] line){
		if(rooms == null){
			rooms = new ArrayList<>();
		}
		rooms.add(Hotel.getRoom(Integer.parseInt(line[0])));
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		try {
			startDate = formatter.parse(line[1]);
			endDate = formatter.parse(line[2]);
		} catch (ParseException e) {
			System.err.println("Error in Reservation(String[]): invalid date format from csv");
			throw new RuntimeException(e);
		}
		try{
			guest = (Guest) Hotel.searchForAccountByUsername(line[3]);
			if(guest == null){
				System.err.println("Error in Reservation(String[]): guest associated with reservation does not exist");
				throw new RuntimeException();
			}
		} catch (ClassCastException e){
			System.err.println("Error in Reservation(String[]): reservation is under a clerk/admin");
			throw new RuntimeException();
		}
	}
	public Reservation(Date s, Date e, Guest g, Room r) {
		startDate = s;
		endDate = e;
		rooms = new ArrayList<>();
		rooms.add(r);
		guest = g;
		nights = (int)((endDate.getTime() - startDate.getTime()) / (1000*60*60*24));
		rate = 0.0;
		rooms.forEach(t->{rate += t.quality.getRate();});
	}
	public static Reservation createReservation(Date start, Date end, Guest g, ArrayList<Integer> roomNums) {
		List<Room> rooms = new ArrayList<>();
		Hotel.rooms.stream().filter((room)->roomNums.contains(room.getRoomNumber())).forEach(rooms::add);
		return new Reservation(start, end, g, rooms);
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
	
	public void checkOut() {
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
			Hotel.reservations.remove(this);
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
