package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomizedButtonActions {
	JButton myButton;
	
	public JButton getButton() { return myButton;}
	
	public class ConfirmButton extends JButton implements ActionListener {
		
		private static final String text = "Confirm";

		public ConfirmButton() {
			super(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO launch
			
		}
		
	}
	
	public class CancelButton extends JButton implements ActionListener{
		private static final String text = "Cancel";

		public CancelButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class DeleteButton extends JButton implements ActionListener{
		private static final String text = "Delete";

		public DeleteButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class LoginButton extends JButton implements ActionListener{
		private static final String text = "Login";

		public LoginButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class CreateAccountButton extends JButton implements ActionListener{
		private static final String text = "Create Account";

		public CreateAccountButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class ResetPasswordButton extends JButton implements ActionListener{
		private static final String text = "Reset Password";

		public ResetPasswordButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionViewResButton extends JButton implements ActionListener{
		private static final String text = "View and edit existing reservations";

		public OptionViewResButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionNewResButton extends JButton implements ActionListener{
		private static final String text = "Create new reservation";

		public OptionNewResButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionViewProfileButton extends JButton implements ActionListener{
		private static final String text = "View and edit profile";

		public OptionViewProfileButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionViewRoomsButton extends JButton implements ActionListener{
		private static final String text = "View and edit existing rooms";

		public OptionViewRoomsButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionCreateRoomButton extends JButton implements ActionListener{
		private static final String text = "Create new room";

		public OptionCreateRoomButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionViewClerksButton extends JButton implements ActionListener{
		private static final String text = "View and edit existing Clerk accounts";

		public OptionViewClerksButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionCreateClerkButton extends JButton implements ActionListener{
		private static final String text = "Create new Clerk account";

		public OptionCreateClerkButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class OptionRecordsButton extends JButton implements ActionListener{
		private static final String text = "View and edit profile";

		public OptionRecordsButton() {
			super(text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public CustomizedButtonActions(String type) {
		switch(type.toLowerCase()) {
			case "confirm":
				myButton = new ConfirmButton();
				break;
			case "cancel":
				myButton = new CancelButton();
				break;
			case "delete":
				myButton = new DeleteButton();
				break;
			case "login":
				myButton = new LoginButton();
				break;
			case "create":
				myButton = new CreateAccountButton();
				break;
			case "reset":
				myButton = new ResetPasswordButton();
				break;
			case "viewres":
				myButton = new OptionViewResButton();
				break;
			case "newres":
				myButton = new OptionNewResButton();
				break;
			case "profile":
				myButton = new OptionViewProfileButton();
				break;
			case "viewroom":
				myButton = new OptionViewRoomsButton();
				break;
			case "newroom":
				myButton = new OptionCreateRoomButton();
				break;
			case "viewclerk":
				myButton = new OptionViewClerksButton();
				break;
			case "createclerk":
				myButton = new OptionCreateClerkButton();
				break;
			case "records":
				myButton = new OptionRecordsButton();
				break;
		}
			
	}

}
