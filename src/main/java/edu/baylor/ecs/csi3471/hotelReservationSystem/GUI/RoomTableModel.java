/**
 * file: RoomTableModel
 * author: KayLynn Beard
 *
 * Filterable, scrollable, selectable table object that
 * displays all (available?) rooms in the hotel
 */

// TODO: make it pretty (change dimensions, font, font size, alignment, $ for rates, etc...)
// TODO: update table function with chosen dates?

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.coderazzi.filters.gui.*;

public class RoomTableModel extends JPanel implements LaunchEditor{
    protected JTable table;
    protected Date startDate, endDate;
    private DefaultTableModel model;

    private static final int MAX_ROOMS = 40;
    private static final int NUM_COLUMNS = 6;
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, BedType.class, QualityLevel.class,
            Boolean.class, Double.class};
    private static final String[] columnNames = {
            "Room Number", "Bed Count", "Bed Size", "Quality Level",
            "Smoking", "Room Rate" };
    private static Object[][] rooms = new Object[MAX_ROOMS][NUM_COLUMNS];

    public RoomTableModel(){
        super();
        // get all rooms from hotel
        loadRoomsIntoTable(Hotel.getRooms());
        // create table of rooms
         model = new DefaultTableModel(rooms, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
            @Override
            public Class<?> getColumnClass(int columnIndex) {return columnClass[columnIndex];}
        };
        table = new JTable(model);
        // set dimensions of table
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        // reserve multiple rooms at a time
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // make it scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        // add filters for each column
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
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
            rooms[i][5] = r.getQuality().getRate();
            i++;
        }
    }

    @Override
    public void launch() {
        int[] index = table.getSelectedRows();
        if (index.length == 0) {
            JOptionPane.showMessageDialog(this, "No row selected.");
        } else {
            RoomEditorGUI roomEditorGUI = new RoomEditorGUI(Hotel.rooms.get(index[0]));
            roomEditorGUI.setVisible(true);

            // Wait for the RoomEditorGUI to close
            roomEditorGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (roomEditorGUI.isRoomUpdated()) {
                        // Update the table model after editing a room
                        Room updatedRoom = Hotel.rooms.get(index[0]);
                        model.setValueAt(updatedRoom.getRoomNumber(), index[0], 0);
                        model.setValueAt(updatedRoom.getBedCount(), index[0], 1);
                        model.setValueAt(updatedRoom.getBedSize(), index[0], 2);
                        model.setValueAt(updatedRoom.getQuality(), index[0], 3);
                        model.setValueAt(updatedRoom.getSmoking(), index[0], 4);
                        model.setValueAt(updatedRoom.getQuality().getRate(), index[0], 5);

                        model.fireTableRowsUpdated(index[0], index[0]);
                    }
                }
            });
        }
    }


    @Override
    public JTable getTable() {
        return table;
    }

    @Override
    public String getMessage() {
        return "No room selected";
    }
    
    public void deleteSelected() {
        int[] viewIndices = table.getSelectedRows();
        if (viewIndices.length == 0) {
            JOptionPane.showMessageDialog(this, "No row selected.");
        } else {
            // Convert view indices to model indices
            int[] modelIndices = new int[viewIndices.length];
            for (int i = 0; i < viewIndices.length; i++) {
                modelIndices[i] = table.convertRowIndexToModel(viewIndices[i]);
            }

            // Show the confirmation dialog
            String message = "Are you sure you want to delete the selected room(s)?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Sort model indices in descending order
                Arrays.sort(modelIndices);
                for (int i = modelIndices.length - 1; i >= 0; i--) {
                    int modelIndex = modelIndices[i];

                    // Remove room from Hotel.rooms and update the table model
                    Hotel.rooms.remove(modelIndex);
                    model.removeRow(modelIndex);
                }

                // Show success message
                JOptionPane.showMessageDialog(null, "Room(s) successfully deleted");
            }
        }
    }

}