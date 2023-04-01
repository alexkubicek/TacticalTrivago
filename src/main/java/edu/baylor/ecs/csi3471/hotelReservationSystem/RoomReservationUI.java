package edu.baylor.ecs.csi3471.hotelReservationSystem;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RoomReservationUI extends AbstractTableModel {
    private static final int MAX_ROOMS = 100;
    private static final int NUM_COLUMNS = 6;
    private static final String[] columnNames = { "Room Number", "Bed Count", "Bed Size", "Quality Level", "Smoking Allowed", "Room Rate" };
    private static Object[][] rooms = new Object[MAX_ROOMS][NUM_COLUMNS];

    public void loadRooms(List<Room> roomList){
        int i = 0;
        for (Room r : roomList){
            rooms[i] = new Object[NUM_COLUMNS];
            rooms[i][0] = r.getRoomNumber();
            rooms[i][1] = r.getBedCount();
            rooms[i][2] = r.getBedSize();
            rooms[i][3] = r.getQuality();
            rooms[i][4] = r.getSmoking();
            rooms[i][5] = r.getQuality().getRate();
            i++;
        }
    }
    public RoomReservationUI(){ 
    	super(); 
    }

    public void display(){
    	Hotel hotel = HotelApp.hotel;
        loadRooms(hotel.getRooms());

        JFrame frame = new JFrame("Displaying All Rooms in Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JButton reserveButton = new JButton("Reserve selected room");
        reserveButton.setVisible(true);

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


        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        JDialog dialog = new JDialog();
                        dialog.setSize(400, 300);
                        dialog.setVisible(true);
                        JPanel panel = new JPanel();

                        JLabel label1 = new JLabel("Select desired check-in date:");
                        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
                        panel.add(label1);
                        // fix this!!!
                        JDateChooser dateChooser = new JDateChooser();
                        dateChooser.setDateFormatString("MM/dd/yyyy");
                        panel.add(dateChooser);
                        /* Fix this!!
                        String[] availableStartDates = { "today", "tomorrow" };
                        JComboBox<String> startDateOptions = new JComboBox<String>(availableStartDates);
                        panel.add(startDateOptions);
                         */
                        JLabel label2 = new JLabel("Select desired check-out date:");
                        label2.setAlignmentX(Component.LEFT_ALIGNMENT);
                        panel.add(label2);

                        JDateChooser checkDateChooser = new JDateChooser();
                        checkDateChooser.setDateFormatString("MM/dd/yyyy");
                        panel.add(checkDateChooser);

                        /* fix this!!!
                        String[] availableEndDates = { "tomorrow", "the next day", "the day after that" };
                        JComboBox<String> availableEndOptions = new JComboBox<String>(availableEndDates);
                        availableEndOptions.setVisible(true);
                        panel.add(availableEndOptions);

                         */

                        JButton confirmButton = new JButton("Submit reservation");
                        panel.add(confirmButton);
                        // add action listener to call reserveRoom()

                        dialog.add(panel);
                    }
                });
            }
        });
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

        JTable table = new JTable(this);
        JTableHeader header = table.getTableHeader();
        header.setTable(table);
        header.setVisible(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(reserveButton);
        buttonPanel.add(addUserButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public int getRowCount() {
        int numRooms = 0;
        for(Object[] r : rooms){
            if(r[0] == null){
                return numRooms;
            } else{
                numRooms++;
            }
        }
        return numRooms;
    }
    @Override
    public int getColumnCount() {
        return NUM_COLUMNS;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rooms[rowIndex][columnIndex];
    }
}