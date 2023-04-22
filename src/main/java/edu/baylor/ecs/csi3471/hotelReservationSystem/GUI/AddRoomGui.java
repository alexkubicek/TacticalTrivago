package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;


import javax.swing.*;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.CleanStatus;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomGui {

    private JFrame frame;
    private JTextField roomNumberField;
    private JComboBox<String> bedCountField;
    private JComboBox<String> bedSizeField;
    private JComboBox<String> qualityField;
    private JCheckBox smokingField;
    private JTextField rateField;

    public AddRoomGui() {
        frame = new JFrame("Room Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2));

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberField = new JTextField();
        formPanel.add(roomNumberLabel);
        formPanel.add(roomNumberField);

        JLabel bedCountLabel = new JLabel("Bed Count:");
        bedCountField = new JComboBox<String>(new String[]{"1", "2", "3"});
        formPanel.add(bedCountLabel);
        formPanel.add(bedCountField);

        JLabel bedSizeLabel = new JLabel("Bed Size:");
        bedSizeField = new JComboBox<String>(new String[]{"Twin", "Full", "Queen", "King"});
        formPanel.add(bedSizeLabel);
        formPanel.add(bedSizeField);

        JLabel qualityLabel = new JLabel("Quality Level:");
        qualityField = new JComboBox<String>(new String[]{"Economy", "Standard", "Luxury"});
        formPanel.add(qualityLabel);
        formPanel.add(qualityField);

        JLabel smokingLabel = new JLabel("Smoking Allowed:");
        smokingField = new JCheckBox();
        formPanel.add(smokingLabel);
        formPanel.add(smokingField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNumber = Integer.parseInt((String)roomNumberField.getText());
                int bedCount = Integer.parseInt((String)bedCountField.getSelectedItem());
                BedType bedSize = BedType.valueOf(((String)bedSizeField.getSelectedItem()).toUpperCase());
                QualityLevel quality = QualityLevel.valueOf(((String)qualityField.getSelectedItem()).toUpperCase());
                boolean smoking = smokingField.isSelected();
                Room newRoom = new Room(roomNumber, bedCount, smoking, quality, bedSize);
                Hotel.addRoom(newRoom);
                JOptionPane.showMessageDialog(frame, "Room " + roomNumber + " added successfully.");
                clearFields();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void clearFields() {
        roomNumberField.setText("");
        bedCountField.setSelectedIndex(0);
        bedSizeField.setSelectedIndex(0);
        qualityField.setSelectedIndex(0);
        smokingField.setSelected(false);
    }

    public static void main(String[] args) {
        new AddRoomGui();
    }
}

