package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomizedButtonActions {
	JButton myButton;
	
	public class ConfirmButton extends JButton implements ActionListener {
		
		private static final String text = "Confirm";

		public ConfirmButton() {
			super(text);
		}
		
		public void Clicked() {
			//TODO launch correct GUI
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
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
		}
			
	}

}
