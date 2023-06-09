/*
 * A class representing a clerk.
 * Extends the User class.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.AdminOptionsGUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.ClerkOptionsGUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.UserProfileGUI;

public class Clerk extends User {

	public Clerk(String nameFirst, String nameLast, AccountInformation account){
		super(nameFirst, nameLast, account);
	}

	public Clerk(String[] line){
		super(line[0], line[1], new AccountInformation(line[2], line[3]));
	}
	public Clerk(){super();}
	public String getPublicInfo(){
		return super.getFullName();
	}
	@Override
	public void launchOptions() {
		System.out.println("launching clerk options");
		new ClerkOptionsGUI(this);
	}
	@Override
	public String[] getTableInfo() {
		String[] myInfo = new String[]{getAccountInformation().getUsername(), getNameFirst(), getNameLast()};
		return(myInfo);
	}
	@Override
	public void launchProfile() {
		new UserProfileGUI(this);
	}
	public void login(){
		gui = new ClerkOptionsGUI(this);
	}

	@Override
	public void updateFromProfileGUI(UserProfileGUI myGUI) {
		myGUI.updateUser(this);
	}
}
