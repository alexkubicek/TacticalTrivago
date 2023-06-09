/*
 * A class representing an admin in the hotel.
 * Extends the User class.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.util.Random;

import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.AdminOptionsGUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.UserProfileGUI;

public class Admin extends User {
    private int AdminId;

    public Admin(int AdminId, String nameFirst, String nameLast, AccountInformation account){
        super(nameFirst, nameLast, account);
        this.AdminId = AdminId;
    }

    public Admin(String[] line){
        super(line[0], line[1], new AccountInformation(line[2], line[3]));
        AdminId = Integer.parseInt(line[4]);
    }
    public Admin(){
        super();
    }
    @Override
	public void launchProfile() {
        new UserProfileGUI(this);
    }

    public Admin(String nameFirst, String nameLast, AccountInformation account){
        super(nameFirst, nameLast, account);
    }
    @Override
    public void launchOptions() {
        System.out.println("launching admin options");
        new AdminOptionsGUI(this);
    }
    public void edit(){
        new UserProfileGUI(this);
    }
    public int getAdminId() {
        return AdminId;
    }
    public void setAdminId(int id){
        this.AdminId = id;
    }

    public static Clerk createClerk(String Username, String nameFirst, String nameLast){
        if(Username == null || nameFirst == null || nameLast == null) throw new NullPointerException();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  // Characters to use for password
        StringBuilder password = new StringBuilder();  // StringBuilder to store generated password
        Random rnd = new Random();
    
        // Generate random password of length 8
        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        AccountInformation acc = new AccountInformation(Username, password.toString());
        return new Clerk(nameFirst, nameLast, acc);
    }
    public void resetPassword(User user, String newPassword) {
        user.getAccountInformation().setPassword(newPassword);
    }

    public void login(){
        gui = new AdminOptionsGUI(this);
    }

    @Override
    public void updateFromProfileGUI(UserProfileGUI myGUI) {
        myGUI.updateUser(this);
    }
}
