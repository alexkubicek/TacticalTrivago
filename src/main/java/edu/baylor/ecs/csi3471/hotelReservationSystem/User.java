package edu.baylor.ecs.csi3471.hotelReservationSystem;

public abstract class User {
  private String nameFirst, nameLast;
  private AccountInformation account;

  public User(){}
  public User(String nameFirst, String nameLast, AccountInformation info){
      this.nameFirst = nameFirst;
      this.nameLast = nameLast;
      this.account = info;
  }
  
  public String getFullName() {
	  return nameFirst + " " + nameLast;
  }
  
  public String getNameFirst() {
	  return nameFirst;
  }
  
  public String getNameLast() {
	  return nameLast;
  }
  
  public void setNameFirst(String n) {
	  this.nameFirst = n;
  }
  
  public void setNameLast(String n) {
	  this.nameLast = n;
  }
  public AccountInformation getAccountInformation(){
    return account;
  }
  public void setAccountInformation(AccountInformation info){
      this.account = info;
  }
}
