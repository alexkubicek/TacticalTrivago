package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRoomReservationsGUI extends JFrame implements ActionListener {
    JTable table;
    JButton editButton, deleteButton;
    DefaultTableModel myTableModel;
    public ViewRoomReservationsGUI(DefaultTableModel tm){
        myTableModel = tm;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        table = new JTable(tm);
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        //TODO: put on frame and make frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((JButton)e.getSource() == editButton){
            //myTableModel.launch
            //TODO: launch edit gui (we should use polymorphism and call from tableModel)
        } else if((JButton)e.getSource() == deleteButton){
            //TODO: launch confirm delete popup
        }
    }
}
