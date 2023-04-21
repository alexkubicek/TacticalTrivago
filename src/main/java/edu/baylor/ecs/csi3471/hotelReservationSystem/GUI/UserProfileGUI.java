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
        setTitle("Guest Profile");

        // Create a JPanel to hold the fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Add the fields and labels to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(new JLabel()); // empty label for spacing
        panel.add(isCorporate);

        JButton confirmButton = new JButton("Confirm");

        // Confirm Button ActionListener (Will validate TextField inputs)
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                g.setAccountUsername(usernameField.getText());
                g.setAccountPassword(new String(passwordField.getPassword()));
                g.setNameFirst(firstNameField.getText());
                g.setNameLast(lastNameField.getText());
                g.setCorporate(isCorporate.isSelected());

                // Show a popup to confirm that the profile was updated
                JOptionPane.showMessageDialog(UserProfileGUI.this, "Profile updated.");
            }
        });

        // Add the panel and confirm button to the frame
        add(panel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        // Set the size of the frame and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
