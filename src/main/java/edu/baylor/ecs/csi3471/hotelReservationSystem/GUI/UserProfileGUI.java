package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;


import java.awt.*;

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
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 2, 10, 10));
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(confirmPasswordLabel);
            panel.add(confirmPasswordField);
            panel.add(firstNameLabel);
            panel.add(firstNameField);
            panel.add(lastNameLabel);
            panel.add(lastNameField);

            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Validate that all required fields are filled in
                    if (usernameField.getText().isEmpty() ||
                            confirmPasswordField.getPassword().length == 0 ||
                            firstNameField.getText().isEmpty() ||
                            lastNameField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
                        return;
                    }
                        // This will return to prompting users to fill in textfields
                        // Update the guest object with the new values
                        u.setAccountUsername(usernameField.getText());
                        u.setNameFirst(firstNameField.getText());
                        u.setNameLast(lastNameField.getText());
                        // Show a popup to confirm that the profile was updated
                        JOptionPane.showMessageDialog(UserProfileGUI.this, "Profile updated.");

                }
            });
            add(panel, BorderLayout.CENTER);
            add(confirmButton, BorderLayout.SOUTH);
            // Set the size of the frame and make it visible
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        } else{
            JPanel panel = new JPanel();
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
            panel.add(isCorporate);
            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String confirmPassword = new String(confirmPasswordField.getPassword());
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    boolean corporate = isCorporate.isSelected();

                    // validate inputs
                    if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
                        JOptionPane.showMessageDialog(panel, "Please fill in all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(!password.equals(confirmPassword)){
                        JOptionPane.showMessageDialog(panel, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // create new user
                    AccountInformation accInfo = new AccountInformation(username, password);
                    Guest g = new Guest(firstName, lastName, accInfo);
                    if(corporate){
                        g.setCorporate(true);
                    }
                    JOptionPane.showMessageDialog(panel, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            });
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
        // Set properties for the JFrame
        configFrame("Clerk Profile");
    }

    public UserProfileGUI(Admin a){
        adminIDField.setText(Integer.toString(a.getAdminId()));
        panel.add(adminIDLabel);
        panel.add(adminIDField);


        // Set properties for the JFrame
        configFrame("Admin Profile");

    }

    public void configFrame(String userType) {
    	panel.add(confirmButton);
        confirmButton.addActionListener(this);

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
