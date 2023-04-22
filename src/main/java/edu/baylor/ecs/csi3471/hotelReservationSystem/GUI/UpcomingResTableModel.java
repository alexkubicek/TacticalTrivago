package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class UpcomingResTableModel extends JPanel {
    public static final Class<?>[] columnClass = new Class[] {Date.class, Date.class, String.class, Double.class};
    public static final String[] columnNames = {"Start Date", "End Date", "Rooms", "Rate"};
    protected JTable table;

    private static final int MAX_RESERVATIONS = 40;
    private static final int NUM_COLUMNS = 4;
    private static final Object[][] reservations = new Object[MAX_RESERVATIONS][NUM_COLUMNS];
    public UpcomingResTableModel(Guest g){
        super();
        // get all reservations from hotel
        loadReservationsIntoTable(g.getUpcomingReservations());
        // create table of reservations
        DefaultTableModel model = new DefaultTableModel(reservations, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
            @Override
            public Class<?> getColumnClass(int columnIndex) {return columnClass[columnIndex];}
        };
        table = new JTable(model);
        // set dimensions of table
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);
        // only view one reservation at a time
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
            reservations[i][0] = r.getRoomsString();
            reservations[i][1] = r.getStartDate();
            reservations[i][2] = r.getEndDate();
            reservations[i][3] = r.getRate();
            i++;
        }
    }
}
