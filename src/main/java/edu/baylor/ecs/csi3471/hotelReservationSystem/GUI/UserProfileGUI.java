package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileGUI extends JFrame implements ActionListener {
    protected static final JLabel adminIDLabel = new JLabel("ID:");
    protected static final JLabel usernameLabel = new JLabel("Username:");
    protected static final JLabel passwordLabel = new JLabel("Password:");
    protected static final JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    protected static final JLabel firstNameLabel = new JLabel("First Name:");
    protected static final JLabel lastNameLabel = new JLabel("Last Name:");
    protected static final JFormattedTextField adminIDField = new JFormattedTextField();
    protected static final JTextField usernameField = new JTextField();
    protected static final JTextField firstNameField = new JTextField();
    protected static final JTextField lastNameField = new JTextField();
    protected static final JPasswordField passwordField = new JPasswordField();
    protected static final JPasswordField confirmPasswordField = new JPasswordField();
    protected static final JCheckBox isCorporate = new JCheckBox("Corporate");
    protected static final JButton confirmButton = new JButton("Confirm");
    public UserProfileGUI(User u, boolean createClerk){
        if(u != null){
            u.launchProfile();
        }
        if(createClerk){
            //todo: needed fields: username, firstName, lastName
        } else{
            //todo: creating guest, need fields: all but adminID
        }
        //we must be creating a user then
    }
    public UserProfileGUI(User u){
        if(u != null){
            u.launchProfile();
        }
        //assume we are making guest
        //todo: needed fields: username, password, confirm password, first and last name, isCorporate checkBox
        //we must be creating a user then
    }
    public UserProfileGUI(Guest g){
        //todo: needed fields - all but admin id
    }

    public UserProfileGUI(Clerk c){
        //todo: needed fields - all but check box and admin id
    }

    public UserProfileGUI(Admin a){
        //todo: needed fields - all but check box
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO validate input
        //todo: profile created/updated popup
    }
}
