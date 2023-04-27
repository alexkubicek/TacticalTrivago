package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;


import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class UserProfileGUI extends JDialog implements ActionListener {
    protected static final JLabel adminIDLabel = new JLabel("ID:");
    protected static final JLabel usernameLabel = new JLabel("Username:");
    protected static final JLabel passwordLabel = new JLabel("Password:");
    protected static final JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    protected static final JLabel firstNameLabel = new JLabel("First Name:");
    protected static final JLabel lastNameLabel = new JLabel("Last Name:");
    protected static final JFormattedTextField adminIDField = new JFormattedTextField(NumberFormat.getIntegerInstance());
    protected static final JTextField usernameField = new JTextField();
    protected static final JTextField firstNameField = new JTextField();
    protected static final JTextField lastNameField = new JTextField();
    protected static final JPasswordField passwordField = new JPasswordField();
    protected static final JPasswordField confirmPasswordField = new JPasswordField();
    protected static final JCheckBox isCorporate = new JCheckBox("Corporate");
    protected static final JButton confirmButton = new JButton("Confirm");
    protected User myUser;
    protected final JPanel fullPanel = new JPanel();
    protected final JPanel gridPanel = new JPanel();
    protected final JPanel bottomPanel = new JPanel();
    protected boolean create = false, returned;
    private boolean successful = false;
    private void setUp(){
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
        confirmButton.addActionListener(this);
        bottomPanel.add(isCorporate);
        bottomPanel.add(confirmButton);
        gridPanel.setVisible(true);
        bottomPanel.setVisible(true);
        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(gridPanel, BorderLayout.CENTER);
        fullPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    private void removeIDRow(){
        gridPanel.remove(adminIDField);
        gridPanel.remove(adminIDLabel);
    }
    private void removeCorpCheckBox(){
        bottomPanel.remove(isCorporate);
    }
    private void removePasswordFields(){
        gridPanel.remove(passwordLabel);
        gridPanel.remove(passwordField);
        gridPanel.remove(confirmPasswordLabel);
        gridPanel.remove(confirmPasswordField);
    }
    private void checkIfCreate(){
        if(myUser.getAccountInformation() == null){
            create = true;
            myUser.setAccountInformation(new AccountInformation());
        } else {
            fillStandardFieldsFromUser();
        }
    }
    private void fillStandardFieldsFromUser(){
        usernameField.setText(myUser.getAccountUsername());
        firstNameField.setText(myUser.getNameFirst());
        lastNameField.setText(myUser.getNameLast());
        passwordField.setText(myUser.getAccountPassword());
        confirmPasswordField.setText(myUser.getAccountPassword());
    }
    public UserProfileGUI(Guest g){
        setUp();
        myUser = g;
        checkIfCreate();
        if(!create){
            isCorporate.setSelected(g.corporate());
        }
        setTitle("Guest Profile");
        removeIDRow();
        // Add the panel and confirm button to the frame
        add(fullPanel);

        // have to successfully create an account for the dialog to exit or confirm you want to quit
        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Would you like to exit?", "Exit confirmation", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION) {
                    dispose();
                }
            }
        });
        // Set the size of the frame and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public UserProfileGUI(Clerk c){
        setUp();
        myUser = c;
        checkIfCreate();
        if(create){
            removePasswordFields();
        }
        gridPanel.setLayout(new GridLayout(3, 2));
        // Set properties for the JFrame
        setTitle("Clerk Profile");
        removeIDRow();
        removeCorpCheckBox();
        if(c.getAccountInformation() == null){
            removePasswordFields();
        }
        // Add the panel and confirm button to the frame
        add(fullPanel);

        // Set the size of the frame and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }

    public UserProfileGUI(Admin a){
        setUp();
        myUser = a;
        checkIfCreate();
        if(!create){
            adminIDField.setText(String.valueOf(a.getAdminId()));
        }
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
    private void fillFromStandardFields(){
        if (usernameField.getText().isEmpty() ||
                firstNameField.getText().isEmpty() ||
                lastNameField.getText().isEmpty() ||
                passwordField.getPassword().length == 0 ||
                confirmPasswordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
            returned = true;
            return;
            // This will return to prompting users to fill in textfields
        }
        String newUsername = usernameField.getText();
        if(!Objects.equals(newUsername, myUser.getAccountUsername()) && !Hotel.isUsernameUnique(newUsername)){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Username is not unique.");
            returned = true;
            return;
        }
        myUser.setAccountUsername(newUsername);
        myUser.setNameFirst(firstNameField.getText());
        myUser.setNameLast(lastNameField.getText());
        if(passwordField.getPassword().length == 0 ||
                confirmPasswordField.getPassword().length == 0){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
            returned = true;
            return;
        }
        String myPass, confirmMyPass;
        myPass = String.valueOf(passwordField.getPassword());
        confirmMyPass = String.valueOf(confirmPasswordField.getPassword());
        if(!myPass.equals(confirmMyPass)){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Passwords don't match.");
            returned = true;
            return;
        }
        myUser.setAccountPassword(myPass);
    }

    public void updateUser(Guest g){
        fillFromStandardFields();
        if(returned){
            return;
        }
        g.setCorporate(isCorporate.isSelected());
        myUser = g;
    }
    public void updateUser(Clerk c){
        if(create){
            if (usernameField.getText().isEmpty() ||
                    firstNameField.getText().isEmpty() ||
                    lastNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
                returned = true;
                return;
                // This will return to prompting users to fill in textfields
            }
            myUser = Admin.createClerk(usernameField.getText(), firstNameField.getText(), lastNameField.getText());
        } else {
            fillFromStandardFields();
            if(returned){
                return;
            }
        }
    }
    public void updateUser(Admin a){
        fillFromStandardFields();
        if(returned){
            return;
        }
        if(adminIDField.getText().isEmpty()){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
            returned = true;
            return;
        }
        a.setAdminId(Integer.parseInt(adminIDField.getText()));
        myUser = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        returned = false;
        myUser.updateFromProfileGUI(this);
        if(returned){
            return;
        }
        Hotel.addAccount(myUser);
        String message = "Profile Successfully Updated";
        if(create){
            message = message.replace("Updated", "Created");
        }
        JOptionPane.showMessageDialog(UserProfileGUI.this, message);
        successful = true;
        this.dispose();
    }

    public User getMyUser(){
        return myUser;
    }
    public boolean isSuccessful() { return successful; }
}
