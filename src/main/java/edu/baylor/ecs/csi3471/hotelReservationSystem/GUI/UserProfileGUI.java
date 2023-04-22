package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;


import java.awt.*;

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
    protected User myUser;
    protected static final JPanel fullPanel = new JPanel();
    protected static final JPanel gridPanel = new JPanel();
    protected static final JPanel bottomPanel = new JPanel();
    static{
        gridPanel.setLayout(new GridLayout(6, 2, 10, 10));
        gridPanel.add(adminIDLabel);
        gridPanel.add(adminIDField);
        gridPanel.add(usernameLabel);
        gridPanel.add(usernameField);
        gridPanel.add(passwordLabel);
        gridPanel.add(passwordField);
        gridPanel.add(confirmPasswordLabel);
        gridPanel.add(confirmPasswordField);
        gridPanel.add(firstNameLabel);
        gridPanel.add(firstNameField);
        gridPanel.add(lastNameLabel);
        gridPanel.add(lastNameField);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        isCorporate.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(isCorporate);
        bottomPanel.add(confirmButton);
        gridPanel.setVisible(true);
        bottomPanel.setVisible(true);
        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(gridPanel, BorderLayout.CENTER);
        fullPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    private static void removeIDRow(){
        gridPanel.remove(0);
        gridPanel.remove(0);
    }
    private static void removeCorpCheckBox(){
        bottomPanel.remove(isCorporate);
    }
    public UserProfileGUI(Guest g){
        System.out.println("guest profile");
        myUser = g;
        setTitle("Guest Profile");
        removeIDRow();
        // Add the panel and confirm button to the frame
        add(fullPanel);

        // Set the size of the frame and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }

    public UserProfileGUI(Clerk c){
        System.out.println("clerk profile");

        myUser = c;
        // Set properties for the JFrame
        setTitle("Clerk Profile");
        removeIDRow();
        removeCorpCheckBox();
        // Add the panel and confirm button to the frame
        add(fullPanel);

        // Set the size of the frame and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }

    public UserProfileGUI(Admin a){
        System.out.println("admin profile");

        myUser = a;
        setTitle("Admin Profile");
        removeCorpCheckBox();
        // Add the panel and confirm button to the frame
        add(fullPanel);

        // Set the size of the frame and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    	
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //action listener for guest profile
        // Validate that all required fields are filled in
        if (usernameField.getText().isEmpty() ||
                passwordField.getPassword().length == 0 ||
                confirmPasswordField.getPassword().length == 0 ||
                firstNameField.getText().isEmpty() ||
                lastNameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
            return;
            // This will return to prompting users to fill in textfields
        }

        // TODO: Validate other fields, such as the password and confirm password fields

        // Update the guest object with the new values
        myUser.setAccountUsername(usernameField.getText());
        myUser.setAccountPassword(new String(passwordField.getPassword()));
        myUser.setNameFirst(firstNameField.getText());
        myUser.setNameLast(lastNameField.getText());
        //myUser.setCorporate(isCorporate.isSelected());

        // Show a popup to confirm that the profile was updated
        JOptionPane.showMessageDialog(UserProfileGUI.this, "Profile updated.");
        //TODO validate input
        //todo: profile created/updated popup

    }
}
