/**
* A class with the GUI for the Clerk options.
* Extends UserOptions class.
*/

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClerkOptionsGUI  extends UserOptions {

    private static final JButton viewProfileButton = new JButton("View and Edit Profile");
    private static final JButton viewRoomsButton = new JButton("View and Edit Rooms");
    private static final JButton viewReservationsButton = new JButton("View and Edit Reservation");
    private static final JButton createReservationButton = new JButton("Create Reservation");
    private static final JButton createRoomButton = new JButton("Create Room");
    private static final JButton checkinRoom = new JButton("Check in");
    private static final JButton Logout = new JButton("logout");
    private static final List<JButton> myButtons = new ArrayList<>();
    static{
        myButtons.add(createReservationButton);
        myButtons.add(viewReservationsButton);
        myButtons.add(createRoomButton);
        myButtons.add(viewRoomsButton);
        myButtons.add(viewProfileButton);
        myButtons.add(checkinRoom);
        myButtons.add(Logout);

    }
    private Clerk myClerk;

    public ClerkOptionsGUI(Clerk a){
        super(myButtons, a.getNameFirst());
        myClerk = a;
        setSize(400, 300);
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
            // prompt clerk to enter guest information to make a reservation for
            EnterGuestInfoGUI g = new EnterGuestInfoGUI();
            Guest guest = g.getGuest();
            if(guest == null){
                System.err.println("Error in ClerkOptionsGUI: guest to make reservation for is null");
            } else{
                new MakeReservationGUI(guest);
            }
        }else if(((JButton)e.getSource()).equals(checkinRoom)){
        	new CheckInGUI();
        }else if(((JButton)e.getSource()).equals(Logout)) {
        	new LogOutGui();
            try {
                LogOutGui.displayLogoutPopup(this);
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
