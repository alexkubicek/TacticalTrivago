package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;

public abstract class UserOptions extends JFrame implements ActionListener {
	public UserOptions() {
		setTitle("Welcome ");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(50);
		getContentPane().add(verticalStrut);
		
		JLabel optionsLabel = new JLabel("             What would you like to do?");
		getContentPane().add(optionsLabel);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel);}
	
		
}
