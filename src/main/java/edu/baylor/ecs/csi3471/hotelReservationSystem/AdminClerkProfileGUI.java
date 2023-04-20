package edu.baylor.ecs.csi3471.hotelReservationSystem;

import javax.swing.*;

public class AdminClerkProfileGUI extends JFrame {
    //TODO put generic fields
    //subclasses: GuestProfileGUI
    // with the difference being the isCorp check box
    JLabel usernameLabel, firstNameLabel, lastNameLabel;
    JTextField usernameField, firstNameField, lastNameField;

    public AdminClerkProfileGUI(){
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        usernameLabel = new JLabel("Username:");
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name: ");
        //TODO: format and add to frame
    }
}
