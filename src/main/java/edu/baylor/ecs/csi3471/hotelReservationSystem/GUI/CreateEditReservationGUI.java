package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEditReservationGUI extends JFrame implements ActionListener {
    public CreateEditReservationGUI(){}
    public CreateEditReservationGUI(Reservation r){
        //TODO: populate fields with given res info
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: verify date
        //TODO: if reservation is not paid, open paymentGUI
    }
    //TODO: add table, confirm button, date selections (probably reuse KayLynns from before)
}
