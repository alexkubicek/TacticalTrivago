package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class ReservationTableModel extends JPanel implements LaunchEditor{
    public static final Class<?>[] columnClass = new Class[] {String.class, Date.class, Date.class, String.class, Double.class};
    public static final String[] columnNames = {"Guest", "Start Date", "End Date", "Rooms", "Rate"};
    protected JTable table;

    private static final int MAX_RESERVATIONS = 50;
    private static final int NUM_COLUMNS = 5;
    private static final Object[][] reservations = new Object[MAX_RESERVATIONS][NUM_COLUMNS];
    public ReservationTableModel(){
        super();
        // get all rooms from hotel
        loadReservationsIntoTable(Hotel.reservations);
        // create table of rooms
        DefaultTableModel model = new DefaultTableModel(reservations, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
            @Override
            public Class<?> getColumnClass(int columnIndex) {return columnClass[columnIndex];}
        };
        table = new JTable(model);
        // set dimensions of table
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // make it scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        // add filters for each column
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
    }
    public void loadReservationsIntoTable(List<Reservation> reservationList){
        int i = 0;
        for (Reservation r : reservationList){
            if(i > MAX_RESERVATIONS){
                return;
            }
            reservations[i] = new Object[NUM_COLUMNS];
            reservations[i][0] = r.getGuest().getAccountUsername();
            reservations[i][1] = r.getRoomsString();
            reservations[i][2] = r.getStartDate();
            reservations[i][3] = r.getEndDate();
            reservations[i][4] = r.getRate();
            i++;
        }
    }

    @Override
    public void launch() {
        int[] index = table.getSelectedRows();
        new RoomEditorGUI(Hotel.rooms.get(index[0]));
    }
}

