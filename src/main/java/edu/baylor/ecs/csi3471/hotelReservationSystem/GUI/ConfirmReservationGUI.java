/**
 * file: ConfirmationReservationGUI
 * author: KayLynn Beard
 *
 * Displays reservation information for a guest
 * about to make a reservation and makes the
 * reservation if confirmed
 */

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmReservationGUI extends JDialog {
    private final Guest guest;
    private final Room room;
    private final Date startDate, endDate;
    private final RoomTableModel table;

    public ConfirmReservationGUI(Guest g, Room r, Date s, Date e, RoomTableModel t){
        guest = g;
        room = r;
        startDate = s;
        endDate = e;
        table = t;

        setTitle("Please Confirm Reservation Information");
        setSize(380, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(getReservationInfo(), SwingConstants.CENTER), BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm reservation");
        panel.add(confirmButton, BorderLayout.SOUTH);
        confirmButton.addActionListener(event -> {
            // reserve room and update table
            Hotel.reserveRoom(r, s, e, g);
            table.updateTable(s, e);
            JOptionPane.showMessageDialog(null, "Reservation made successfully!");
            // close dialog
            dispose();
        });
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String getReservationInfo(){
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd, yyyy");
        String guestInfo = "Reservation for ";
        if (guest.getAccountInformation() == null){
            guestInfo += guest.getFullName() + ":</br>";
        } else{
            guestInfo += guest.toStringForUI() + ":</br>";
        }
        String roomInfo = room.toStringForUI();
        return "<html>" + guestInfo +
                "<br/>-------------------------------------------------------------------<br/>" +
                roomInfo +
                "<br/>-------------------------------------------------------------------<br/>" +
                "Check-in date: " + formatter.format(startDate) +
                "<br> Check-out date: " + formatter.format(endDate) + "</html>";
    }

}
