package edu.baylor.ecs.csi3471.hotelReservationSystem;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
public class AddUserUI extends AbstractTableModel {

    private JPanel pane;
    private JPanel cardPanel;
    private JTextField Field_ID;
    private JTextField Field_Password;

    private JTextField FirstName;

    private JTextField LastName;

    private JTextField Address;

    private JTextField PhoneNum;

    private CardLayout cardLayout;


    public AddUserUI(){}

    public JPanel AddUser(){
        Hotel hotel = HotelApp.hotel;
        JPanel form = new JPanel(new GridLayout(0, 1));
        BoxLayout boxLayout = new BoxLayout(form, BoxLayout.Y_AXIS);
        form.setLayout(boxLayout);
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JComboBox<String> ComboBox = new JComboBox<>(new String[]{"Admin", "Clerk"});
        ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = (String)ComboBox.getSelectedItem();
                cardLayout.show(cardPanel, choice);
            }
        });
        comboBoxPanel.add(new JLabel("User Type:"));
        comboBoxPanel.add(ComboBox);
        form.add(comboBoxPanel);

        //--------------------------ComboPanel Implementation--------------------//
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel adminPanel = new JPanel(new GridLayout(0, 2));
        adminPanel.add(new JLabel("Admin:"));
        adminPanel.add(new JLabel(""));
        adminPanel.add(new JLabel("First Name:"));
        adminPanel.add(new JTextField(20));
        adminPanel.add(new JLabel("Last Name:"));
        adminPanel.add(new JTextField(20));
        adminPanel.add(new JLabel("ID:"));
        adminPanel.add(new JTextField(20));
        adminPanel.add(new JLabel("Password:"));
        adminPanel.add(new JTextField(20));
        cardPanel.add(adminPanel, "Admin");

        JPanel clerkPanel = new JPanel(new GridLayout(0, 2));
        clerkPanel.add(new JLabel("Clerk:"));
        clerkPanel.add(new JLabel(""));
        clerkPanel.add(new JLabel("First Name:"));
        clerkPanel.add(new JTextField(20));
        clerkPanel.add(new JLabel("Last Name:"));
        clerkPanel.add(new JTextField(20));
        clerkPanel.add(new JLabel("ID:"));
        clerkPanel.add(new JTextField(20));
        clerkPanel.add(new JLabel("Password:"));
        clerkPanel.add(new JTextField(20));
        cardPanel.add(clerkPanel, "Clerk");

        JPanel guestPanel = new JPanel(new GridLayout(0, 2));
        guestPanel.add(new JLabel("Guest:"));
        guestPanel.add(new JLabel(""));
        guestPanel.add(new JLabel("First Name:"));
        guestPanel.add(new JTextField(20));
        guestPanel.add(new JLabel("Last Name:"));
        guestPanel.add(new JTextField(20));
        guestPanel.add(new JLabel("ID:"));
        guestPanel.add(new JTextField(20));
        guestPanel.add(new JLabel("Password:"));
        guestPanel.add(new JTextField(20));
        cardPanel.add(guestPanel, "Guest");

        form.add(cardPanel);

        //-----------------------------------------------------------------//


        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton regButton = new JButton("Register");
        buttonPane.add(regButton);
        form.add(buttonPane);

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = "";
                String lastName = "";
                String id = "";
                String password = "";

                for (Component component : form.getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField textField = (JTextField) component;
                        if (textField.getName().equals("firstName")) {
                            firstName = textField.getText();
                        } else if (textField.getName().equals("lastName")) {
                            lastName = textField.getText();
                        } else if (textField.getName().equals("id")) {
                            id = textField.getText();
                        } else if (textField.getName().equals("password")) {
                            password = textField.getText();
                        }
                    }
                }

                String type = (String) ComboBox.getSelectedItem();
                AccountInformation account = new AccountInformation(id, password);
                if (type.equals("Admin")) {
                    Admin admin;
                    admin = new Admin(firstName, lastName, account);
                    hotel.addAccount(admin);
                    System.out.println("Adding new admin: " + firstName + " " + lastName);
                } else if (type.equals("Clerk")) {
                    Clerk clerk = new Clerk(firstName, lastName, account);
                    hotel.addAccount(clerk);
                    System.out.println("Adding new clerk: " + firstName + " " + lastName);
                } else if (type.equals("Guest")){
                    Guest guest = new Guest(firstName, lastName, account);
                    hotel.addAccount(guest);
                    System.out.println("Welcome to Tactical Trivago " + firstName + " " + lastName + "!");
                }

            }
        });
        this.pane = new JPanel(new BorderLayout());
        pane.add(form, BorderLayout.CENTER);
        return this.pane;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
