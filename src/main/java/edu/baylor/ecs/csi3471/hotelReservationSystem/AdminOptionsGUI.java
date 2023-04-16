package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminOptionsGUI extends UserOptions{
	
	private static JPanel jp;
	
	private static final JButton viewProfileButton = new CustomizedButtonActions("profile").getButton();
	private static final JButton createClerkButton = new CustomizedButtonActions("createclerk").getButton();
	private static final JButton viewEditClerkButton = new CustomizedButtonActions("viewclerk").getButton();

	private Admin myAdmin = null;
	
	AdminOptionsGUI(Admin a){
		super(addButtons());	
		myAdmin = a;

	}

	private static JPanel addButtons() {
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(viewProfileButton);
		jp.add(createClerkButton);
		jp.add(viewEditClerkButton);
		
		return jp;
	}
	
	
}
