/**
* This class represents a GUI window for editing and viewing user profiles.
* It allows guests, clerks, and admins to view and edit their profiles.
* It contains several fields, including username, password, and first and last names.
* This class extends JDialog and implements ActionListener to handle user actions.
*/

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
    protected final JTextField usernameField = new JTextField();
    protected final JTextField firstNameField = new JTextField();
    protected final JTextField lastNameField = new JTextField();
    protected final JPasswordField passwordField = new JPasswordField();
    protected final JPasswordField confirmPasswordField = new JPasswordField();
    protected final JCheckBox isCorporate = new JCheckBox("Corporate");
    protected static final JButton confirmButton = new JButton("Confirm");
    protected User myUser;
    protected final JPanel fullPanel = new JPanel();
    protected final JPanel gridPanel = new JPanel();
    protected final JPanel bottomPanel = new JPanel();
    protected boolean create = false, fieldError = false;
    private boolean successful = false;
    protected boolean clerkCreate = false;
    private void setUp(){
        System.out.println("setUp()");
        gridPanel.setLayout(new GridLayout(6, 2, 10, 10));
        adminIDField.setText(null);
        usernameField.setText(null);
        passwordField.setText(null);
        confirmPasswordField.setText(null);
        firstNameField.setText(null);
        lastNameField.setText(null);
        isCorporate.setSelected(false);
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
        fullPanel.setVisible(true);
    }
    private void completeSetUp(){
        add(fullPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void removeIDRow(){
        System.out.println("removeIDRow()");
        gridPanel.remove(adminIDField);
        gridPanel.remove(adminIDLabel);
        int rows = ((GridLayout)gridPanel.getLayout()).getRows();
        gridPanel.setLayout(new GridLayout(rows - 1, 2, 10, 10));
    }
    private void removeCorpCheckBox(){
        System.out.println("removeCorpCheckBox()");
        bottomPanel.remove(isCorporate);
    }
    private void removePasswordFields(){
        System.out.println("removePasswordFields()");
        gridPanel.remove(passwordLabel);
        gridPanel.remove(passwordField);
        gridPanel.remove(confirmPasswordLabel);
        gridPanel.remove(confirmPasswordField);
        int rows = ((GridLayout)gridPanel.getLayout()).getRows();
        gridPanel.setLayout(new GridLayout(rows - 2, 2, 10, 10));
    }
    private void checkIfCreate(){
        System.out.println("checkIfCreate()");
        if(myUser.getAccountInformation() == null){
            create = true;
            myUser.setAccountInformation(new AccountInformation());
        } else {
            fillStandardFieldsFromUser();
            System.out.println("not creating - filling fields");
        }
    }
    private void fillStandardFieldsFromUser(){
        System.out.println("fillStandardFieldsFromUser()");
        usernameField.setText(myUser.getAccountUsername());
        firstNameField.setText(myUser.getNameFirst());
        lastNameField.setText(myUser.getNameLast());
        passwordField.setText(myUser.getAccountPassword());
        confirmPasswordField.setText(myUser.getAccountPassword());
    }
    public UserProfileGUI(Guest g){
        System.out.println("guest constructor");
        setUp();
        myUser = g;
        checkIfCreate();

        //remove unnecessary fields
        removeIDRow();
        if(!create){
            fillStandardFieldsFromUser();
            isCorporate.setSelected(g.corporate());
        }
        completeSetUp();
    }

    public UserProfileGUI(Clerk c){
        System.out.println("clerk constructor");
        setUp();
        myUser = c;
        checkIfCreate();

        //remove unnecessary fields
        removeIDRow();
        removeCorpCheckBox();
        if(create){
            removePasswordFields();
            clerkCreate = true;
        } else {
            fillStandardFieldsFromUser();
        }
        completeSetUp();
    }

    public UserProfileGUI(Admin a){
        System.out.println("admin constructor");
        setUp();
        myUser = a;
        checkIfCreate();

        //remove unnecessary fields
        removeCorpCheckBox();
        if(!create){
            fillStandardFieldsFromUser();
        }
        completeSetUp();
    }
    private void fillFromStandardFields(){
        System.out.println("fillFromStandardFields()");
        if (usernameField.getText().isEmpty() ||
                firstNameField.getText().isEmpty() ||
                lastNameField.getText().isEmpty() ||
                passwordField.getPassword().length == 0 ||
                confirmPasswordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
            fieldError = true;
            return;
            // This will return to prompting users to fill in textfields
        }
        String newUsername = usernameField.getText();
        if(!Objects.equals(newUsername, myUser.getAccountUsername()) && !Hotel.isUsernameUnique(newUsername)){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Username is not unique.");
            fieldError = true;
            return;
        }
        myUser.setAccountUsername(newUsername);
        myUser.setNameFirst(firstNameField.getText());
        myUser.setNameLast(lastNameField.getText());
        if(passwordField.getPassword().length == 0 ||
                confirmPasswordField.getPassword().length == 0){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
            fieldError = true;
            return;
        }
        String myPass, confirmMyPass;
        myPass = String.valueOf(passwordField.getPassword());
        confirmMyPass = String.valueOf(confirmPasswordField.getPassword());
        if(!myPass.equals(confirmMyPass)){
            JOptionPane.showMessageDialog(UserProfileGUI.this, "Passwords don't match.");
            fieldError = true;
            return;
        }
        myUser.setAccountPassword(myPass);
    }

    public void updateUser(Guest g){
        System.out.println("guest update");
        fillFromStandardFields();
        if(fieldError){
            return;
        }
        g.setCorporate(isCorporate.isSelected());
        if(create){
            Hotel.addAccount(g);
        }
    }
    public void updateUser(Clerk c){
        System.out.println("clerk update");
        if(create){
            if (usernameField.getText().isEmpty() ||
                    firstNameField.getText().isEmpty() ||
                    lastNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(UserProfileGUI.this, "Please fill in all required fields.");
                fieldError = true;
                return;
            }
            String newUsername = usernameField.getText();
            if(!Objects.equals(newUsername, myUser.getAccountUsername()) && !Hotel.isUsernameUnique(newUsername)){
                JOptionPane.showMessageDialog(UserProfileGUI.this, "Username is not unique.");
                fieldError = true;
                return;
            }
            Hotel.addAccount(Admin.createClerk(usernameField.getText(), firstNameField.getText(), lastNameField.getText()));
        } else {
            fillFromStandardFields();
        }
    }
    public void updateUser(Admin a){
        System.out.println("admin update");
        fillFromStandardFields();
        if(fieldError){
            return;
        }
        a.setAdminId(Integer.parseInt(adminIDField.getText()));
        if(create){
            Hotel.addAccount(a);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fieldError = false;
        System.out.println(Hotel.accounts);
        myUser.updateFromProfileGUI(this);
        if(fieldError){
            this.dispose();
            return;
        }
        System.out.println(Hotel.accounts);
        String message = "Profile Successfully Updated";
        if(create){
            message = message.replace("Updated", "Created");
        }
        JOptionPane.showMessageDialog(UserProfileGUI.this, message);
        System.out.println("dialog shown");
        successful = true;
        this.dispose();
    }

    public User getMyUser(){
        return myUser;
    }
    public boolean isSuccessful() { return successful; }
}
