package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Objects;

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
  public void setAccountUsername(String n){
    account.setUsername(n);
  }
  public void setAccountPassword(String n){
    account.setPassword(n);
  }
  public AccountInformation getAccountInformation(){return account;}
  //TODO Adding a temporary getter for CSV, for permissions we might want
  // to get rid of this but we need to figure out how to access accounts for csv
  public String getAccountPassword(){return account.getPassword();}
  public String getAccountUsername(){return account.getUsername();}

  public void setAccountInformation(AccountInformation info){
      this.account = info;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(nameFirst, user.nameFirst) && Objects.equals(nameLast, user.nameLast) && Objects.equals(account, user.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nameFirst, nameLast, account);
  }
}
