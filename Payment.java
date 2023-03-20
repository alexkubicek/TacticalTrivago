public class Payment {
    Date d;
    Double amount;

    //associations
    //Person p;
    CreditCard method;

    //Constructors
    public Payment(){}
    public Payment(Date date, Double monDue, CreditCard newCreditCard){
        this.d = date;
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
        printDate(this.d);
    }

    //for when the guest pays
    public void updatePayment(Double guestPay){
        this.amount = this.amount - guestPay;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setMethod(CreditCard method) {
        this.method = method;
    }
}
