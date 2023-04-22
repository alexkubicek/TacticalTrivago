package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ClerkOptionsGUI  extends UserOptions {

    private static final JButton viewProfileButton = new JButton("View and Edit Profile");
    private static final JButton viewRoomsButton = new JButton("View and Edit Rooms");
    private static final JButton viewReservationsButton = new JButton("View and Edit Reservation");
    private static final JButton createReservationButton = new JButton("Create Reservation");
    private static final JButton createRoomButton = new JButton("Create Room");
    private static final List<JButton> myButtons = new ArrayList<>();
    static{
        myButtons.add(createReservationButton);
        myButtons.add(viewReservationsButton);
        myButtons.add(createRoomButton);
        myButtons.add(viewRoomsButton);
        myButtons.add(viewProfileButton);
    }
    private Clerk myClerk;

    public ClerkOptionsGUI(Clerk a){
        super(myButtons, a.getNameFirst());
        myClerk = a;
        setBounds(400, 200, 400, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).equals(viewRoomsButton)){
            new ViewRoomReservationsGUI(new RoomTableModel());
        } else if(((JButton)e.getSource()).equals(viewProfileButton)){
            new UserProfileGUI(myClerk);
        } else if(((JButton)e.getSource()).equals(viewReservationsButton)){
            new ViewRoomReservationsGUI(new ReservationTableModel());
        } else if(((JButton)e.getSource()).equals(createRoomButton)){
        	new AddRoomGui();
        } else if(((JButton)e.getSource()).equals(createReservationButton)){
            Reservation myNewReservation = new Reservation();
            new ReservationEditorGUI(myNewReservation);
            //TODO: need a way to not add it if reservation is not properly created
            Hotel.reservations.add(myNewReservation);
        }
    }
}
