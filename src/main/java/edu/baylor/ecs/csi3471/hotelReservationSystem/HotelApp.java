package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.GridLayout;

public class HotelApp extends javax.swing.JFrame{
	private JTabbedPane tabs;

    public HotelApp() {
        setTitle("Hotel Management System: ");
        setLayout(new FlowLayout());
        tabs = new JTabbedPane();
        JPanel pan1 = new JPanel();
        RoomManagementUI r = new RoomManagementUI();
        pan1.add(r);
        JPanel pan2 = new JPanel();
        RoomReservationUI customTableModel = new RoomReservationUI();
        JTable customTable = new JTable(customTableModel);
        JScrollPane scrollPane = new JScrollPane(customTable);
        pan2.add(scrollPane);
        JPanel pan3 = new JPanel();
        JPanel userFormPanel = new JPanel(new GridLayout(0, 2)); // 2 columns
        userFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding


        userFormPanel.add(new JLabel("First Name: "));
        userFormPanel.add(new JTextField(20));
        userFormPanel.add(new JLabel("Last Name: "));
        userFormPanel.add(new JTextField(20));
        userFormPanel.add(new JLabel("ID: "));
        userFormPanel.add(new JTextField(20));
        userFormPanel.add(new JLabel("Password: "));
        userFormPanel.add(new JTextField(20));
        userFormPanel.add(new JLabel("Type: "));
        JComboBox<String> ComboBox = new JComboBox<>(new String[]{"Admin", "Clerk", "Other"});
        userFormPanel.add(ComboBox);

        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        buttonPane.add(submitButton);
        userFormPanel.add(buttonPane);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = ((JTextField) userFormPanel.getComponent(1)).getText();
                String lastName = ((JTextField) userFormPanel.getComponent(3)).getText();
                String id = ((JTextField) userFormPanel.getComponent(5)).getText();
                String password = ((JTextField) userFormPanel.getComponent(7)).getText();
                String type = (String) ComboBox.getSelectedItem();

                if (type.equals("Admin")) {
                    /*
                      We can implement adding feature in here
                     */
                    System.out.println("Adding new admin: " + firstName + " " + lastName);
                } else if (type.equals("Clerk")) {
                    /*
                      We can implement adding feature in here later
                     */
                    System.out.println("Adding new clerk: " + firstName + " " + lastName);
                }

            }
        });


        pan3.add(userFormPanel);

        tabs.addTab("Add rooms", null, pan1);
        tabs.addTab("Reserve rooms", null, pan2);
        tabs.addTab("Add User", null, pan3);
        add(tabs);
        this.pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelApp().setVisible(true);
            }
        });
    }
}
