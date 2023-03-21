public class Clerk extends User {

	private ReservationHandler clerkReserv;

	public Clerk(String nameFirst, String nameLast , AccountInformation account){
		super();
	}
	String getPublicInfo(){
		return super.getFullName();

	}


}
