package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.AddUserUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.RoomManagementUI;
import edu.baylor.ecs.csi3471.hotelReservationSystem.GUI.RoomReservationUI;
import hotelReadWriteUtils.java.CSVHotelUtils;

import java.awt.GridLayout;

public class HotelApp extends javax.swing.JFrame{
	private JTabbedPane tabs;
	static CSVHotelUtils csv;
	private static Hotel hotel;
	
	public static Hotel getHotel() {
		return hotel;
	}
	
	static {
		try {
			csv = new CSVHotelUtils();
			hotel = csv.load();
		}catch(FileNotFoundException e) {
			System.err.println("CSV file not found: " + e.getMessage());
		}
	}
	
    public HotelApp() {
        setTitle("Hotel Management System: ");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabs = new JTabbedPane();
        JPanel pan1 = new JPanel();
        RoomManagementUI r = new RoomManagementUI();
        pan1.add(r);
        JPanel pan2 = new JPanel();

        Guest g = new Guest("firstname", "lastname", new AccountInformation());
        RoomReservationUI customTableModel = new RoomReservationUI();
        //JTable customTable = new JTable(customTableModel);
        //JScrollPane scrollPane = new JScrollPane(customTable);
        //customTable.setVisible(true);
        //scrollPane.setVisible(true);
        //pan2.add(scrollPane);

        JButton reserveButton = new JButton("Reserve room");
        reserveButton.setVisible(true);
        pan2.add(reserveButton);
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        customTableModel.display(g);
                    }
                });
            }
        });

        AddUserUI addUserUI = new AddUserUI();
        JPanel pan3 = addUserUI.AddUser();

        tabs.addTab("Add rooms", null, pan1);
        tabs.addTab("Reserve rooms", null, pan2);
        tabs.addTab("Add User", null, pan3);
        add(tabs);
        this.pack();
    }
    /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelApp().setVisible(true);
            }
        });
    }*/
}
