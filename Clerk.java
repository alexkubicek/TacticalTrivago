public class Clerk extends User {

	public Clerk(String nameFirst, String nameLast , AccountInformation account){
		super();
	}
	String getPublicInfo(){
		return super.getFullName();
		
	}
}
