/**
* A class with the GUI for the Admin options.
* Extends UserOptions class.
*/

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;

public class AdminOptionsGUI extends UserOptions{
	

	private static final JButton viewProfileButton = new JButton("View or Edit Profile");
	private static final JButton createClerkButton = new JButton("Create New Clerk Account");
	private static final JButton viewEditClerkButton = new JButton("View Existing Clerk Accounts");
	private static final JButton getAdminReport = new JButton("Get report");
	private static final JButton Logout = new JButton("logout");
	private static final List<JButton> myButtons = new ArrayList<>();
	
	/**
	 * Static block for initializing buttons and adding them to the button list.
	*/
	static {
		myButtons.add(createClerkButton);
		myButtons.add(viewEditClerkButton);
		myButtons.add(getAdminReport);
		myButtons.add(viewProfileButton);
		myButtons.add(Logout);
	}
	private final Admin myAdmin;
	
	/**
	 * Constructs a new AdminOptionsGUI instance.
	 */
	public AdminOptionsGUI(Admin a){
		super(myButtons, a.getNameFirst());
		myAdmin = a;
		setBounds(400, 100, 400, 300);
		setVisible(true);
	}

	/**
	 * Implements the actionPerformed method inherited from ActionListener to 
	 * make different option buttons work.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).equals(viewProfileButton)){
			new UserProfileGUI(myAdmin);
		} else if(((JButton)e.getSource()).equals(createClerkButton)){
			new UserProfileGUI(new Clerk());
		} else if(((JButton)e.getSource()).equals(viewEditClerkButton)){
			new ViewClerkAccountsGUI(new ClerkTableModel());
            
		} else if(((JButton)e.getSource()).equals(getAdminReport)){
			 //TODO
		}else if(((JButton)e.getSource()).equals(Logout)) {
        	new LogOutGui();
			LogOutGui.displayLogoutPopup(this);
        }
	}
}
