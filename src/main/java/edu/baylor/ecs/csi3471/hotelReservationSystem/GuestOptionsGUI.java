package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GuestOptionsGUI extends UserOptions {
	private static JPanel jp;
	private static final JButton viewResButton = (new CustomizedButtonActions("viewres")).getButton();
	private static final JButton createResButton = (new CustomizedButtonActions("createres")).getButton();
	private static final JButton profileButton = (new CustomizedButtonActions("profile")).getButton();
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
}
