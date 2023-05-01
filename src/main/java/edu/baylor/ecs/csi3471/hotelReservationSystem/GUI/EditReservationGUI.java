package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import static edu.baylor.ecs.csi3471.hotelReservationSystem.backend.DateHelper.getDateWithoutTime;

public class EditReservationGUI extends JFrame {
    private Reservation reservation;
    private JLabel guestLabel, startLabel, endLabel, costLabel;
    private JTextField guestField, costField;
    private JDateChooser startDateChooser, endDateChooser;
    private JButton saveButton, cancelButton;
    private boolean reservationUpdated;

    public EditReservationGUI(Reservation reservation) {
        super("Edit Reservation");
        this.reservation = reservation;
        this.reservationUpdated = false;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initComponents() {
        // Create labels and text fields for editing reservation
        guestLabel = new JLabel("Guest:");
        startLabel = new JLabel("Start Date:");
        endLabel = new JLabel("End Date:");
        costLabel = new JLabel("Cost:");

        guestField = new JTextField(reservation.getGuest().getAccountUsername());
        guestField.setEditable(false);

        // Create date choosers for start and end dates
        startDateChooser = new JDateChooser(reservation.getStartDate());
        endDateChooser = new JDateChooser(reservation.getEndDate());

        // Format the date choosers
        startDateChooser.setDateFormatString("MM/dd/yyyy");
        endDateChooser.setDateFormatString("MM/dd/yyyy");

        costField = new JTextField(String.format("%.2f", reservation.calculateTotal()));
        costField.setEditable(false);

        // Create a panel for the guest information
        JPanel guestPanel = new JPanel(new GridLayout(1, 2));
        guestPanel.add(guestLabel);
        guestPanel.add(guestField);

        // Create a panel for the start date chooser
        JPanel startDatePanel = new JPanel(new GridLayout(1, 2));
        startDatePanel.add(startLabel);
        startDatePanel.add(startDateChooser);

        // Create a panel for the end date chooser
        JPanel endDatePanel = new JPanel(new GridLayout(1, 2));
        endDatePanel.add(endLabel);
        endDatePanel.add(endDateChooser);

        // Create a panel for the cost information
        JPanel costPanel = new JPanel(new GridLayout(1, 2));
        costPanel.add(costLabel);
        costPanel.add(costField);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the reservation with the new dates
                Date startDate = startDateChooser.getDate();
                Date endDate = endDateChooser.getDate();
                if (datesAreValid(startDate, endDate)) {
                    try {
                        reservation.setStartDate(getDateWithoutTime(startDate));
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        reservation.setEndDate(getDateWithoutTime(endDate));
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    reservationUpdated = true;
                    reservation.updateReservation(startDate, endDate);
                    JOptionPane.showMessageDialog(EditReservationGUI.this,
                            "Edit completed");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(EditReservationGUI.this,
                            "The start date cannot be after the end date.", "Invalid Dates",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without updating the reservation
                dispose();
            }
        });

        // Add components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(guestPanel);
        contentPane.add(startDatePanel);
        contentPane.add(endDatePanel);
        contentPane.add(costPanel);
        contentPane.add(buttonPanel);
    }

    public boolean isReservationUpdated() {
        return reservationUpdated;
    }


    private boolean datesAreValid(Date startDate, Date endDate) {
        Date today = null, start = null, end = null;
        if (startDate == null || endDate == null) {
            return false;
        }
        try {
            today = getDateWithoutTime(new Date());
            start = getDateWithoutTime(startDate);
            end = getDateWithoutTime(endDate);
        } catch (ParseException ex) {
            System.err.println("Exception in ReservationEditorGUI validDates(): " + ex);
            return false;
        }

        // startDate must be after today or equal to today
        if (!start.after(today) && !start.equals(today)) {
            return false;
        }
        // endDate must be after startDate or equal to startDate
        return end.after(start) || end.equals(start);
    }
}