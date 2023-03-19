public abstract class AccountInformation{
	private String username, password;
	
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