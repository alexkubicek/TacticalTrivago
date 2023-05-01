/*
 * Represents an Address of a guest. 
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import static java.lang.Integer.parseInt;

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

	public Address(String line) {
		super();
		String[] addressLine;
		addressLine = line.split("\\.");
		this.buildingNumber = parseInt(addressLine[0]);
		this.zipCode = parseInt(addressLine[1]);
		this.street = addressLine[2];
		this.city = addressLine[3];
		this.state = State.valueOf(addressLine[4]);
	}
}
