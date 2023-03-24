package edu.baylor.ecs.csi3471.hotelReservationSystem;

public class AccountInformation{
	private String username, password;

	public AccountInformation(){}
	public AccountInformation(String username, String password){
		this.username = username;
		this.password = password;
	}

	String getUsername() {
		return username;
	}
	
	String getPassword() {
		return password;
	}
	
	void setUsername(String n) {
		this.username = n;
	}
	
	void setPassword(String n) {
		this.password = n;
	}
}