public abstract class User {
	
  String nameFirst, nameLast;
  AccountInformation account;
  
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
  
  
}
