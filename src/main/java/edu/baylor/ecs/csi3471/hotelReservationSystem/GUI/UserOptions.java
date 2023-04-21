package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;

public abstract class UserOptions extends JFrame implements ActionListener {

	private JPanel buttonPanel;
	
	public void launch() {
		setTitle("Welcome ");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(50);
		getContentPane().add(verticalStrut);
		
		JLabel optionsLabel = new JLabel("             What would you like to do?");
		getContentPane().add(optionsLabel);
		
		buttonPanel = new JPanel();
		getContentPane().add(buttonPanel);
	}
	
	protected UserOptions(JPanel jp) {
		buttonPanel = jp;
	}
}
