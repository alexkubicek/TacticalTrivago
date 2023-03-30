package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private Date date;
    private Double amount;

    //associations
    private CreditCard method;

    //Constructors
    public Payment(){}
    public Payment(Date date, Double monDue, CreditCard newCreditCard){
        this.date = date;
        this.amount = monDue;
        this.method = newCreditCard;
    }

    public static void printDate(Date c){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(f.format(c.getTime()));
    }
    public void displayInformation(){
        System.out.print("Payment Required: " + amount +
                "\nDue Date: ");
        printDate(this.date);
    }

    //for when the guest pays
    public void updatePayment(Double guestPay){
        this.amount = this.amount - guestPay;
    }

    public void setDate(Date d) {
        this.date = d;
    }
    
    public Date getDate() {
        return this.date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {return amount;}

    public void setPaymentMethod(CreditCard method) {
        this.method = method;
    }
    CreditCard getPaymentMethod(){ return this.method; }
}

/*
@Test       CAN BE USED FOR SAME FORMAT FOR DATE SET AS WELL
 public void dateUpdate(Payment){
    do something with date to make sure it can happen within the future
    (basically that no unreasonable dates are entered)
}
@Test
 public void paymentUpdate(CreditCard){
    do something to make sure that the guest cant have money withdrawn if it
    results in a negative value
 }

 @Test
 public void UserName(CreditCard){
    do something with size and input specifics to make sure it follows
    username rules if there is a set max length or if the username already exists
 }
*/