package edu.baylor.ecs.csi3471.hotelReservationSystem;

public class Clerk extends User {

	public Clerk(String nameFirst, String nameLast, AccountInformation account){
		super(nameFirst, nameLast, account);
	}

	public String getPublicInfo(){
		return super.getFullName();
	}
}
