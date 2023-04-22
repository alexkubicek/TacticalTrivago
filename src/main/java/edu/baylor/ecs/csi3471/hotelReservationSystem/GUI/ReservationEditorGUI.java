/**
 * file: ReservationEditorGUI
 * use case: MakeReservation - UC1
 * author: KayLynn Beard
 *
 * Uses RoomTableModel to display, filter/search, select any
 * (available?) rooms in the hotel. Reserve room button attempts to
 * reserve selected room for chosen dates.
 *
 * INSTRUCTIONS: how to open the reservation editor UI so that guest/clerk can reserve rooms for guest:
 * ReservationEditorGUI r = new ReservationEditorGUI(guest, hotel);
 * r.display();
 */

// TODO: should the table only display available rooms? or all rooms and say "hey this isn't available for what you selected?"
// TODO: add payment functionality
// TODO: make it pretty (change dimensions, font, font size, alignment, button placements, etc...)

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import com.toedter.calendar.JDateChooser;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static edu.baylor.ecs.csi3471.hotelReservationSystem.backend.DateHelper.getDateWithoutTime;

public class ReservationEditorGUI {
    private RoomTableModel roomsTable;
    private Guest g;
    private Hotel h;

    ReservationEditorGUI(Guest g, Hotel h){
        this.g = g;
        this.h = h;
        this.roomsTable = new RoomTableModel(h, g);
    }
    private boolean datesAreValid(){
        Date today = null, start = null, end = null;
        try{
            today = getDateWithoutTime(new Date());
            start = getDateWithoutTime(roomsTable.startDate);
            end = getDateWithoutTime(roomsTable.endDate);
        } catch(ParseException ex){
            System.err.println("Exception in ReservationEditorGUI validDates(): " + ex);
            return false;
        }

        // startDate must be after today
        if(!start.after(today)){
            return false;
        }
        // endDate must be after startDate
        return end.after(start);
    }
    private void createConfirmationDialog(DefaultTableModel model, int modelRow)  {
        // TODO: get payment method, check that payment is valid, confirmation

        // check for valid dates
        if(!datesAreValid()){
            JOptionPane.showMessageDialog(null, "Selected dates are invalid!");
            return;
        }

        int roomNum = (Integer)model.getValueAt(modelRow, 0);
        Room room = Hotel.getRoom(roomNum);
        if(room == null){
            System.err.println("Error in ReservationEditorGUI createConfirmationDialog(): " +
                    "selected room was not found in hotel");
            return;
        }

        // check that room is available for given dates
        if(!room.isAvailable(roomsTable.startDate, roomsTable.endDate)){
            JOptionPane.showMessageDialog(null, "Room is not available for these dates!");
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Confirm Reservation");
        dialog.setSize(400, 300);
        dialog.setVisible(true);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Confirm room reservation information:"));
        panel.add(new JLabel(room.toStringForUI()));
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd, yyyy");
        panel.add(new JLabel("<html>Check-in date: " + formatter.format(roomsTable.startDate) +
                "<br> Check-out date: " + formatter.format(roomsTable.endDate) + "</html>"));

        JButton confirmButton = new JButton("Submit reservation");
        panel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            // reserve room (should never throw an error if you make it this far)
            Hotel.reserveRoom(room, roomsTable.startDate, roomsTable.endDate, this.g);
            JOptionPane.showMessageDialog(null, "Reservation made successfully!");
            // close dialog
            dialog.dispose();
        });
        dialog.add(panel);
    }
    private JPanel createDateSelection(){
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Select desired check-in date:");
        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label2 = new JLabel("Select desired check-out date:");
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);

        JDateChooser startDateChooser = new JDateChooser();
        startDateChooser.setDateFormatString("MM/dd/yyyy");
        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("MM/dd/yyyy");

        panel.add(label1);
        panel.add(startDateChooser);
        panel.add(label2);
        panel.add(endDateChooser);

        // update date variables when a date is selected
        startDateChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                roomsTable.startDate = (Date) e.getNewValue();
                // TODO: update table??
            }
        });
        endDateChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                roomsTable.endDate = (Date) e.getNewValue();
                // TODO: update table??
            }
        });
        return panel;
    }

    private JButton createReserveButton(){
        JButton reserveButton = new JButton("Reserve selected room");
        reserveButton.setVisible(true);
        reserveButton.addActionListener(e -> {
            int viewRow = roomsTable.table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(null, "No room selected!");
            }else if(roomsTable.startDate == null || roomsTable.endDate == null){
                JOptionPane.showMessageDialog(null, "No dates selected!");
            } else {
                int modelRow = roomsTable.table.convertRowIndexToModel(viewRow);
                DefaultTableModel model = (DefaultTableModel)roomsTable.table.getModel();
                // open confirmation dialog based on selected row to reserve
                SwingUtilities.invokeLater(() -> createConfirmationDialog(model, modelRow));
            }
        });
        return reserveButton;
    }

    public void display(){
        roomsTable.setOpaque(true);

        roomsTable.add(createDateSelection());
        roomsTable.add(createReserveButton(), BorderLayout.SOUTH);

        JFrame frame = new JFrame("Available Rooms in Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setContentPane(roomsTable);
        frame.setVisible(true);
    }

}