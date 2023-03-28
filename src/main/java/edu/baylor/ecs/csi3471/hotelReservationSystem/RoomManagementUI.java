package edu.baylor.ecs.csi3471.hotelReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementUI {

    private JFrame frame;
    private JTextField roomNumberField;
    private JComboBox<String> bedCountField;
    private JComboBox<String> bedSizeField;
    private JComboBox<String> qualityField;
    private JCheckBox smokingField;
    private JTextField rateField;

    public RoomManagementUI() {
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

        JLabel rateLabel = new JLabel("Room Rate:");
        rateField = new JTextField();
        formPanel.add(rateLabel);
        formPanel.add(rateField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer roomNumber = Integer.valueOf(roomNumberField.getText());
                Integer bedCount = Integer.valueOf((String)bedCountField.getSelectedItem());
                BedType bedSize = BedType.valueOf((String)bedSizeField.getSelectedItem());
                QualityLevel quality = QualityLevel.valueOf((String) qualityField.getSelectedItem());
                boolean smoking = smokingField.isSelected();
                double rate = Double.parseDouble(rateField.getText());
                Room newRoom = new Room(roomNumber, bedCount, smoking , quality, bedSize);
                Hotel hotel = new Hotel();
                hotel.addRoom(newRoom);
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
        rateField.setText("");
    }

    public static void main(String[] args) {
        new RoomManagementUI();
    }
}
