package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

public abstract class UserOptions extends JFrame implements ActionListener {

	protected JPanel buttonPanel;
	
	protected UserOptions(JPanel jp, String name) {
		System.out.println("recieved name " + name);
		buttonPanel = jp;
		buttonPanel.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Welcome " + name);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(50);
		getContentPane().add(verticalStrut);

		JLabel optionsLabel = new JLabel("What would you like to do?");
		getContentPane().add(optionsLabel);

		getContentPane().add(buttonPanel);
	}
}
