/**
* A class with the GUI for the Guest options.
* Extends UserOptions class.
*/
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;

public class GuestOptionsGUI extends UserOptions {
	private static final JButton viewResButton = (new JButton("View Existing Reservations"));
	private static final JButton createResButton = (new JButton("Create New Reservation"));
	private static final JButton profileButton = (new JButton("View or Edit Profile"));
	private static final JButton paymentButton = new JButton("Payment Information");
	private static final JButton Logout = new JButton("logout");
	private static final List<JButton> myButtons = new ArrayList<>();

	static {
		myButtons.add(createResButton);
		myButtons.add(viewResButton);
		myButtons.add(profileButton);
		myButtons.add(paymentButton);
		myButtons.add(Logout);
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
			new ViewRoomReservationsGUI(new UpcomingResTableModel(myGuest));
		} else if(Objects.equals(b, createResButton)) {
			new MakeReservationGUI(myGuest);
		} else if(Objects.equals(b, profileButton)){
			new UserProfileGUI(myGuest);
		}else if (Objects.equals(b, paymentButton)) {
			new PaymentGUI(myGuest);
		}else if(((JButton)e.getSource()).equals(Logout)) {
        	new LogOutGui();
			LogOutGui.displayLogoutPopup(this);
        }
	}
}
