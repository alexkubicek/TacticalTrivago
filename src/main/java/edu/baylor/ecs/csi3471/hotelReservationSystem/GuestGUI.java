package edu.baylor.ecs.csi3471.hotelReservationSystem;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.time.Month;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class GuestGUI extends JFrame implements ActionListener {
    private Guest associatedGuest;
    private JTable makeResTable;
    private JTable upcomingResTable;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameFirstField;
    private JTextField nameLastField;
    private JCheckBox isCorpCheckBox;
    private JComboBox<String> sdYearBox;
    private JComboBox<Month> sdMonthBox;
    private JComboBox<String> sdDayBox;
    private JComboBox<String> edYearBox;
    private JComboBox<Month> edMonthBox;
    private JComboBox<String> edDayBox;
    private JComboBox<QualityLevel> roomQualityDropDown;
    private JComboBox<BedType> bedSizeDropDown;
    private JCheckBox smokingCheckBox;
    private JFormattedTextField bedNumField;
    private JButton makeReservationButton;
    private JButton mrFilterButton;
    private JButton editResButton;
    private JButton cancelResButton;
    private JButton updateProfileButton;
    public GuestGUI(String name, User u) {
        associatedGuest = (Guest)u;
        setResizable(false);
        setTitle("Welcome " + name);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel makeReservationTab = new JPanel();
        tabbedPane.addTab("Make Reservations", null, makeReservationTab, null);
        makeReservationTab.setLayout(null);

        JLabel qualityFilterLabel = new JLabel("Room Quality:");
        qualityFilterLabel.setBounds(6, 6, 106, 16);
        makeReservationTab.add(qualityFilterLabel);

        roomQualityDropDown = new JComboBox<>();
        roomQualityDropDown.setModel(new DefaultComboBoxModel<>(QualityLevel.values()));
        roomQualityDropDown.setBounds(95, 2, 132, 27);
        makeReservationTab.add(roomQualityDropDown);

        JLabel bedSizeFilterLabel = new JLabel("Bed Size:");
        bedSizeFilterLabel.setBounds(6, 34, 61, 16);
        makeReservationTab.add(bedSizeFilterLabel);

        bedSizeDropDown = new JComboBox<>();
        bedSizeDropDown.setModel(new DefaultComboBoxModel<>(BedType.values()));
        bedSizeDropDown.setBounds(95, 30, 132, 27);
        makeReservationTab.add(bedSizeDropDown);

        JLabel bedNumLabel = new JLabel("Number of Beds:");
        bedNumLabel.setBounds(6, 62, 111, 16);
        makeReservationTab.add(bedNumLabel);

        bedNumField = new JFormattedTextField();
        bedNumField.setHorizontalAlignment(SwingConstants.TRAILING);
        bedNumField.setBounds(116, 57, 106, 26);
        makeReservationTab.add(bedNumField);

        smokingCheckBox = new JCheckBox("Smoking");
        smokingCheckBox.setBounds(6, 90, 128, 23);
        makeReservationTab.add(smokingCheckBox);

        JLabel startDateLabel = new JLabel("Start Date: ");
        startDateLabel.setBounds(6, 125, 72, 16);
        makeReservationTab.add(startDateLabel);

        JLabel sdYearLabel = new JLabel("Year: ");
        sdYearLabel.setBounds(16, 142, 61, 16);
        makeReservationTab.add(sdYearLabel);

        JLabel sdMonthLabel = new JLabel("Month:");
        sdMonthLabel.setBounds(16, 170, 51, 16);
        makeReservationTab.add(sdMonthLabel);

        JLabel sdDayLabel = new JLabel("Day:");
        sdDayLabel.setBounds(16, 198, 61, 16);
        makeReservationTab.add(sdDayLabel);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(6, 226, 61, 16);
        makeReservationTab.add(endDateLabel);

        JLabel edYearLabel = new JLabel("Year:");
        edYearLabel.setBounds(17, 246, 61, 16);
        makeReservationTab.add(edYearLabel);

        JLabel edMonthLabel = new JLabel("Month:");
        edMonthLabel.setBounds(17, 274, 61, 16);
        makeReservationTab.add(edMonthLabel);

        JLabel edDayLabel = new JLabel("Day:");
        edDayLabel.setBounds(16, 302, 61, 16);
        makeReservationTab.add(edDayLabel);

        sdYearBox = new JComboBox<>();
        sdYearLabel.setLabelFor(sdYearBox);
        sdYearBox.setModel(new DefaultComboBoxModel<>(new String[] {"2023", "2024", "2025"}));
        sdYearBox.setBounds(60, 138, 167, 27);
        makeReservationTab.add(sdYearBox);

        sdMonthBox = new JComboBox<>();
        sdMonthLabel.setLabelFor(sdMonthBox);
        sdMonthBox.setModel(new DefaultComboBoxModel<>(Month.values()));
        sdMonthBox.setBounds(60, 166, 167, 27);
        makeReservationTab.add(sdMonthBox);

        sdDayBox = new JComboBox<>();
        sdDayBox.setModel(new DefaultComboBoxModel<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        sdDayBox.setBounds(60, 194, 167, 27);
        makeReservationTab.add(sdDayBox);

        edYearBox = new JComboBox<>();
        edYearBox.setModel(new DefaultComboBoxModel<>(new String[] {"2023", "2024", "2025"}));
        edYearBox.setBounds(60, 242, 167, 27);
        makeReservationTab.add(edYearBox);

        edMonthBox = new JComboBox<>();
        edMonthBox.setModel(new DefaultComboBoxModel<>(Month.values()));
        edMonthBox.setBounds(60, 270, 167, 27);
        makeReservationTab.add(edMonthBox);

        edDayBox = new JComboBox<>();
        edDayBox.setModel(new DefaultComboBoxModel<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        edDayBox.setBounds(60, 298, 167, 27);
        makeReservationTab.add(edDayBox);

        makeReservationButton = new JButton("Reserve Rooms");
        makeReservationButton.addActionListener(this);
        makeReservationButton.setBounds(384, 297, 132, 29);
        makeReservationTab.add(makeReservationButton);

        mrFilterButton = new JButton("Filter");
        mrFilterButton.addActionListener(this);
        mrFilterButton.setBounds(271, 297, 117, 29);
        makeReservationTab.add(mrFilterButton);

        JScrollPane makeResScrollPane = new JScrollPane();
        makeResScrollPane.setBounds(239, 6, 318, 284);
        makeReservationTab.add(makeResScrollPane);

        makeResTable = new JTable(new RoomTableModel());
        makeResScrollPane.setViewportView(makeResTable);
        makeResTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel futureReservationsTab = new JPanel();
        tabbedPane.addTab("Upcoming Reservations", null, futureReservationsTab, null);
        futureReservationsTab.setLayout(null);

        editResButton = new JButton("Edit");
        editResButton.addActionListener(this);
        editResButton.setBounds(153, 298, 117, 29);
        futureReservationsTab.add(editResButton);

        cancelResButton = new JButton("Cancel");
        cancelResButton.addActionListener(this);
        cancelResButton.setBounds(282, 298, 117, 29);
        futureReservationsTab.add(cancelResButton);

        JScrollPane upcomingResScrollPane = new JScrollPane();
        upcomingResScrollPane.setBounds(6, 6, 551, 280);
        futureReservationsTab.add(upcomingResScrollPane);

        upcomingResTable = new JTable(new UpcomingResTableModel(associatedGuest));

        upcomingResScrollPane.setViewportView(upcomingResTable);

        JPanel profileTab = new JPanel();
        tabbedPane.addTab("Edit Profile", null, profileTab, null);
        profileTab.setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(136, 80, 95, 16);
        profileTab.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(136, 108, 95, 16);
        profileTab.add(passwordLabel);

        JLabel nameFirstLabel = new JLabel("First Name: ");
        nameFirstLabel.setBounds(136, 136, 95, 16);
        profileTab.add(nameFirstLabel);

        JLabel nameLastLabel = new JLabel("Last Name: ");
        nameLastLabel.setBounds(136, 164, 95, 16);
        profileTab.add(nameLastLabel);

        isCorpCheckBox = new JCheckBox("Corporate");
        isCorpCheckBox.setSelected(associatedGuest.corporate());
        isCorpCheckBox.setBounds(136, 192, 95, 23);
        profileTab.add(isCorpCheckBox);

        usernameField = new JTextField();
        usernameField.setText(associatedGuest.getAccountUsername());
        usernameField.setBounds(209, 75, 130, 26);
        profileTab.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setText(associatedGuest.getAccountPassword());
        passwordField.setBounds(209, 103, 130, 26);
        profileTab.add(passwordField);
        passwordField.setColumns(10);

        nameFirstField = new JTextField();
        nameFirstField.setText(associatedGuest.getNameFirst());
        nameFirstField.setBounds(209, 131, 130, 26);
        profileTab.add(nameFirstField);
        nameFirstField.setColumns(10);

        nameLastField = new JTextField();
        nameLastField.setText(associatedGuest.getNameLast());
        nameLastField.setBounds(209, 159, 130, 26);
        profileTab.add(nameLastField);
        nameLastField.setColumns(10);

        updateProfileButton = new JButton("Update");
        updateProfileButton.addActionListener(this);
        updateProfileButton.setBounds(229, 191, 95, 29);
        profileTab.add(updateProfileButton);
    }
    @Override
    public void actionPerformed(ActionEvent a) {
        if(a.getSource() == mrFilterButton) {
            QualityLevel ql = (QualityLevel) roomQualityDropDown.getSelectedItem();
            Integer bedNum = Integer.valueOf(bedNumField.getText());
            BedType bs = (BedType)bedSizeDropDown.getSelectedItem();
            Boolean smoking = smokingCheckBox.isSelected();
            Integer year = sdYearBox.getSelectedIndex() + 2023;
            Integer m = sdMonthBox.getSelectedIndex();
            Integer day = sdDayBox.getSelectedIndex() + 1;
            Calendar cal = new GregorianCalendar(year, m, day);
            Date start = cal.getTime();
            cal.set(Calendar.YEAR, edYearBox.getSelectedIndex() + 2023);
            cal.set(Calendar.MONTH, edMonthBox.getSelectedIndex());
            cal.set(Calendar.DATE, edDayBox.getSelectedIndex() + 1);
            Date end = cal.getTime();
            ((RoomTableModel) makeResTable.getModel()).filter(ql, bedNum, bs, smoking, start, end);
        } else if(a.getSource() == makeReservationButton) {
            Integer year = sdYearBox.getSelectedIndex() + 2023;
            Integer m = sdMonthBox.getSelectedIndex();
            Integer day = sdDayBox.getSelectedIndex() + 1;
            Calendar cal = new GregorianCalendar(year, m, day);
            Date start = cal.getTime();
            cal.set(Calendar.YEAR, edYearBox.getSelectedIndex() + 2023);
            cal.set(Calendar.MONTH, edMonthBox.getSelectedIndex());
            cal.set(Calendar.DATE, edDayBox.getSelectedIndex() + 1);
            Date end = cal.getTime();
            int[] rows = makeResTable.getSelectedRows();
            ArrayList<Integer> roomNumbers = new ArrayList<>();
            for(int i = 0; i < rows.length; i++) {
                roomNumbers.add((Integer)(makeResTable.getValueAt(i, 0)));
            }
            Reservation r = Reservation.createReservation(start, end, associatedGuest, roomNumbers);
            ConfirmReservationGUI cr = new ConfirmReservationGUI(r, upcomingResTable);
        } else if(a.getSource() == editResButton){
            //TODO: open edit reservation GUI once created
        } else if(a.getSource() == cancelResButton){
            //TODO: check date
            //todo: create confirm cancel GUI
        } else if(a.getSource() == updateProfileButton){
            if(!Objects.equals(usernameField.getText(), associatedGuest.getAccountUsername())){
                if(Hotel.isUsernameUnique(usernameField.getText())){
                    associatedGuest.setAccountUsername(usernameField.getText());
                }
            }
            associatedGuest.setAccountPassword(Arrays.toString(passwordField.getPassword()));
            associatedGuest.setNameFirst(nameFirstField.getText());
            associatedGuest.setNameLast(nameLastField.getText());
        }
    }
}

