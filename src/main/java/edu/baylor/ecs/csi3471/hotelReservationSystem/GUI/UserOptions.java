package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

public abstract class UserOptions extends JFrame implements ActionListener {

	protected JPanel buttonPanel;
	
	protected UserOptions(List<JButton> buttonList, String name) {
		JLabel optionsLabel = new JLabel("What would you like to do?");
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(optionsLabel);
		buttonList.forEach(b->{
			b.addActionListener(this);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonPanel.add(b);
		});
		buttonPanel.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Welcome " + name);
		setLayout(new BorderLayout());

		add(buttonPanel, BorderLayout.CENTER);
	}
}
