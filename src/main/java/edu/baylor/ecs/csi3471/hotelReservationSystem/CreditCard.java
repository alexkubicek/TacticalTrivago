package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CreditCard {
    Integer cardNum, cvv;
    Date expiration;

    //associations
    User user;

    public CreditCard(Date date, Integer cardID, Integer threeFancyDigits) {
        this.cardNum = cardID;
        this.expiration = date;
        this.cvv = threeFancyDigits;
    }

    public static void printDate(Date c) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(f.format(c.getTime()));
    }

    public void displayCardInfo() {
        System.out.println("Card Owner: " + user.getFullName());
        System.out.print("Card Number: " + this.cardNum + "\nCVV: "
                + this.cvv + "\nExpiration Date: ");
        printDate(this.expiration);
    }

    public void setCardNum(Integer cardNum) {
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
}
