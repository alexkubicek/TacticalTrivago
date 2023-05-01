/*
 * Represents account information for a user.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.util.*;
import java.util.Objects;

public class AccountInformation{
	private String username, password;

	public AccountInformation(){}
	public AccountInformation(String username, String password){
		this.username = username;
		this.password = password;
	}

	//TODO Made these public for CSV output, please look into if you disagree
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String n) {
		this.username = n;
	}
	
	public void setPassword(String n) {
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