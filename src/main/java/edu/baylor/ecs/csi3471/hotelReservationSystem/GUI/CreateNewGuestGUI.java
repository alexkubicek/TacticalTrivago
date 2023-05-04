package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.AccountInformation;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateNewGuestGUI extends JDialog implements ActionListener {
    protected static final JLabel usernameLabel = new JLabel("Username:");
    protected static final JLabel passwordLabel = new JLabel("Password:");
    protected static final JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    protected static final JLabel firstNameLabel = new JLabel("First Name:");
    protected static final JLabel lastNameLabel = new JLabel("Last Name:");
    protected final JTextField usernameField = new JTextField();
    protected final JTextField firstNameField = new JTextField();
    protected final JTextField lastNameField = new JTextField();
    protected final JPasswordField passwordField = new JPasswordField();
    protected final JPasswordField confirmPasswordField = new JPasswordField();
    protected final JCheckBox isCorporate = new JCheckBox("Corporate");
    protected static final JButton confirmButton = new JButton("Confirm");
    protected Guest myUser;

    protected final JPanel fullPanel = new JPanel();
    protected final JPanel gridPanel = new JPanel();
    protected final JPanel bottomPanel = new JPanel();

    private void GUISetUp(){
        System.out.println("setUp()");
        gridPanel.setLayout(new GridLayout(6, 2, 10, 10));
        usernameField.setText(null);
        passwordField.setText(null);
        confirmPasswordField.setText(null);
        firstNameField.setText(null);
        lastNameField.setText(null);
        isCorporate.setSelected(false);
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
        confirmButton.addActionListener(this);
        bottomPanel.add(isCorporate);
        bottomPanel.add(confirmButton);
        gridPanel.setVisible(true);
        bottomPanel.setVisible(true);
        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(gridPanel, BorderLayout.CENTER);
        fullPanel.add(bottomPanel, BorderLayout.SOUTH);
        fullPanel.setVisible(true);
    }
    private void completeSetUp(){
        add(fullPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public CreateNewGuestGUI(Guest u) {
        GUISetUp();
        myUser = u;
        completeSetUp();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String username = usernameField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        char[] password = passwordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();
        boolean corporate = isCorporate.isSelected();
        String passwordString = new String(password);
        String confirmPasswordString = new String(confirmPassword);


        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || passwordString.length() == 0 || confirmPasswordString.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please fill out all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!String.valueOf(password).equals(String.valueOf(confirmPassword))) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Hotel.isUsernameUnique(username)) {
            JOptionPane.showMessageDialog(this, "Username is not unique.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        myUser.setAccountInformation(new AccountInformation(username, passwordString));
        myUser.setNameLast(lastName);
        myUser.setNameFirst(firstName);
        myUser.setCorporate(isCorporate.isSelected());

        JOptionPane.showMessageDialog(this, "Profile Successfully Created!");

        Hotel.addAccount(myUser);
        dispose();
    }
}

