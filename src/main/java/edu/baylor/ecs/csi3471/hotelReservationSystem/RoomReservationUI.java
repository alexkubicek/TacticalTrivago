package edu.baylor.ecs.csi3471.hotelReservationSystem;

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
    public RoomReservationUI(){ super(); }

    public void display(Hotel hotel){
        loadRooms(hotel.getRooms());

        JFrame frame = new JFrame("Displaying All Rooms in Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JButton reserveButton = new JButton("Reserve selected room");
        reserveButton.setVisible(true);
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
                        String[] availableStartDates = { "today", "tomorrow" };
                        JComboBox<String> startDateOptions = new JComboBox<String>(availableStartDates);
                        panel.add(startDateOptions);

                        JLabel label2 = new JLabel("Select desired check-out date:");
                        label2.setAlignmentX(Component.LEFT_ALIGNMENT);
                        panel.add(label2);
                        // fix this!!!
                        String[] availableEndDates = { "tomorrow", "the next day", "the day after that" };
                        JComboBox<String> availableEndOptions = new JComboBox<String>(availableEndDates);
                        availableEndOptions.setVisible(true);
                        panel.add(availableEndOptions);

                        JButton confirmButton = new JButton("Submit reservation");
                        panel.add(confirmButton);
                        // add action listener to call reserveRoom()

                        dialog.add(panel);
                    }
                });
            }
        });

        JTable table = new JTable(this);
        JTableHeader header = table.getTableHeader();
        header.setTable(table);
        header.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.add(reserveButton, BorderLayout.SOUTH);

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