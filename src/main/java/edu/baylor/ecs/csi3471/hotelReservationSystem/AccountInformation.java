package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AccountInformation)) return false;
		AccountInformation that = (AccountInformation) o;
		return Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
}