///**
//* An abstract class with the GUI for user options.
//* It extends the JFrame class and implements the ActionListener interface
//* to get button click information.
//*/
//package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.*;
//import java.util.List;
//
//import javax.swing.*;
//
//public abstract class UserOptions extends JFrame implements ActionListener {
//
//	protected JPanel buttonPanel;
//
//	protected UserOptions(List<JButton> buttonList, String name) {
//		JLabel optionsLabel = new JLabel("What would you like to do?");
//		buttonPanel = new JPanel();
//		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
//		buttonPanel.add(optionsLabel);
//		buttonList.forEach(b->{
//			b.addActionListener(this);
//			b.setAlignmentX(Component.CENTER_ALIGNMENT);
//			buttonPanel.add(b);
//		});
//		buttonPanel.setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setTitle("Welcome " + name);
//		setLayout(new BorderLayout());
//
//		add(buttonPanel, BorderLayout.CENTER);
//	}
//}
/**
 * An abstract class with the GUI for user options.
 * It extends the JFrame class and implements the ActionListener interface
 * to get button click information.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

import javax.swing.*;

public abstract class UserOptions extends JFrame implements ActionListener {

	protected JPanel buttonPanel;

	protected UserOptions(List<JButton> buttonList, String name) {
		JLabel optionsLabel = new JLabel("What would you like to do?");
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		labelPanel.add(optionsLabel);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonList.forEach(b -> {
			b.addActionListener(this);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonPanel.add(b);
		});
		buttonPanel.setVisible(true);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(labelPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Welcome " + name);
		setContentPane(mainPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				new LogOutGui();
				LogOutGui.displayLogoutPopup(e.getWindow());
			}
		});
	}
}
