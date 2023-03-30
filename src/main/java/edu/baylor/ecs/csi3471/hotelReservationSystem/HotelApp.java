package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.*;

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
        tabs.addTab("add rooms", null, pan1);
        tabs.addTab("Reserve rooms", null, pan2);
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
