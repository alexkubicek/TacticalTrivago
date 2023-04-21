package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.CleanStatus;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomEditorGUI extends JFrame implements ActionListener {
    //TODO: implement action listener and move variables from method to class as needed
    JCheckBox smokingCheckBox;
    JFormattedTextField roomNumField, bedNumField;
    JComboBox qualityLevelDropDown, bedTypeDropDown, comboBox;
    private static final JButton confirmButton = new JButton("Confirm");
    public RoomEditorGUI() {
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
        getContentPane().add(smokingCheckBox);

        roomNumField = new JFormattedTextField();
        roomNumLabel.setLabelFor(roomNumField);
        roomNumField.setBounds(168, 47, 28, 26);
        getContentPane().add(roomNumField);

        qualityLevelDropDown = new JComboBox();
        qualityLevelLabel.setLabelFor(qualityLevelDropDown);
        qualityLevelDropDown.setModel(new DefaultComboBoxModel(QualityLevel.values()));
        qualityLevelDropDown.setBounds(168, 76, 123, 27);
        getContentPane().add(qualityLevelDropDown);

        bedTypeDropDown = new JComboBox();
        bedSizeLabel.setLabelFor(bedTypeDropDown);
        bedTypeDropDown.setModel(new DefaultComboBoxModel(BedType.values()));
        bedTypeDropDown.setBounds(168, 104, 123, 27);
        getContentPane().add(bedTypeDropDown);

        bedNumField = new JFormattedTextField();
        bedNumLabel.setLabelFor(bedNumField);
        bedNumField.setBounds(168, 131, 28, 26);
        getContentPane().add(bedNumField);

        JLabel statusLabel = new JLabel("Clean Status: ");
        statusLabel.setBounds(68, 164, 86, 16);
        getContentPane().add(statusLabel);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(CleanStatus.values()));
        comboBox.setBounds(168, 160, 123, 27);
        getContentPane().add(comboBox);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO What else am I checking for here?
        if (roomNumField.getText().isEmpty() ||
                bedNumField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(RoomEditorGUI.this, "Please fill in all required fields.");
            return;
            // This will return to prompting users to fill in textfields
        }
    }
}
