/*
 * A class representing a hotel reservation in the hotel reservation system.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

	public void applyExtendedStayDiscount() {
		int stayLength = getNights();
		double baseRate = getRate();
		double discountRate = 1.0;

		// Apply a 10% discount for stays of 5 nights or more, and a 20% discount for stays of 7 nights or more
		if (stayLength >= 5 && stayLength < 7) {
			discountRate = 0.9;
		} else if (stayLength >= 7) {
			discountRate = 0.8;
		}
		setRate(baseRate * discountRate);
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

	public boolean checkIn(double amount, List<Reservation> reservations) {
		double total = 0.0;
	    for (Reservation reservation : reservations) {
	        total += reservation.calculateTotal();
	    }
	    if (amount - total >= 0) {
	        amount -= total;
	        // Do the check-in process here
	        rooms.forEach(r->{
				r.clean_status = CleanStatus.OCCUPIED;
			});
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public void checkIn() {
		rooms.forEach(r->{
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
	//Editing Dates only
	public void updateReservation(Date startDate, Date endDate) {
		// Compute the number of nights for the new reservation dates
		int nights = (int) ((endDate.getTime() - startDate.getTime()) / (1000*60*60*24));

		// Compute the new rate based on the selected room's rate and the new number of nights
		// Set the updated reservation fields
		setStartDate(startDate);
		setEndDate(endDate);
		setNights(nights);
	}
	//Editing Dates and Rooms
	public void updateReservation(Date startDate, Date endDate, Room selectedRoom) {
		// Compute the number of nights for the new reservation dates
		int nights = (int) ((endDate.getTime() - startDate.getTime()) / (1000*60*60*24));

		// Compute the new rate based on the selected room's rate and the new number of nights
		double rate = selectedRoom.getQuality().getRate() * nights;

		// Set the updated reservation fields
		setStartDate(startDate);
		setEndDate(endDate);
		setNights(nights);
		setRate(rate);
		setRooms(Collections.singletonList(selectedRoom));
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
