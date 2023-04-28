package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CheckInGUI extends JFrame {
    private JTextField guestNameField;
    private JButton findReservationsButton;
    private JTable reservationsTable;
    private DefaultTableModel reservationsTableModel;
    private JButton checkInButton;
    
    private ReservationTableModel reservationTableModel;

    public CheckInGUI() {
        setTitle("Check-in");
        setLayout(new BorderLayout());
        reservationTableModel = new ReservationTableModel();

        // Initialize the reservationsTableModel and assign it to the reservationsTable
        reservationsTableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Guest", "Rooms", "Start Date", "End Date", "Cost"});
        reservationsTable = new JTable(reservationsTableModel);

        JPanel searchPanel = new JPanel(new FlowLayout());
        guestNameField = new JTextField(15);
        findReservationsButton = new JButton("Find Reservations");

        searchPanel.add(new JLabel("Enter Guest Name:"));
        searchPanel.add(guestNameField);
        searchPanel.add(findReservationsButton);
        add(searchPanel, BorderLayout.NORTH);

        // Add the reservationsTable to the center of the frame
        JScrollPane scrollPane = new JScrollPane(reservationsTable);
        add(scrollPane, BorderLayout.CENTER);

        findReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guestName = guestNameField.getText();
                List<Reservation> reservations = Hotel.getReservations();
                updateReservationsTable(reservations);
            }
        });

        checkInButton = new JButton("Check In");

        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = reservationsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Reservation reservation = getReservationFromSelectedRow(selectedRow);
                    // Proceed with the payment process
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reservation to check in.");
                }
            }
        });

        // Add the checkInButton to the bottom of the frame
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(checkInButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void updateReservationsTable(List<Reservation> reservations) {
        // Clear the table
        reservationsTableModel.setRowCount(0);

        // Populate the table with the reservations
        for (Reservation reservation : reservations) {
            Object[] rowData = { reservation.getGuest().getNameFirst(), reservation.getRooms(),
                    reservation.getEndDate(), reservation.getStartDate()};
            reservationsTableModel.addRow(rowData);
        }
    }

    private Reservation getReservationFromSelectedRow(int selectedRow) {
        // Retrieve the reservation ID from the table
        int reservationId = (Integer) reservationsTable.getValueAt(selectedRow, 0);
		return null;

        // Find and return the Reservation object using the reservation ID
        // ... your code to find the reservation by ID
    }
}
