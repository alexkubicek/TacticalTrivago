package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewClerkAccountsGUI extends JFrame implements ActionListener {
    JButton editButton, deleteButton;
    LaunchEditor myTableModel;
    public ViewClerkAccountsGUI(LaunchEditor tm){
        setBounds(300, 150, 600, 410);
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
        int selectedRow = myTableModel.getTable().getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "No clerk selected");
            return;
        }

        Object index = myTableModel.getTable().getValueAt(selectedRow, myTableModel.getTable().getSelectedColumn());

        if ((JButton) e.getSource() == editButton) {
            myTableModel.launch();
        } else if ((JButton) e.getSource() == deleteButton) {
            myTableModel.deleteSelected();
        }
    }

}

