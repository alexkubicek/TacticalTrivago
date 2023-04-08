package edu.baylor.ecs.csi3471.hotelReservationSystem;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AdminGUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton createClerkButton;
    private JButton confirmButton;
    private Admin associatedAdmin;
    public AdminGUI(String name, User u) {
        associatedAdmin = (Admin)u;
        setTitle("Welcome Admin " + name);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel createClerkAccountTab = new JPanel();
        tabbedPane.addTab("Create Clerk Account", null, createClerkAccountTab, null);
        createClerkAccountTab.setLayout(null);


        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(128, 59, 70, 16);
        createClerkAccountTab.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(203, 54, 130, 26);
        usernameLabel.setLabelFor(usernameField);
        createClerkAccountTab.add(usernameField);
        usernameField.setColumns(10);

        JLabel firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setBounds(122, 92, 76, 16);
        createClerkAccountTab.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(203, 87, 130, 26);
        firstNameLabel.setLabelFor(firstNameField);
        createClerkAccountTab.add(firstNameField);
        firstNameField.setColumns(10);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(124, 125, 74, 16);
        createClerkAccountTab.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(203, 120, 130, 26);
        lastNameLabel.setLabelFor(lastNameField);
        createClerkAccountTab.add(lastNameField);
        lastNameField.setColumns(10);

        createClerkButton = new JButton("Create");
        createClerkButton.addActionListener(this);
        createClerkButton.setBounds(158, 147, 117, 29);
        createClerkAccountTab.add(createClerkButton);

        JPanel resetPasswordTab = new JPanel();
        tabbedPane.addTab("Reset Password", null, resetPasswordTab, null);
        resetPasswordTab.setLayout(null);


        JLabel passLabel = new JLabel("New Password: ");
        passLabel.setBounds(113, 75, 98, 16);
        resetPasswordTab.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 70, 130, 26);
        passwordField.setColumns(10);
        resetPasswordTab.add(passwordField);

        JLabel confirmPassLabel = new JLabel("Confirm Password: ");
        confirmPassLabel.setBounds(89, 103, 122, 16);
        resetPasswordTab.add(confirmPassLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(210, 98, 130, 26);
        confirmPasswordField.setColumns(10);
        resetPasswordTab.add(confirmPasswordField);

        confirmButton = new JButton("Update");
        confirmButton.setBounds(232, 126, 88, 29);
        resetPasswordTab.add(confirmButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createClerkButton){
            if(Hotel.isUsernameUnique(usernameField.getText())){
                //TODO: create clerk account
            } else {
                UsernameTakenPopupGUI utpg = new UsernameTakenPopupGUI();
            }
        } else if(e.getSource() == confirmButton && passwordField.getPassword() != null){
            associatedAdmin.setAccountPassword(Arrays.toString(passwordField.getPassword()));
        }
    }
}
