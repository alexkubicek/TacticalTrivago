package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Random;

public class Admin extends User {
    private int AdminId;

    public Admin(int AdminId, String nameFirst, String nameLast, AccountInformation account){
        super(nameFirst, nameLast, account);
        this.AdminId = AdminId;
    }

    public int getAdminId() {
        return AdminId;
    }
    public void setAdminId(int id){
        this.AdminId = id;
    }

    public Clerk CreateClerk(String Username, String nameFirst, String nameLast){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  // Characters to use for password
        StringBuilder password = new StringBuilder();  // StringBuilder to store generated password
        Random rnd = new Random();
    
        // Generate random password of length 8
        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        AccountInformation acc = new AccountInformation(Username, password.toString());
        Clerk C = new Clerk(nameFirst, nameLast, acc);
        return C;
    }
    public void resetPassword(User user, String newPassword) {
        user.getAccountInformation().setPassword(newPassword);
    }
}