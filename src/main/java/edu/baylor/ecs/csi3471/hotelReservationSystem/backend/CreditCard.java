package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CreditCard {
    private Long cardNum;
    private Integer cvv;
    private Date expiration;
    private Address address;
    private String name;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //associations
    private User user;

    public User getUser() {return user;}
    public Long getCardNum(){return cardNum;}
    public Integer getCvv(){return cvv;}
    public Date getExpiration(){return expiration;}

    public CreditCard(){}

    public CreditCard(Date date, Long cardID, Integer threeFancyDigits) {
        this.cardNum = cardID;
        this.expiration = date;
        this.cvv = threeFancyDigits;
    }

    public CreditCard(Date date, Long cardID, Integer cvv, Address billAddress){
        this.cardNum = cardID;
        this.expiration = date;
        this.cvv = cvv;
        this.address = billAddress;
    }

    public static void printDate(Date c) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(f.format(c.getTime()));
    }

    public void setCreditCard(CreditCard card){
        this.cardNum = card.cardNum;
        this.expiration = card.expiration;
        this.cvv = card.cvv;
        this.address = card.address;
        this.name = card.name;
    }

    //create a credit card using a line
    //cardnum,cvv,date,add,name
    public CreditCard(String[] line){

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
             this.expiration = formatter.parse(line[2]);
        } catch (ParseException e) {
            System.err.println("Error in Reservation(String[]): invalid date format from csv");
            throw new RuntimeException(e);
        }

        this.cardNum = parseLong(line[0]);
        this.cvv = parseInt(line[1]);

        this.address = new Address(line[3]);

        this.name = line[4];
    }
    public void displayCardInfo() {
        System.out.println("Card Owner: " + user.getFullName());
        System.out.print("Card Number: " + this.cardNum + "\nCVV: "
                + this.cvv + "\nExpiration Date: ");
        printDate(this.expiration);
    }

    public void setCardNum(Long cardNum) {
        this.cardNum = cardNum;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreditCard(String name, Date date, Long cardID, Integer threeFancyDigits, Integer buildingNumber, Integer zipCode, String street, String city, State tx){
        this.name = name;
        this.expiration = date;
        this.cvv = threeFancyDigits;
        this.cardNum = cardID;
        this.address = new Address(buildingNumber, zipCode, street, city, tx);
    }
}

/*
@Test
 public void validCardNum(CreditCard){
    do something with size if too large or too small then invalid
}
@Test
 public void Cvv(CreditCard){
    do something with size and input specifics to make sure its a real Cvv
 }

 @Test
 public void UserName(CreditCard){
    do something with size and input specifics to make sure it follows
    username rules if there is a set max length or if the username already exists
 }
*/
