package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.FlowLayout;
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
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Admin", "Clerk", "Other"});
        userFormPanel.add(genderComboBox);

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
