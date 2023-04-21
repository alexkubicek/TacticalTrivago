package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileGUI extends JFrame implements ActionListener {
	protected static final JPanel panel = new JPanel(new GridLayout(7, 2));
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
        	
            usernameField.setText(u.getAccountUsername());
            firstNameField.setText(u.getNameFirst());
            lastNameField.setText(u.getNameLast());
            passwordField.setText(u.getAccountPassword());
            confirmPasswordField.setText(u.getAccountPassword());
            
            // Create and set layout for the JPanel
            JPanel panel = new JPanel(new GridLayout(7, 2));
            
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(firstNameLabel);
            panel.add(firstNameField);
            panel.add(lastNameLabel);
            panel.add(lastNameField);
            panel.add(confirmButton);
            
            u.launchProfile();
        }
        
        //assume we are making guest
        //todo: needed fields: username, password, confirm password, first and last name, isCorporate checkBox
        //we must be creating a user then
    }
    public UserProfileGUI(Guest g){

    	isCorporate.setSelected(g.corporate());
    	panel.add(isCorporate);

        panel.add(confirmButton);
        confirmButton.addActionListener(this);

        // Set properties for the JFrame
        configFrame("Guest Profile");
    }

    public UserProfileGUI(Clerk c){
    	
        panel.add(confirmButton);
        confirmButton.addActionListener(this);

        // Set properties for the JFrame
        configFrame("Clerk Profile");
    }

    public UserProfileGUI(Admin a){
    
        adminIDField.setText(Integer.toString(a.getAdminId()));

        panel.add(adminIDLabel);
        panel.add(adminIDField);

        panel.add(confirmButton);
        confirmButton.addActionListener(this);

        // Set properties for the JFrame
        configFrame("Admin Profile");
    	
    }

    public void configFrame(String userType) {
    	this.setTitle(userType);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO validate input
        //todo: profile created/updated popup
    }
}
