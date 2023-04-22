
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;
import hotelReadWriteUtils.java.CSVHotelUtils;

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

    private final boolean isAdmin;

    public UserProfileGUI(User u, boolean isAdmin) {
        this.isAdmin = isAdmin;
        if (u != null) {
            u.launchProfile();
        }
        if (isAdmin) {
            // Add clerk fields to panel
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(firstNameLabel);
            panel.add(firstNameField);
            panel.add(lastNameLabel);
            panel.add(lastNameField);

            // Set properties for the JFrame
            configFrame("Create Clerk Account");
        } else {
            // Add guest fields to panel
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

            // Set default values for the fields
            confirmPasswordField.addActionListener(this);
            panel.add(confirmButton);
            confirmButton.addActionListener(this);

            // Set properties for the JFrame
            configFrame("Create Guest Account");
        }
    }

    public UserProfileGUI(User u) {
        this.isAdmin = false;
        if (u != null) {
            // Add user fields to panel
            usernameField.setText(u.getAccountUsername());
            firstNameField.setText(u.getNameFirst());
            lastNameField.setText(u.getNameLast());
            passwordField.setText(u.getAccountPassword());
            confirmPasswordField.setText(u.getAccountPassword());

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

            // Add confirm button to panel
            confirmPasswordField.addActionListener(this);
            panel.add(confirmButton);
            confirmButton.addActionListener(this);

            // Set properties for the JFrame
            configFrame("Edit User Account");
            // Launch user profile
            u.launchProfile();
        }
    }

    public UserProfileGUI(Guest g) {
        this.isAdmin = false;
        // Add guest fields to panel
        isCorporate.setSelected(g.corporate());
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

        // Add confirm button to panel
        confirmPasswordField.addActionListener(this);
        panel.add(confirmButton);
        confirmButton.addActionListener(this);

        // Set properties for the JFrame
        configFrame("Guest Profile");
    }

    public UserProfileGUI(Clerk c) {
        this.isAdmin = false;
        // Add clerk fields to panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        // Add confirm button to panel
        panel.add(confirmButton);
        confirmButton.addActionListener(this);

        // Set properties for the JFrame
        configFrame("Clerk Profile");
    }

    public UserProfileGUI(Admin a) {
        this.isAdmin = false;
        // Add admin fields to panel
        adminIDField.setText(Integer.toString(a.getAdminId()));
        adminIDField.setEditable(false);
        panel.add(adminIDLabel);
        panel.add(adminIDField);

        // Set properties for the JFrame
        configFrame("Admin Profile");
    }

    public void configFrame(String userType) {
        // Add confirm button to panel
        panel.add(confirmButton);
        confirmButton.addActionListener(this);

        this.setTitle(userType);
        this.add(panel);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
//            //TODO validate input
//            //todo: profile created/updated popup
//            String[] userInfo = {usernameField.getText(), passwordField.getText(), firstNameField.getText(),
//                    lastNameField.getText()};
//            if (isAdmin) {
//                
//                
//            } else {
//                
//            }
//            CSVHotelUtils csvUtils = new CSVHotelUtils();
//            csvUtils.save(Hotel.getInstance());
        }
    }



    public boolean isAdmin() {
        return isAdmin;
    }
}

           

