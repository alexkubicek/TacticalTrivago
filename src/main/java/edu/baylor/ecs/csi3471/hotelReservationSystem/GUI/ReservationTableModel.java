/**
* This class represents the table model for displaying reservations in the hotel. It extends JPanel and implements
* the LaunchEditor interface. It provides methods to load reservations into the table and delete a selected
* reservation. The table is sortable and filterable by column.
*/
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class ReservationTableModel extends JPanel implements LaunchEditor{
    public static final Class<?>[] columnClass = new Class[] {String.class, String.class, String.class, String.class, String.class};
    public static final String[] columnNames = {"Guest", "Start Date", "End Date", "Rooms", "Cost"};
    protected JTable table;

    private static final int MAX_RESERVATIONS = 50;
    private static final int NUM_COLUMNS = 5;
    private static final Object[][] reservations = new Object[MAX_RESERVATIONS][NUM_COLUMNS];
    public ReservationTableModel(){
        super();
        // get all rooms from hotel
        loadReservationsIntoTable(Hotel.reservations);
        // create table of rooms
        DefaultTableModel model = new DefaultTableModel(reservations, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
            @Override
            public Class<?> getColumnClass(int columnIndex) {return columnClass[columnIndex];}
        };
        table = new JTable(model);
        // set dimensions of table
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // make it scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        // add filters for each column
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
    }
    public void loadReservationsIntoTable(List<Reservation> reservationList) {
        int i = 0;
        for (Reservation r : reservationList) {
            if (i > MAX_RESERVATIONS) {
                return;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd");
            reservations[i] = new Object[NUM_COLUMNS];
            reservations[i][0] = r.getGuest().getAccountUsername();
            reservations[i][1] = formatter.format(r.getStartDate());
            reservations[i][2] = formatter.format(r.getEndDate());
            reservations[i][3] = r.getRoomsString(); // Fix: Move this line here
            reservations[i][4] = String.format("$%.2f", r.calculateTotal()); // Fix: Move this line here
            i++; // Fix: Increment the counter
        }
    }


    @Override
    public void launch() {
        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EditReservationGUI editReservationGUI = new EditReservationGUI(Hotel.reservations.get(index));

        // Listen for window close event and update table accordingly
        editReservationGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // If updated is true, update the reservation
                if (editReservationGUI.isReservationUpdated()) {
                    Reservation updatedRes = editReservationGUI.getUpdatedReservation();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt(updatedRes.getStartDate(), index, 1);
                    model.setValueAt(updatedRes.getEndDate(), index, 2);
                    model.setValueAt(updatedRes.getRoomsString(), index, 3);
                    model.setValueAt(String.format("$%.2f", updatedRes.calculateTotal()), index, 4);
                    model.fireTableRowsUpdated(index, index);
                }
            }
        });
    }


    @Override
    public JTable getTable() {
        return table;
    }

    @Override
    public String getMessage() {
        return "No reservation selected";
    }

    @Override
    public void deleteSelected() {
        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to cancel " + table.getValueAt(index, 0) + "'s reservation?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Hotel.reservations.remove(index);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(index);
            JOptionPane.showMessageDialog(null, "Reservation successfully deleted");
        }
    }

    
}

