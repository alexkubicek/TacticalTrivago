package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRoomReservationsGUI extends JFrame implements ActionListener {
    JButton editButton, deleteButton;
    LaunchEditor myTableModel;
    public ViewRoomReservationsGUI(LaunchEditor tm){
        setBounds(300, 150, 600, 300);
        myTableModel = tm;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        buttons.add(editButton);
        buttons.add(deleteButton);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        setLayout(new BorderLayout());
        add((Component) myTableModel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((JButton)e.getSource() == editButton){
            System.out.println("launching");
            myTableModel.launch();
        } else if((JButton)e.getSource() == deleteButton){
            //TODO: launch confirm delete popup
        }
    }
}
