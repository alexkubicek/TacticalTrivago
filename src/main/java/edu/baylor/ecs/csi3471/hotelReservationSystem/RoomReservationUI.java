package edu.baylor.ecs.csi3471.hotelReservationSystem;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.coderazzi.filters.gui.*;
import com.toedter.calendar.JDateChooser;

public class RoomReservationUI extends JPanel {
    private Hotel hotel;
    private JTable table;
    private Guest guest;
    private static final int MAX_ROOMS = 40;
    private static final int NUM_COLUMNS = 6;
    final Class<?>[] columnClass = new Class[] {String.class, String.class, BedType.class, QualityLevel.class, Boolean.class, Double.class};
    private static final String[] columnNames = { "Room Number", "Bed Count", "Bed Size", "Quality Level", "Smoking", "Room Rate" };
    private static Object[][] rooms = new Object[MAX_ROOMS][NUM_COLUMNS];
    private Date startDate, endDate;

    public RoomReservationUI(){
        super();
        // create scrollable table of rooms with filters for each column
        DefaultTableModel model = new DefaultTableModel(rooms, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
            @Override
            public Class<?> getColumnClass(int columnIndex) {return columnClass[columnIndex];}
        };
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        // only reserve one room at a time, for simplicity's sake
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
    }
    public void loadRooms(List<Room> roomList){
        int i = 0;
        //rooms[0] = columnNames;
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
    
    public void updateTable(){
        // if start or end date changes, update table to only display rooms available for all days between those dates ---------
    }

    public void createConfirmationDialog(DefaultTableModel model, int modelRow)  {
        // check dates are valid ------------------
        /*
        Date today = new Date();
        // check that endDate falls after startDate, and startDate is after today
       if(!endDate.after(startDate) || today.after(startDate)){
            JOptionPane.showMessageDialog(null, "Selected dates are invalid!");
            return;
        } */
      
       JDialog dialog = new JDialog();
        dialog.setTitle("Confirm Reservation");
        dialog.setSize(400, 300);
        dialog.setVisible(true);
      
      int roomNum = (Integer)model.getValueAt(modelRow, 0);
        Room room = hotel.getRoom(roomNum);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Confirm room reservation information:"));
        panel.add(new JLabel(room.toStringForUI()));
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd, YYYY");
        panel.add(new JLabel("<html>Check-in date: " + formatter.format(startDate) +
                "<br> Check-out date: " + formatter.format(endDate) + "</html>"));

        JButton confirmButton = new JButton("Submit reservation");
        panel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
                        @Override
            public void actionPerformed(ActionEvent e) {
                hotel.reserveRoom(room, startDate, endDate, guest, hotel);
                // add closing of dialog after successful reservation creation
                List<Reservation> list = hotel.getReservations();
                for(Reservation r : list){
                    System.out.println(r.toString());
                }
            }
        });
        dialog.add(panel);
    }
    public JPanel createDateSelection(){
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Select desired check-in date:");
        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label2 = new JLabel("Select desired check-out date:");
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);     
      
        JDateChooser startDateChooser = new JDateChooser();
        startDateChooser.setDateFormatString("MM/dd/yyyy");
        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("MM/dd/yyyy");

        panel.add(label1);
        panel.add(startDateChooser);
        panel.add(label2);
        panel.add(endDateChooser);

        startDateChooser.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            startDate = (Date) e.getNewValue();
                            updateTable();
                        }
                    }
                });

        endDateChooser.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            endDate = (Date) e.getNewValue();
                            updateTable();
                        }
                    }
                });
        return panel;
    }
    public void display(){
        this.hotel = HotelApp.hotel;
        //this.guest = g;
        loadRooms(hotel.getRooms());

        RoomReservationUI roomPane = new RoomReservationUI();
        roomPane.setOpaque(true);
        JButton reserveButton = new JButton("Reserve selected room");
        reserveButton.setVisible(true);
        reserveButton.addActionListener(e -> {
            int viewRow = roomPane.table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(null, "No room selected!");
            }else if(startDate == null || endDate == null){
                JOptionPane.showMessageDialog(null, "No dates selected!");
            }
            else {
                int modelRow = roomPane.table.convertRowIndexToModel(viewRow);
                DefaultTableModel model = (DefaultTableModel)roomPane.table.getModel();
                SwingUtilities.invokeLater(() -> createConfirmationDialog(model, modelRow));
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

        roomPane.add(reserveButton, BorderLayout.SOUTH);
        roomPane.add(createDateSelection());

        JFrame frame = new JFrame("Available Rooms in Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setContentPane(roomPane);
        frame.setVisible(true);
    }
}