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
        adminPanel.add(new JLabel("Admin Form:"));
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
        clerkPanel.add(new JLabel("Clerk Form:"));
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

        form.add(cardPanel);

        //-----------------------------------------------------------------//


        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton regButton = new JButton("Register");
        buttonPane.add(regButton);
        form.add(buttonPane);

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = ((JTextField) form.getComponent(1)).getText();
                String lastName = ((JTextField) form.getComponent(3)).getText();
                String id = ((JTextField) form.getComponent(5)).getText();
                String password = ((JTextField) form.getComponent(7)).getText();
                String type = (String) ComboBox.getSelectedItem();
                AccountInformation account = new AccountInformation(id, password);
                if (type.equals("Admin")) {
                    Admin admin = new Admin(firstName, lastName, account);
                    hotel.addAccount(admin);
                    System.out.println("Adding new admin: " + firstName + " " + lastName);
                } else if (type.equals("Clerk")) {
                    Clerk clerk = new Clerk(firstName, lastName, account);
                    hotel.addAccount(clerk);
                    System.out.println("Adding new clerk: " + firstName + " " + lastName);
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
