package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminOptionsGUI extends UserOptions{
	
	private static JPanel jp;
	
	private static final JButton viewProfileButton = new JButton("View or Edit Profile");
	private static final JButton createClerkButton = new JButton("Create New Clerk Account");
	private static final JButton viewEditClerkButton = new JButton("View Existing Clerk Accounts");

	private Admin myAdmin;
	
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).equals(viewProfileButton)){
			//TODO
		} else if(((JButton)e.getSource()).equals(createClerkButton)){
			//TODO
		} else if(((JButton)e.getSource()).equals(viewEditClerkButton)){
			//TODO
		}
	}
}
