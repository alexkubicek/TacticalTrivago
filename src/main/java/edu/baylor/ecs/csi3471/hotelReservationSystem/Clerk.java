package edu.baylor.ecs.csi3471.hotelReservationSystem;

public class Clerk extends User {

	public Clerk(String nameFirst, String nameLast, AccountInformation account){
		super(nameFirst, nameLast, account);
	}

	public String getPublicInfo(){
		return super.getFullName();
	}
	@Override
	public void launchOptions() {
		new ClerkOptionsGUI(this);
	}
	@Override
	public String[] getTableInfo() {
		String[] myInfo = new String[]{getAccountInformation().getUsername(), getNameFirst(), getNameLast()};
		return(myInfo);
	}
	@Override
	protected void launchProfile() {
		new UserProfileGUI(this);
	}
	public void login(){
		//TODO launch ClerkOptionsGUI once created
	}
}
