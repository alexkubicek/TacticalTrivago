/*
 * This is the GUI class for the check-in process of a hotel. 
 * The class extends JFrame and contains a table to display reservations, 
 * search through them, and check in guests for their reservations.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.IFilterEditor;
import net.coderazzi.filters.IFilter;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.FilterSettings;
import net.coderazzi.filters.gui.ParserModel;



public class CheckInGUI extends JFrame {
    private JTextField guestNameField;
    private JButton findReservationsButton;
    private JTable reservationsTable;
    private DefaultTableModel tableModel;

    public CheckInGUI() {
        setTitle("Check-in");
        setLayout(new BorderLayout());

        String[] columnNames = {"Guest", "Start Date", "End Date", "Rooms", "Cost"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        reservationsTable = new JTable(tableModel);
        reservationsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 

        // Add a TableFilterHeader to the reservationsTable
        TableFilterHeader filterHeader = new TableFilterHeader(reservationsTable, AutoChoices.ENABLED);

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
                List<Reservation> reservations = Hotel.getReservationsByGuestName(guestName);
                loadReservationsIntoTable(reservations);

                // Filter the table by guest name
                IFilterEditor filterEditor = filterHeader.getFilterEditor(0);
                filterHeader.getParserModel().setIgnoreCase(true);
                RowFilter<Object, Object> filter = RowFilter.regexFilter("(?i)" + guestName, 0);
                reservationsTable.setRowSorter(new TableRowSorter<>(tableModel));
                ((DefaultRowSorter<?, ?>) reservationsTable.getRowSorter()).setRowFilter(filter);

                // Show a JOptionPane when no reservations are found
                if (reservations.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No reservations found for the provided guest name.");
                }

                // Clear the guest name field
                guestNameField.setText("");
            }
        });


        JButton checkInButton = new JButton("Check In");

        checkInButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    int[] selectedRows = reservationsTable.getSelectedRows();
        	    if (selectedRows.length > 0) {
        	        Reservation reservation = Hotel.getReservations().get(0);
        	        performCheckIn(reservation);
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
    
    private void performCheckIn(Reservation reservation) {
        if (reservation.getGuest().getPaymentMethod() != null) {
            double amount = getamount(reservation.getGuest().getPaymentMethod().getCardNum());
            System.out.println(amount);
            List<Reservation> reservations = Hotel.getReservations();

            if (reservation.checkIn(amount, reservations)) {
                JOptionPane.showMessageDialog(null, "You have checked in successfully");
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Something happened while retrieving the amount from your card. Do you want to try another payment method?", "Payment Failed", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    reservation.getGuest().setPaymentMethods(null);
                    new PaymentGUI(reservation.getGuest());
                    waitForPayment(reservation.getGuest(), () -> performCheckIn(reservation));
                }
            }
        }else {
        	new PaymentGUI(reservation.getGuest());
            waitForPayment(reservation.getGuest(), () -> performCheckIn(reservation));
        }
    }



    private void waitForPayment(Guest guest, Runnable onSuccess) {
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                while (guest.getPaymentMethod() == null) {
                    try {
                        Thread.sleep(500); // Check every 500 milliseconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }

            @Override
            protected void done() {
                onSuccess.run();
            }
        }.execute();
    }


    public void loadReservationsIntoTable(List<Reservation> reservationList) {
    	System.out.println("Loading reservations: " + reservationList);
        tableModel.setRowCount(0);
        for (Reservation r : reservationList) {
            SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd");
            Object[] rowData = new Object[5];
            rowData[0] = r.getGuest().getAccountUsername();
            rowData[1] = formatter.format(r.getStartDate());
            rowData[2] = formatter.format(r.getEndDate());
            rowData[3] = r.getRoomsString();
            rowData[4] = String.format("$%.2f", r.calculateTotal());
            tableModel.addRow(rowData); // Add the new row to the tableModel
        }
    }
    
    public double getamount(long number){
    	System.out.println(number);
    	System.out.println("--------------");
    	String csvFile = "src/main/resources/cards.csv";
        String line;
        double amount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] cardAmount = line.split(",");
                long cardNumber = (long) Double.parseDouble(cardAmount[0]);
                Double money = Double.parseDouble(cardAmount[1]);
                if(cardNumber == number) {
                	amount= money;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return amount;
    }



}
