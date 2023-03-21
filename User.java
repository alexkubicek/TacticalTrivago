public abstract class User {
  String nameFirst, nameLast;
  AccountInformation account;

  public User(String nameFirst, String nameLast, AccountInformation info){
      this.nameFirst = nameFirst;
      this.nameLast = nameLast;
      this.account = info;
  }
  
  String getFullName() {
	  return nameFirst + " " + nameLast;
  }
  
  String getNameFirst() {
	  return nameFirst;
  }
  
  String getNameLast() {
	  return nameLast;
  }
  
  void setNameFirst(String n) {
	  this.nameFirst = n;
  }
  
  void setNameLast(String n) {
	  this.nameLast = n;
  }
  AccountInformation getAccountInformation(){
    return account;
  }
  void setAccountInformation(AccountInformation info){
      this.account = info;
  }
}
