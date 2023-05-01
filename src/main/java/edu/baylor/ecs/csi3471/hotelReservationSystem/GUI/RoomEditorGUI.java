/*
 * A GUI to edit rooms already added in the hotel. 
 * This is not accessible by a guest.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.CleanStatus;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class RoomEditorGUI extends JFrame implements ActionListener {
    //TODO: implement action listener and move variables from method to class as needed
    JCheckBox smokingCheckBox;
    JFormattedTextField bedNumField;
    JComboBox qualityLevelDropDown, bedTypeDropDown, comboBox;
    private Room associatedRoom;
    private boolean isUpdated = false;

    private static final JButton confirmButton = new JButton("Confirm");
    public RoomEditorGUI(Room r) {
        associatedRoom = r;
        confirmButton.addActionListener(this);
        getContentPane().setLayout(null);

        JLabel roomNumLabel = new JLabel("Room Number: ");
        roomNumLabel.setBounds(68, 52, 103, 16);
        getContentPane().add(roomNumLabel);

        JLabel qualityLevelLabel = new JLabel("Quality Level: ");
        qualityLevelLabel.setBounds(68, 80, 103, 16);
        getContentPane().add(qualityLevelLabel);

        JLabel bedSizeLabel = new JLabel("Bed Size: ");
        bedSizeLabel.setBounds(68, 108, 103, 16);
        getContentPane().add(bedSizeLabel);

        JLabel bedNumLabel = new JLabel("Bed Number: ");
        bedNumLabel.setBounds(68, 136, 103, 16);
        getContentPane().add(bedNumLabel);

        smokingCheckBox = new JCheckBox("Smoking");
        smokingCheckBox.setBounds(68, 192, 128, 23);
        smokingCheckBox.setSelected(r.getSmoking());
        getContentPane().add(smokingCheckBox);

        /* changed the field to a label...
           Should not be able to change the room number (room number is how rooms are searched for,
           changing a room number breaks things) -KayLynn
        roomNumField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        roomNumField.setText(String.valueOf(r.getRoomNumber()));
         */
        JLabel roomNumField = new JLabel(String.valueOf(r.getRoomNumber()));
        roomNumLabel.setLabelFor(roomNumField);
        roomNumField.setBounds(168, 47, 28, 26);
        getContentPane().add(roomNumField);

        qualityLevelDropDown = new JComboBox();
        qualityLevelLabel.setLabelFor(qualityLevelDropDown);
        qualityLevelDropDown.setModel(new DefaultComboBoxModel(QualityLevel.values()));
        qualityLevelDropDown.setBounds(168, 76, 123, 27);
        qualityLevelDropDown.setSelectedItem(r.getQuality());
        getContentPane().add(qualityLevelDropDown);

        bedTypeDropDown = new JComboBox();
        bedSizeLabel.setLabelFor(bedTypeDropDown);
        bedTypeDropDown.setModel(new DefaultComboBoxModel(BedType.values()));
        bedTypeDropDown.setBounds(168, 104, 123, 27);
        bedTypeDropDown.setSelectedItem(r.getBedSize());
        getContentPane().add(bedTypeDropDown);

        bedNumField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        bedNumField.setText(String.valueOf(r.getBedCount()));
        bedNumLabel.setLabelFor(bedNumField);
        bedNumField.setBounds(168, 131, 28, 26);
        getContentPane().add(bedNumField);

        JLabel statusLabel = new JLabel("Clean Status: ");
        statusLabel.setBounds(68, 164, 86, 16);
        getContentPane().add(statusLabel);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(CleanStatus.values()));
        comboBox.setSelectedItem(r.getStatus());
        comboBox.setBounds(168, 160, 123, 27);
        getContentPane().add(comboBox);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(100, 250, 100, 30);
        add(confirmButton);
        setBounds(300, 150, 300, 400);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (bedNumField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(RoomEditorGUI.this, "Please fill in all required fields.");
            return;
            // This will return to prompting users to fill in textfields
        }
        associatedRoom.setBedCount(Integer.parseInt(bedNumField.getText()));
        associatedRoom.setQuality((QualityLevel) qualityLevelDropDown.getSelectedItem());
        associatedRoom.setBedSizes((BedType) bedTypeDropDown.getSelectedItem());
        associatedRoom.setClean_status((CleanStatus) comboBox.getSelectedItem());
        associatedRoom.setSmoking(smokingCheckBox.isSelected());
        JOptionPane.showMessageDialog(RoomEditorGUI.this, "Room successfully updated.");
        this.isUpdated = true;
        this.dispose();
    }
    public boolean isRoomUpdated() {
        return isUpdated;
    }
}
