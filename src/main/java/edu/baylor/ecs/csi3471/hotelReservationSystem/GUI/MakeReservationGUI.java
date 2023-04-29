/**
 * file: MakeReservationGUI
 * use case: MakeReservation - UC1
 * author: KayLynn Beard
 *
 * Uses RoomTableModel to display, filter/search, select any
 * available rooms in the hotel. Reserve room button attempts to
 * reserve selected room for chosen dates.
 *
 * USAGE: new MakeReservationGUI(guest);
 */

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import com.toedter.calendar.JDateChooser;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static edu.baylor.ecs.csi3471.hotelReservationSystem.backend.DateHelper.getDateWithoutTime;

public class MakeReservationGUI {
    private RoomTableModel roomsTable;
    private Date startDate = null, endDate = null;
    private Guest g;

    /**
     * @param g - guest you want to make reservations for
     */
    public MakeReservationGUI(Guest g){
        this.g = g;
        this.roomsTable = new RoomTableModel();
        display();
    }

    private boolean datesAreValid(){
        Date today = null, start = null, end = null;
        if(startDate == null || endDate == null){
            return false;
        }
        try{
            today = getDateWithoutTime(new Date());
            start = getDateWithoutTime(startDate);
            end = getDateWithoutTime(endDate);
        } catch(ParseException ex){
            System.err.println("Exception in ReservationEditorGUI validDates(): " + ex);
            return false;
        }

        // startDate must be after today or equal to today
        if(!start.after(today) && !start.equals(today)){
            return false;
        }
        // endDate must be after startDate or equal to startDate
        return end.after(start) || end.equals(start);
    }


    private void createConfirmationDialog(DefaultTableModel model, int modelRow)  {

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
        if(!room.isAvailable(startDate, endDate)){
            JOptionPane.showMessageDialog(null, "Room is not available for these dates!");
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Please Confirm Reservation Information");
        dialog.setSize(380, 250);
        dialog.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd, yyyy");
        String guestInfo = "Reservation for ";
        if (g.getAccountInformation() == null){
            guestInfo += g.getFullName() + ":</br>";
        } else{
            guestInfo += g.toStringForUI() + ":</br>";
        }
        String roomInfo = room.toStringForUI();
        String reservationInfo = "<html>" + guestInfo +
                "<br/>-------------------------------------------------------------------<br/>" +
                roomInfo + "<br/>-------------------------------------------------------------------<br/>" +
                "Check-in date: " + formatter.format(startDate) +
                "<br> Check-out date: " + formatter.format(endDate) + "</html>";
        panel.add(new JLabel(reservationInfo, SwingConstants.CENTER), BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm reservation");
        panel.add(confirmButton, BorderLayout.SOUTH);
        confirmButton.addActionListener(e -> {
            // reserve room (should never throw an error if you make it this far)
            Hotel.reserveRoom(room, startDate, endDate, this.g);
            roomsTable.updateTable(startDate, endDate);
            JOptionPane.showMessageDialog(null, "Reservation made successfully!");
            // close dialog
            dialog.dispose();
        });
        dialog.add(panel);
    }

    private JPanel createDateSelection(){
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel label1 = new JLabel("Select desired check-in date: ");
        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label2 = new JLabel("Select desired check-out date: ");
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);

        JDateChooser startDateChooser = new JDateChooser();
        startDateChooser.setDateFormatString("MM/dd/yyyy");
        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("MM/dd/yyyy");

        panel.add(label1);
        panel.add(startDateChooser);
        panel.add(label2);
        panel.add(endDateChooser);

        startDateChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                startDate = (Date) e.getNewValue();
                System.out.println("Start Date changed: " + startDate); // Add this print statement
                if(datesAreValid()){
                    roomsTable.updateTable(startDate, endDate);
                }
            }
        });
        endDateChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                endDate = (Date) e.getNewValue();
                System.out.println("End Date changed: " + endDate); // Add this print statement
                if(datesAreValid()) {
                    roomsTable.updateTable(startDate, endDate);
                }
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
            }else if(startDate == null || endDate == null){
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

    private void newFilter(JTextField text) {
        RowFilter<DefaultTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter("(?i)" + text.getText(), 0, 1, 2, 3, 5);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        roomsTable.sorter.setRowFilter(rf);
    }

    private JPanel createSearchBar(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Search rooms:");
        JTextField searchField = new JTextField("");
        searchField.setPreferredSize(new Dimension(350, 25));

        panel.add(label);
        panel.add(searchField);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                newFilter(searchField);
            }
            public void insertUpdate(DocumentEvent e) {
                newFilter(searchField);
            }
            public void removeUpdate(DocumentEvent e) {
                newFilter(searchField);
            }
        });

        return panel;
    }

    private void display(){
        roomsTable.setOpaque(true);

        roomsTable.add(createReserveButton());
        roomsTable.add(createSearchBar());
        roomsTable.add(createDateSelection());

        JFrame frame = new JFrame("Available Rooms in Hotel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setContentPane(roomsTable);
        frame.setVisible(true);
    }

}