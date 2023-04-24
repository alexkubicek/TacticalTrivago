//package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;
//
//import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
//import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.CreditCard;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class PaymentGUI {
//    private static final JLabel nameLabel = new JLabel("Name on card:");
//    private static final JTextField nameField = new JTextField();
//    private static final JLabel cardNumLabel = new JLabel("Card Number:");
//    private static final JFormattedTextField cardNumField = new JFormattedTextField();
//    private static final JLabel expirationLabel = new JLabel("expiration");
//    private static final JFormattedTextField expirationField = new JFormattedTextField();
//    private static final JLabel cvvLabel = new JLabel("CVV:");
//    private static final JFormattedTextField cvvField = new JFormattedTextField();
//
//    private Guest guest;

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class PaymentGUI extends JFrame implements ActionListener {
    private static final JLabel nameLabel = new JLabel("Name on card:");
    private static final JTextField nameField = new JTextField();
    private static final JLabel cardNumLabel = new JLabel("Card Number:");
    private static final JTextField cardNumField = new JTextField();
    private static final JLabel expirationLabel = new JLabel("expiration");
    private static final JFormattedTextField expirationField = new JFormattedTextField();
    private static final JLabel cvvLabel = new JLabel("CVV:");
    private static final JTextField cvvField = new JTextField(3);

    private JTextField buildingNumField, zipCodeField, streetField, cityField;

    private JTextField cardNumberField1, cardNumberField2, cardNumberField3, cardNumberField4;
    private JComboBox<String> stateComboBox;
    private JButton saveButton, cancelButton;
    private Guest guest;

    public PaymentGUI(Guest guest) {
        this.guest = guest;

        this.setTitle("Payment Information");
        this.setLayout(new BorderLayout());
        this.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        JLabel cardNumLabel = new JLabel("Card Number: ");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(cardNumLabel, c);

        cardNumField.setColumns(16);
        cardNumField.setDocument(new JTextFieldLimit(16));
        c.gridx = 1;
        c.gridy = 0;
        panel.add(cardNumField, c);

        JLabel cvvLabel = new JLabel("CVV: ");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(cvvLabel, c);

        cvvField.setColumns(3);
        cvvField.setDocument(new JTextFieldLimit(3));
        c.gridx = 1;
        c.gridy = 1;
        panel.add(cvvField, c);

        // Expiration Date label and combo box
        JLabel expirationLabel = new JLabel("Expiration Date: ");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(expirationLabel, c);

        JComboBox<String> monthComboBox = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        JComboBox<String> yearComboBox = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
        JPanel expirationPanel = new JPanel();
        expirationPanel.add(monthComboBox);
        expirationPanel.add(new JLabel("/"));
        expirationPanel.add(yearComboBox);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(expirationPanel, c);

        // Name on Card label and text field
        JLabel nameLabel = new JLabel("Name on Card: ");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(nameLabel, c);

        //nameField = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        panel.add(nameField, c);

        // Billing Address label
        JLabel billingAddressLabel = new JLabel("Billing Address");
        billingAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        c.gridx = 0;
        c.gridy = 4;
        panel.add(billingAddressLabel, c);

        // Building Number label and text field
        JLabel buildingNumLabel = new JLabel("Building Number: ");
        c.gridx = 0;
        c.gridy = 5;
        panel.add(buildingNumLabel, c);

        buildingNumField = new JTextField();
        c.gridx = 1;
        c.gridy = 5;
        panel.add(buildingNumField, c);

        // Street label and text field
        JLabel streetLabel = new JLabel("Street: ");
        streetField = new JTextField();
        c.gridx = 1;
        c.gridy = 6;
        panel.add(streetField, c);

        // City label and text field
        JLabel cityLabel = new JLabel("City: ");
        c.gridx = 0;
        c.gridy = 7;
        panel.add(cityLabel, c);

        cityField = new JTextField();
        c.gridx = 1;
        c.gridy = 7;
        panel.add(cityField, c);

        // State label and combo box
        JLabel stateLabel = new JLabel("State: ");
        c.gridx = 0;
        c.gridy = 8;
        panel.add(stateLabel, c);

        stateComboBox = new JComboBox<>(new DefaultComboBoxModel<>(State.getStatesArray()));
        c.gridx = 1;
        c.gridy = 8;
        panel.add(stateComboBox, c);

        // Zip Code label and text field
        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        c.gridx = 0;
        c.gridy = 9;
        panel.add(zipCodeLabel, c);

        zipCodeField = new JTextField();
        c.gridx = 1;
        c.gridy = 9;
        panel.add(zipCodeField, c);

        // Save and Cancel Buttons
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 10;
        panel.add(saveButton, c);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        c.gridx = 1;
        c.gridy = 10;
        panel.add(cancelButton, c);
        
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            // Get the data from the fields
            String cardNum = cardNumField.getText();
            String cvv = cvvField.getText();
            Date expirationDate = Date.valueOf(expirationField.getText());
            String name = nameField.getText();
            String buildingNum = buildingNumField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            State state = (State) stateComboBox.getSelectedItem();
            String zipCode = zipCodeField.getText();

            // Create the Address and CreditCard objects
            Address billingAddress = new Address(Integer.parseInt(buildingNum), Integer.parseInt(zipCode), street, city, state);
            CreditCard creditCard = new CreditCard(expirationDate, Integer.parseInt(cardNum), Integer.parseInt(cvv), billingAddress);

            // Set the CreditCard object to the guest's payment info
            guest.setPaymentInfo(creditCard);

            // Close the PaymentGUI
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            // Close the PaymentGUI without saving
            this.dispose();
        }
    }
}