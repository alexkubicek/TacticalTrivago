package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ClerkGUI extends JFrame implements ActionListener {
    private JTable roomTable;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTable resTable;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField profFirstNameField;
    private JTextField profLastNameField;
    private JFormattedTextField roomNumField;
    private JFormattedTextField bedNumField;
    private JComboBox bedTypeBox;
    private JComboBox qualLevelBox;
    private JButton addRoomButton;
    private JButton editRoomButton;
    private JFormattedTextField startDateField;
    private JButton newResButton;
    private JButton editResButton;
    private JButton cancelResButton;
    private JButton updateProfileButton;
    private JButton roomFilterButton;
    private Clerk associatedClerk;
    private JButton resFilterButton;
    public ClerkGUI(String name, User u) {
        associatedClerk = (Clerk)u;
        setResizable(false);
        setTitle("Welcome Clerk " + name);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel roomsTab = new JPanel();
        tabbedPane.addTab("Rooms", null, roomsTab, null);
        roomsTab.setLayout(null);

        roomFilterButton = new JButton("Filter");
        roomFilterButton.addActionListener(this);
        roomFilterButton.setBounds(6, 6, 61, 16);
        roomsTab.add(roomFilterButton);

        JLabel roomNumLabel = new JLabel("Room Number: ");
        roomNumLabel.setBounds(6, 34, 103, 16);
        roomsTab.add(roomNumLabel);

        roomNumField = new JFormattedTextField();
        roomNumField.setBounds(108, 29, 38, 26);
        roomsTab.add(roomNumField);

        JLabel bedNumLabel = new JLabel("Bed Number: ");
        bedNumLabel.setBounds(6, 62, 103, 16);
        roomsTab.add(bedNumLabel);

        bedNumField = new JFormattedTextField();
        bedNumField.setBounds(108, 57, 38, 26);
        roomsTab.add(bedNumField);

        JLabel bedTypeLabel = new JLabel("Bed Size:");
        bedTypeLabel.setBounds(6, 90, 61, 16);
        roomsTab.add(bedTypeLabel);

        bedTypeBox = new JComboBox();
        bedTypeBox.setModel(new DefaultComboBoxModel(BedType.values()));
        bedTypeBox.setBounds(15, 107, 131, 27);
        roomsTab.add(bedTypeBox);

        JLabel roomQualityLabel = new JLabel("Room Quality:");
        roomQualityLabel.setBounds(6, 135, 103, 16);
        roomsTab.add(roomQualityLabel);

        qualLevelBox = new JComboBox();
        qualLevelBox.setModel(new DefaultComboBoxModel(QualityLevel.values()));
        qualLevelBox.setBounds(15, 152, 131, 27);
        roomsTab.add(qualLevelBox);

        addRoomButton = new JButton("Add");
        addRoomButton.setBounds(-1, 177, 80, 29);
        roomsTab.add(addRoomButton);

        editRoomButton = new JButton("Edit");
        editRoomButton.setBounds(66, 177, 80, 29);
        roomsTab.add(editRoomButton);

        JScrollPane roomScrollPane = new JScrollPane();
        roomScrollPane.setBounds(159, 6, 402, 200);
        roomsTab.add(roomScrollPane);

        roomTable = new JTable(new RoomTableModel());
        roomTable.setForeground(Color.GRAY);
        roomScrollPane.setViewportView(roomTable);

        JPanel reservationsTab = new JPanel();
        tabbedPane.addTab("Reservations", null, reservationsTab, null);
        reservationsTab.setLayout(null);

        resFilterButton = new JButton("Filter:");
        resFilterButton.setBounds(6, 6, 61, 16);
        reservationsTab.add(resFilterButton);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(6, 34, 90, 16);
        reservationsTab.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(6, 62, 90, 16);
        reservationsTab.add(lastNameLabel);

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(6, 90, 90, 16);
        reservationsTab.add(startDateLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(83, 29, 130, 26);
        reservationsTab.add(firstNameField);
        firstNameField.setColumns(10);

        lastNameField = new JTextField();
        lastNameField.setBounds(83, 57, 130, 26);
        reservationsTab.add(lastNameField);
        lastNameField.setColumns(10);

        startDateField = new JFormattedTextField();
        startDateField.setBounds(83, 85, 130, 26);
        reservationsTab.add(startDateField);

        newResButton = new JButton("New Reservation");
        newResButton.setBounds(6, 118, 140, 29);
        reservationsTab.add(newResButton);

        editResButton = new JButton("Edit");
        editResButton.setBounds(6, 146, 140, 29);
        reservationsTab.add(editResButton);

        cancelResButton = new JButton("Cancel");
        cancelResButton.setBounds(6, 174, 140, 29);
        reservationsTab.add(cancelResButton);

        JScrollPane resScrollPane = new JScrollPane();
        resScrollPane.setBounds(228, 6, 333, 200);
        reservationsTab.add(resScrollPane);

        resTable = new JTable(new ReservationTableModel());
        resScrollPane.setViewportView(resTable);

        JPanel editProfilePanel = new JPanel();
        tabbedPane.addTab("Edit Profile", null, editProfilePanel, null);
        editProfilePanel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(0, 1, 283, 42);
        usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        editProfilePanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(283, 1, 145, 42);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        editProfilePanel.add(usernameField);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(0, 43, 283, 42);
        passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        editProfilePanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(283, 43, 145, 42);
        editProfilePanel.add(passwordField);

        JLabel profFirstNameLabel = new JLabel("First Name: ");
        profFirstNameLabel.setBounds(0, 85, 283, 42);
        profFirstNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        editProfilePanel.add(profFirstNameLabel);

        profFirstNameField = new JTextField();
        profFirstNameField.setBounds(283, 85, 145, 42);
        editProfilePanel.add(profFirstNameField);
        profFirstNameField.setColumns(10);

        JLabel profLastNameLabel = new JLabel("Last Name: ");
        profLastNameLabel.setBounds(0, 127, 283, 42);
        profLastNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        editProfilePanel.add(profLastNameLabel);

        profLastNameField = new JTextField();
        profLastNameField.setBounds(283, 127, 145, 42);
        editProfilePanel.add(profLastNameField);
        profLastNameField.setColumns(10);

        updateProfileButton = new JButton("Update");
        updateProfileButton.setBounds(283, 169, 145, 37);
        editProfilePanel.add(updateProfileButton);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if(a.getSource() == roomFilterButton) {
            ((RoomTableModel)roomTable.getModel()).filter((QualityLevel)qualLevelBox.getSelectedItem(), Integer.valueOf(bedNumField.getText()), (BedType)bedTypeBox.getSelectedItem(), null, null, null);
        } else if(a.getSource() == resFilterButton){
            Date d = new Date(); //todo: parse startDateField and assign here
            ((ReservationTableModel)resTable.getModel()).filter(firstNameField.getText(), lastNameField.getText(), d);
        } else if(a.getSource() == addRoomButton){
            setVisible(false);
            ClerkAddRoomGUI carg = new ClerkAddRoomGUI();
            carg.setVisible(true);
        } else if(a.getSource() == editRoomButton){
            //open add room gui with fields filled with current values
        } else if(a.getSource() == newResButton){
            //todo: create new res GUI with table of rooms and filters
        } else if(a.getSource() == editResButton){
            //todo: open new Reservation GUI with filters set to current res values
        } else if(a.getSource() == cancelResButton){
            //TODO: confirm cancel reservation GUI
        } else if(a.getSource() == updateProfileButton){
            if(!Objects.equals(usernameField.getText(), associatedClerk.getAccountUsername())){
                if(Hotel.isUsernameUnique(usernameField.getText())){
                    associatedClerk.setAccountUsername(usernameField.getText());
                } else {
                    UsernameTakenPopupGUI utpg = new UsernameTakenPopupGUI();
                }
            }
            associatedClerk.setAccountPassword(Arrays.toString(passwordField.getPassword()));
            associatedClerk.setNameFirst(profFirstNameField.getText());
            associatedClerk.setNameLast(profLastNameField.getText());
        }
    }
}
