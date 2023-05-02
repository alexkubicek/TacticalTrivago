package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Admin;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;

public class AdminReportGUI extends JDialog implements ActionListener {
    protected static final JButton doneButton = new JButton("Close");
    protected static final JTextArea text = new JTextArea();
    protected static final JScrollPane scrollPane = new JScrollPane(text);
    protected static final JPanel gridPanel = new JPanel();
    protected static final JPanel smallPanel = new JPanel();
  	protected static final JLabel myLabel = new JLabel("Reservations & Payments");
  	

    AdminReportGUI(Admin a) {
        setTitle("Hotel Records");

        text.setText("");
        Hotel.printRecords(text);
        text.setEditable(false);
        
        doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doneButton.addActionListener(this);
        
        myLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
        smallPanel.add(myLabel);
        smallPanel.add(scrollPane);
        smallPanel.add(scrollPane, BorderLayout.CENTER);
        
        gridPanel.setLayout(new BorderLayout());
        gridPanel.add(smallPanel, BorderLayout.CENTER);
        gridPanel.add(doneButton, BorderLayout.SOUTH);

        add(gridPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
