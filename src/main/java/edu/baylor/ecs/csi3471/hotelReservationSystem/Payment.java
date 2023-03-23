public class Payment {
    Date date;
    Double amount;

    //associations
    CreditCard method;

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

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(CreditCard method) {
        this.method = method;
    }
    CreditCard getPaymentMethod(){ return this.method; }
}
