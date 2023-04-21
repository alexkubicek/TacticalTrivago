package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;

import java.awt.event.ActionEvent;

public class ClerkOptionsGUI  extends UserOptions {
    private static JPanel jp;

    private static final JButton viewProfileButton = new JButton("View and Edit Profile");
    private static final JButton viewRoomsButton = new JButton("View and Edit Rooms");
    private static final JButton viewReservationsButton = new JButton("View and Edit Reservation");
    private static final JButton createReservationButton = new JButton("Create Reservation");
    private static final JButton createRoomButton = new JButton("Create Room");

    private Clerk myClerk;

    ClerkOptionsGUI(Clerk a){
        super(addButtons());
        myClerk = a;

    }

    private static JPanel addButtons() {
        jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.add(viewProfileButton);
        jp.add(viewReservationsButton);
        jp.add(createReservationButton);
        jp.add(viewRoomsButton);
        jp.add(createRoomButton);
        return jp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).equals(viewRoomsButton)){
            //TODO
        } else if(((JButton)e.getSource()).equals(viewProfileButton)){
            //TODO
        } else if(((JButton)e.getSource()).equals(viewReservationsButton)){
            //TODO
        } else if(((JButton)e.getSource()).equals(createRoomButton)){
            //TODO
        } else if(((JButton)e.getSource()).equals(createReservationButton)){
            //TODO
        }
    }
}
