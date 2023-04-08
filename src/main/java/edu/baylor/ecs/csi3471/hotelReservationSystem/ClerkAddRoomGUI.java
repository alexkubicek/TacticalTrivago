package edu.baylor.ecs.csi3471.hotelReservationSystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ClerkAddRoomGUI extends JFrame {
    //TODO: implement action listener and move variables from method to class as needed
    public ClerkAddRoomGUI() {
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

        JCheckBox smokingCheckBox = new JCheckBox("Smoking");
        smokingCheckBox.setBounds(68, 192, 128, 23);
        getContentPane().add(smokingCheckBox);

        JFormattedTextField roomNumField = new JFormattedTextField();
        roomNumLabel.setLabelFor(roomNumField);
        roomNumField.setBounds(168, 47, 28, 26);
        getContentPane().add(roomNumField);

        JComboBox qualityLevelDropDown = new JComboBox();
        qualityLevelLabel.setLabelFor(qualityLevelDropDown);
        qualityLevelDropDown.setModel(new DefaultComboBoxModel(QualityLevel.values()));
        qualityLevelDropDown.setBounds(168, 76, 123, 27);
        getContentPane().add(qualityLevelDropDown);

        JComboBox bedTypeDropDown = new JComboBox();
        bedSizeLabel.setLabelFor(bedTypeDropDown);
        bedTypeDropDown.setModel(new DefaultComboBoxModel(BedType.values()));
        bedTypeDropDown.setBounds(168, 104, 123, 27);
        getContentPane().add(bedTypeDropDown);

        JFormattedTextField bedNumField = new JFormattedTextField();
        bedNumLabel.setLabelFor(bedNumField);
        bedNumField.setBounds(168, 131, 28, 26);
        getContentPane().add(bedNumField);

        JLabel statusLabel = new JLabel("Clean Status: ");
        statusLabel.setBounds(68, 164, 86, 16);
        getContentPane().add(statusLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(CleanStatus.values()));
        comboBox.setBounds(168, 160, 123, 27);
        getContentPane().add(comboBox);

    }

}
