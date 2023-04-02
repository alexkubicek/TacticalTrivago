package edu.baylor.ecs.csi3471.hotelReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementUI extends JPanel {

    private JTextField roomNumberField;
    private JComboBox<String> bedCountField;
    private JComboBox<String> bedSizeField;
    private JComboBox<String> qualityField;
    private JCheckBox smokingField;
    private JTextField rateField;

    public RoomManagementUI() {
        setLayout(new BorderLayout());

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

        add(formPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Hotel hotel = HotelApp.hotel;
                Integer roomNumber = Integer.valueOf(roomNumberField.getText());
                Integer bedCount = Integer.valueOf((String)bedCountField.getSelectedItem());
                BedType bedSize = BedType.valueOf(((String)bedSizeField.getSelectedItem()).toUpperCase());
                QualityLevel quality = QualityLevel.valueOf(((String)qualityField.getSelectedItem()).toUpperCase());
                boolean smoking = smokingField.isSelected();
                Room newRoom = new Room(roomNumber, bedCount, smoking , quality, bedSize);
                hotel.addRoom(newRoom);
                JOptionPane.showMessageDialog(RoomManagementUI.this, "Room " + roomNumber + " added successfully.");
                clearFields();
            }
        });

        // Add User
        JButton addUserButton = new JButton("Add Clerk/Admin");
        addUserButton.setVisible(true);

        //TextField for First and Last Name
        JTextField firstNameField = new JTextField(20);
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setLabelFor(firstNameField);

        JTextField lastNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setLabelFor(lastNameField);
        //--------------------------------------------------//

        //TextField for admin attributes (ID, Password)
        JTextField adminIdField = new JTextField(20);
        JLabel adminIdLabel = new JLabel("ID:");
        adminIdLabel.setLabelFor(adminIdField);

        JTextField adminPasswordField = new JTextField(20);
        JLabel adminPasswordLabel = new JLabel("Password:");
        adminPasswordLabel.setLabelFor(adminPasswordField);
        // --------------------------------------------//

        //TextField for Generic Account Information
        JTextField accIdField = new JTextField(20);
        JLabel accIdLabel = new JLabel("Admin ID:");
        accIdLabel.setLabelFor(accIdField);

        JTextField accPasswordField = new JTextField(20);
        JLabel accPasswordLabel = new JLabel("Admin Password:");
        accPasswordLabel.setLabelFor(accPasswordField);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JDialog dialog = new JDialog();
                        dialog.setSize(400, 300);
                        dialog.setVisible(true);
                        JPanel panel = new JPanel();

                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        /*
                         *   The UI will prompt for user type and then proceed to corresponding
                         *   request for data input. Planning to perform this on a new dialog
                         *
                         */
                        String[] userTypes = {"Admin", "Clerk"};
                        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);
                        JLabel userTypeLabel = new JLabel("Choose User Type:");
                        userTypeLabel.setLabelFor(userTypeComboBox);
                        panel.add(userTypeLabel);
                        panel.add(userTypeComboBox);

                        userTypeComboBox.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String userType = (String) userTypeComboBox.getSelectedItem();
                                if (userType.equals("Admin")) {
                                    panel.add(firstNameLabel);
                                    panel.add(firstNameField);
                                    panel.add(lastNameLabel);
                                    panel.add(lastNameField);
                                    panel.add(adminIdLabel);
                                    panel.add(adminIdField);
                                    panel.add(adminPasswordLabel);
                                    panel.add(adminPasswordField);

                                } else if (userType.equals("Clerk")) {
                                    panel.remove(firstNameLabel);
                                    panel.remove(firstNameField);
                                    panel.remove(lastNameLabel);
                                    panel.remove(lastNameField);
                                    panel.add(accIdLabel);
                                    panel.add(accIdField);
                                    panel.add(accPasswordLabel);
                                    panel.add(accPasswordField);
                                }
                                dialog.pack();
                            }
                        });


                        dialog.getContentPane().add(panel);
                    }
                });
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void clearFields() {
        roomNumberField.setText("");
        bedCountField.setSelectedIndex(0);
        bedSizeField.setSelectedIndex(0);
        qualityField.setSelectedIndex(0);
        smokingField.setSelected(false);
    }
}
