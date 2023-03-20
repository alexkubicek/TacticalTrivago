public class CreditCard {
    Integer cardNum, cvv;
    Date expiration;

    //associations
    Person p;

    public CreditCard(){}
    public CreditCard(Date date, Integer cardID, Integer threeFancyDigits){
        this.cardNum = cardID;
        this.expiration = date;
        this.cvv = threeFancyDigits;
    }

    public static void printDate(Date c){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(f.format(c.getTime()));
    }
    public void displayInfo(){
        System.out.print("Card Number: " + this.cardNum + "\ncvv: "
                + this.cvv + "Expiration Date: ");
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

    public void setP(Person p) {
        this.p = p;
    }
}
