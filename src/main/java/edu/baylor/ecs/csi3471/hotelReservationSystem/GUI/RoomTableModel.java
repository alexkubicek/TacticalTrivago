/**
 * file: RoomTableModel
 * author: KayLynn Beard
 *
 * Filterable, scrollable, selectable table object that
 * displays all available rooms in the hotel
 */

// TODO: update table function with chosen dates as filter

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Date;
import java.util.List;
import net.coderazzi.filters.gui.*;

public class RoomTableModel extends JPanel {
    //protected Hotel hotel;
    protected JTable table;
    TableRowSorter<DefaultTableModel> sorter;

    private static final int MAX_ROOMS = 40;
    private static final int NUM_COLUMNS = 6;
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, BedType.class, QualityLevel.class,
            Boolean.class, String.class};
    private static final String[] columnNames = {
            "Room Number", "Bed Count", "Bed Size", "Quality Level",
            "Smoking", "Room Rate" };
    private static Object[][] rooms = new Object[MAX_ROOMS][NUM_COLUMNS];

    private void initializeTable(){
        // get all rooms from hotel
        loadRoomsIntoTable(Hotel.getRooms());
        // create table of rooms
        DefaultTableModel model = new DefaultTableModel(rooms, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
            @Override
            public Class<?> getColumnClass(int columnIndex) {return columnClass[columnIndex];}

        };
        table = new JTable(model);
        // set dimensions of table
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        // only reserve one room at a time
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<DefaultTableModel>(model);
        table.setRowSorter(sorter);

        // format the cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(QualityLevel.class, centerRenderer);
        table.setDefaultRenderer(BedType.class, centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        // make it scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        // add filters for each column
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
    }

    public RoomTableModel(){
        super();
        initializeTable();
    }

    public void loadRoomsIntoTable(List<Room> roomList){
        int i = 0;
        for (Room r : roomList){
            rooms[i] = new Object[NUM_COLUMNS];
            rooms[i][0] = r.getRoomNumber();
            rooms[i][1] = r.getBedCount();
            rooms[i][2] = r.getBedSize();
            rooms[i][3] = r.getQuality();
            rooms[i][4] = r.getSmoking();
            rooms[i][5] = "$" + r.getQuality().getRate();
            i++;
        }
    }

    public void updateTable(Date startDate, Date endDate){
        // reload all rooms into table
        // TODO: fix this.. it's not reloading/restoring the table
        loadRoomsIntoTable(Hotel.getRooms());
        ((DefaultTableModel)table.getModel()).fireTableStructureChanged();

        int i = 0;
        while(i < table.getRowCount()) {
            // get each room in the hotel
            int roomNum = (Integer)table.getModel().getValueAt(i, 0);
            Room r = Hotel.getRoom(roomNum);
            // if room is not available, remove from table
            if(!r.isAvailable(startDate, endDate)) {
                ((DefaultTableModel)table.getModel()).removeRow(i);
            }
            i++;
        }
    }
}