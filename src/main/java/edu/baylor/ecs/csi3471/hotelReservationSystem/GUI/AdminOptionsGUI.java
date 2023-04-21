package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;

public class AdminOptionsGUI extends UserOptions{
	

	private static final JButton viewProfileButton = new JButton("View or Edit Profile");
	private static final JButton createClerkButton = new JButton("Create New Clerk Account");
	private static final JButton viewEditClerkButton = new JButton("View Existing Clerk Accounts");
	private static final JButton getAdminReport = new JButton("Get report");

	private Admin myAdmin;
	
	public AdminOptionsGUI(Admin a){
		super(addButtons(), a.getNameFirst());
		myAdmin = a;
		setBounds(400, 100, 400, 300);
		setVisible(true);
	}

	private static JPanel addButtons() {
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(viewProfileButton);
		jp.add(createClerkButton);
		jp.add(viewEditClerkButton);
		jp.add(getAdminReport);

		jp.setVisible(true);
		return jp;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).equals(viewProfileButton)){
			UserProfileGUI profile = new UserProfileGUI(myAdmin);
		} else if(((JButton)e.getSource()).equals(createClerkButton)){
			//TODO
		} else if(((JButton)e.getSource()).equals(viewEditClerkButton)){
			//TODO
		} else if(((JButton)e.getSource()).equals(getAdminReport)){
			 //TODO
		}
	}
}
