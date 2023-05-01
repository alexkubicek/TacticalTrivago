/**
* The LoginPageGUI class represents the GUI for the login page of the hotel reservation system.
* It extends the JFrame class and implements the ActionListener interface to listen for button events.
* It allows the user to login with a previous username and password, or create a new account.
* If the login is successful, the current window is closed and the user's options are launched through the User.launchOptions method.
* If the login is unsuccessful, a LoginFailurePopupGUI is displayed.
*/
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Objects;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;

public class LoginPageGUI extends JFrame implements ActionListener {
    public LoginPageGUI() {
        setTitle("Tactical Trivago - Login");
        setBounds(500,200,500,300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(107, 104, 70, 16);
        panel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(110, 132, 67, 16);
        panel.add(passwordLabel);
        

        usernameField = new JTextField();
        usernameLabel.setLabelFor(usernameField);
        usernameField.setBounds(189, 99, 130, 26);
        panel.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordLabel.setLabelFor(passwordField);
        passwordField.setColumns(10);
        passwordField.setBounds(189, 127, 130, 26);
        panel.add(passwordField);

        JButton login = new JButton("Login");
        login.addActionListener(this);
        login.setBounds(315, 127, 70, 29);
        panel.add(login);

        JButton createAccount = new JButton("Create Account");
        createAccount.setLocation(189, 155);
        createAccount.setSize(130, 26);
        panel.add(createAccount);
        createAccount.addActionListener(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = ((JButton) e.getSource()).getText();
        if(Objects.equals(text, "Create Account")) {
            Guest myNewGuest = new Guest();
            new UserProfileGUI(myNewGuest);
        } else if(Objects.equals(text, "Login")) {
            if (usernameField.getText().isEmpty() ||
                passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(LoginPageGUI.this, "Please fill in all required fields.");
                return;
                // This will return to prompting users to fill in textfields
            }
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            User me = Hotel.login(username, password);
            if(me != null){
                this.setVisible(false);
                me.launchOptions();
            } else {
                new LoginFailurePopupGUI();
            }

        }
    }
}

