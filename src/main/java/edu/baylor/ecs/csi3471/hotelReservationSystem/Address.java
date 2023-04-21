package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Map;

public class Address{
  	private Integer buildingNumber, zipCode;
  	private String street, city; //abbreviation mapped to full name
	private State state;
  
  	public Integer getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Address(Integer buildingNumber, Integer zipCode, String street, String city, State tx) {
		super();
		this.buildingNumber = buildingNumber;
		this.zipCode = zipCode;
		this.street = street;
		this.city = city;
		this.state = tx;
	}
	
	public Address() {}
}
