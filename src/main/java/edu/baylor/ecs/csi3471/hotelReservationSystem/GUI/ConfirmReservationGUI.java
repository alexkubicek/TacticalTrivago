/**
* This class displays formatted reservation information and confirms that the user wants
* to add it to the Hotel Reservation System.
*/

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmReservationGUI extends JFrame implements ActionListener {
    JButton confirmResButton;
    JButton cancelResButton;
    Reservation associatedReservation;
    JTable upcomingResForGuest;
    public ConfirmReservationGUI(Reservation r, JTable toUpdate) {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        upcomingResForGuest = toUpdate;
        associatedReservation = r;
        setResizable(false);
        setTitle("Confirm Reservation");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JTextPane confirmResText = new JTextPane();
        confirmResText.setEditable(false);
        confirmResText.setText("Reservation for:\n"
                + r.getGuest().getFullName() + "\n"
                + r.getStartDate().toString() + " - " + r.getEndDate().toString() + "\n"
                + "Number of rooms: " + r.getRooms().size() + "\n"
                + "Rate: $" + r.getRate() + "\n"
                + "Total: $" + (r.getRate() * r.getNights()));
        getContentPane().add(confirmResText);

        confirmResButton = new JButton("Confirm");
        confirmResButton.addActionListener(this);
        getContentPane().add(confirmResButton);

        cancelResButton = new JButton("Cancel");
        cancelResButton.addActionListener(this);
        getContentPane().add(cancelResButton);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == cancelResButton) {
            setVisible(false);
        } else if(arg0.getSource() == confirmResButton) {
            setVisible(false);
            Hotel.reservations.add(associatedReservation);
            associatedReservation.getGuest().addUpcomingReservations(associatedReservation);
            //((UpcomingResTableModel)(upcomingResForGuest.getModel())).populate(associatedReservation.getGuest());
        }

    }

}
