public class AccountInformation{
	private String username, password;

	public AccountInformation(String username, String password){
		this.username = username;
		this.password = password;
	}

	String getUername() {
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