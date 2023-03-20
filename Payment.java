import java.util.Date;

public class Payment {
  Date d;
  Double amount;
  
  //associations
  User p;
  CreditCard method;
  
  public Date getDate(){
    return d;
  }
}
