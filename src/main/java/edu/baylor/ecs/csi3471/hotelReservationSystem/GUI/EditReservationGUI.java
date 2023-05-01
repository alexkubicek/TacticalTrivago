package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

public class EditReservationGUI extends JFrame {
    private Reservation reservation;
    private JLabel guestLabel, startLabel, endLabel, costLabel;
    private JTextField guestField, startField, endField, costField;
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
        startField = new JTextField(reservation.getStartDate().toString());
        endField = new JTextField(reservation.getEndDate().toString());
        costField = new JTextField(String.format("%.2f", reservation.calculateTotal()));
        costField.setEditable(false);

        // Create buttons for saving and cancelling changes
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");

        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update reservation with new dates
                Date startDate = new Date(startField.getText());
                Date endDate = new Date(endField.getText());
                reservation.updateReservation(startDate, endDate);

                // Set flag indicating that reservation was updated
                reservationUpdated = true;

                // Close the window
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the window without updating the reservation
                dispose();
            }
        });

        // Add components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(guestLabel);
        contentPane.add(guestField);
        contentPane.add(startLabel);
        contentPane.add(startField);
        contentPane.add(endLabel);
        contentPane.add(endField);
        contentPane.add(costLabel);
        contentPane.add(costField);
        contentPane.add(saveButton);
        contentPane.add(cancelButton);
    }

    public boolean isReservationUpdated() {
        return reservationUpdated;
    }
}