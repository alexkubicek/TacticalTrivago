package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;

public class GuestOptionsGUI extends UserOptions {
	private static final JButton viewResButton = (new JButton("View Existing Reservations"));
	private static final JButton createResButton = (new JButton("Create New Reservation"));
	private static final JButton profileButton = (new JButton("View or Edit Profile"));
	private static final List<JButton> myButtons = new ArrayList<>();

	static {
		myButtons.add(createResButton);
		myButtons.add(viewResButton);
		myButtons.add(profileButton);
	}
	private Guest myGuest;

	public GuestOptionsGUI(Guest g) {
		super(myButtons, g.getNameFirst());
		myGuest = g;
		setBounds(400, 200, 400, 300);
		this.add(buttonPanel);
		buttonPanel.setVisible(true);
		setVisible(true);}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(Objects.equals(b, viewResButton)) {
			//TODO: new ViewRoomReservationsGUI(UpcomingResTableModel, myGuest)
		} else if(Objects.equals(b, createResButton)) {
			//TODO: new CreateEditReservation(myGuest)
		} else if(Objects.equals(b, profileButton)){
			//TODO: new UserProfileGUI(myGuest)
		}
	}
}
