package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class GuestCreateAccountGUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField confirmPasswordField;
    private JPasswordField passwordField;
    private JTextField nameFirstField;
    private JTextField nameLastField;
    private LoginPageGUI associatedLoginPage;
    private JCheckBox isCorpCheckBox;
    public GuestCreateAccountGUI(LoginPageGUI lp) {
        associatedLoginPage = lp;
        setAlwaysOnTop(true);
        setType(Type.POPUP);
        setTitle("Tactical Trivago - Create Account");
        getContentPane().setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 30, 70, 16);
        getContentPane().add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 58, 70, 16);
        getContentPane().add(passwordLabel);

        usernameField = new JTextField();
        usernameLabel.setLabelFor(usernameField);
        usernameField.setBounds(155, 25, 122, 26);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setBounds(30, 86, 122, 16);
        getContentPane().add(confirmPasswordLabel);

        JLabel nameFirstLabel = new JLabel("First Name: ");
        nameFirstLabel.setBounds(30, 114, 81, 16);
        getContentPane().add(nameFirstLabel);

        JLabel nameLastLabel = new JLabel("Last Name: ");
        nameLastLabel.setBounds(30, 142, 81, 16);
        getContentPane().add(nameLastLabel);

        isCorpCheckBox = new JCheckBox("Corporate Guest");
        isCorpCheckBox.setBounds(30, 170, 158, 23);
        getContentPane().add(isCorpCheckBox);

        confirmPasswordField = new JPasswordField();
        confirmPasswordLabel.setLabelFor(confirmPasswordField);
        confirmPasswordField.setBounds(155, 81, 122, 26);
        getContentPane().add(confirmPasswordField);

        passwordField = new JPasswordField();
        passwordLabel.setLabelFor(passwordField);
        passwordField.setBounds(155, 53, 122, 26);
        getContentPane().add(passwordField);

        nameFirstField = new JTextField();
        nameFirstLabel.setLabelFor(nameFirstField);
        nameFirstField.setBounds(155, 109, 122, 26);
        getContentPane().add(nameFirstField);
        nameFirstField.setColumns(10);

        nameLastField = new JTextField();
        nameLastLabel.setLabelFor(nameLastField);
        nameLastField.setBounds(155, 137, 122, 26);
        getContentPane().add(nameLastField);
        nameLastField.setColumns(10);

        JButton submitButton = new JButton("Create Account");
        submitButton.setBounds(30, 205, 134, 29);
        getContentPane().add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Hotel.isUsernameUnique(usernameField.getText())) {
            AccountInformation guestAI = new AccountInformation(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            Guest newGuest = new Guest(nameFirstField.getText(), nameLastField.getText(), guestAI);
            if(isCorpCheckBox.isSelected()) {
                newGuest.setCorporate(true);
            }
            Hotel.addAccount(newGuest);
            this.setVisible(false);
            associatedLoginPage.setVisible(true);
        } else {
            UsernameTakenPopupGUI utp = new UsernameTakenPopupGUI();
            utp.setVisible(true);
        }
    }
}

