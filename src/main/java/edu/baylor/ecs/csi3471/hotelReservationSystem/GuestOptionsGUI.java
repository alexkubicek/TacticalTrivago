package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GuestOptionsGUI extends UserOptions {
	private static JPanel jp;
	private static final JButton viewResButton = (new JButton("View Existing Reservations"));
	private static final JButton createResButton = (new JButton("Create New Reservation"));
	private static final JButton profileButton = (new JButton("View or Edit Profile"));
	private Guest myGuest;

	public GuestOptionsGUI(Guest g) {
		super(addButtons());
		myGuest = g;
	}

	private static JPanel addButtons() {
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(profileButton);
		jp.add(createResButton);
		jp.add(viewResButton);
		return jp;
	}

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
