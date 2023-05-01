/**
* The LoginPageGUI class represents the GUI for the login page of the hotel reservation system.
* It extends the JFrame class and implements the ActionListener interface to listen for button events.
* It allows the user to login with a previous username and password, or create a new account.
* If the login is successful, the current window is closed and the user's options are launched through the User.launchOptions method.
* If the login is unsuccessful, a LoginFailurePopupGUI is displayed.
*/
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;

import static java.lang.System.exit;
//import aesthetics.fonts.Yaahowu.*;

public class LoginPageGUI extends JFrame implements ActionListener {
    public LoginPageGUI() throws IOException, FontFormatException {
        setTitle("Tactical Trivago - Login");

        setSize(500,300);
        setLocationRelativeTo(null);

        Font font = Font.createFont(Font.TRUETYPE_FONT,
                (Files.newInputStream(Paths.get("./src/main/java/aesthetics/fonts/B20Sans.ttf"))));
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        genv.registerFont(font);
        font = font.deriveFont(28f);

        JPanel panel = new JPanel();
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
        add(Box.createHorizontalStrut(20), BorderLayout.EAST);
        add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        JLabel welcomeLabel = new JLabel("Welcome back");

        JPanel userPassPanel = new JPanel(new GridLayout(2, 2));

        // makesure to derive the size
        welcomeLabel.setFont(font);
        welcomeLabel.setBounds(100, 20, 200, 100);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JLabel usernameLabel = new JLabel("Username: ");

        JLabel passwordLabel = new JLabel("Password: ");

        usernameField = new JTextField();
        usernameLabel.setLabelFor(usernameField);

        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordLabel.setLabelFor(passwordField);
        passwordField.setColumns(10);
        //panel.add(passwordField);

        usernameLabel.setPreferredSize(new Dimension(70, 16));
        usernameLabel.setHorizontalAlignment(JTextField.RIGHT);
        passwordLabel.setPreferredSize(new Dimension(67, 16));
        passwordLabel.setHorizontalAlignment(JTextField.RIGHT);
        passwordField.setPreferredSize(new Dimension(130, 26));
        usernameField.setPreferredSize(new Dimension(130, 26));
        userPassPanel.setBackground(Color.WHITE);

        userPassPanel.add(usernameLabel);
        userPassPanel.add(usernameField);
        userPassPanel.add(passwordLabel);
        userPassPanel.add(passwordField);

        panel.add(userPassPanel, BorderLayout.CENTER);

        JButton login = new JButton("Login");
        login.addActionListener(this);

        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.add(login);
        loginButtonPanel.setBackground(Color.WHITE);
        panel.add(loginButtonPanel, BorderLayout.EAST);

        JButton createAccount = new JButton("Create Account");
        createAccount.setLocation(189, 155);
        createAccount.setPreferredSize(new Dimension(130, 26));
        JPanel createAccountButtonPanel = new JPanel();
        createAccountButtonPanel.add(createAccount);
        createAccountButtonPanel.setBackground(Color.WHITE);
        panel.add(createAccountButtonPanel, BorderLayout.SOUTH);
        createAccount.addActionListener(this);

        pack();
        getContentPane().setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
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

