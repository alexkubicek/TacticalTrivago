package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClerkTableModel extends JPanel implements LaunchEditor{
    public static final Class<?>[] columnClass = new Class[] {String.class, String.class, String.class};
    public static final String[] columnNames = {"Username", "First Name", "Last Name"};
    protected JTable table;
    protected TableRowSorter<DefaultTableModel> sorter;

    private static final int MAX_USERS = 50;
    private static final int NUM_COLUMNS = 5;
    private static final Object[][] clerks = new Object[MAX_USERS][NUM_COLUMNS];
    private DefaultTableModel model;
    public ClerkTableModel() {
        super();
        // get all rooms from hotel
        loadClerksIntoTable(Hotel.getClerks());
        // create table of rooms
        model = new DefaultTableModel(clerks, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
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
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        // make it scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        // add filters for each column
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
    }
    
    public void loadClerksIntoTable(List<Clerk> users){
        int i = 0;
        for (Clerk u : users){
            if(i > MAX_USERS){
                return;
            }
            clerks[i] = new Object[NUM_COLUMNS];
            clerks[i][0] = u.getAccountUsername();
            clerks[i][1] = u.getNameFirst();
            clerks[i][2] = u.getNameLast();
            i++;
        }
    }

    @Override
    public void launch() {
        String username = (String)table.getValueAt(table.getSelectedRow(), 0);
        for(User u: Hotel.accounts){
            if(u.getAccountUsername().equals(username)){
                u.launchProfile();
                return;
            }
        }
    }

    @Override
    public JTable getTable() {
        return table;
    }

    @Override
    public String getMessage() {
        return "No clerk selected";
    }

    private boolean inTable(User u){
        int i = 0;
        while(i < table.getRowCount()) {
            // get each room in the table
            int roomNum = (Integer)table.getModel().getValueAt(i, 0);
            Room room = Hotel.getRoom(roomNum);
            // if this room is in table
            if(room.equals(u)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void updateTable(Clerk c){
        reloadRooms();
        // remove unavailable rooms from table
        int i = 0;
        while(i < table.getRowCount()) {
            // check if clerk still exists
            String username = (String)table.getModel().getValueAt(i, 0);
            List<Clerk> newClerks = Hotel.getClerkAccounts();
            if(!newClerks.contains(c)) {
            	((DefaultTableModel)table.getModel()).removeRow(i);
            }
            
            i++;
        }
    }

    public void reloadRooms(){
        // reload table 
    	int i = 0;
        for (Clerk u : Hotel.getClerkAccounts()){
            if(!inTable(u)){
		        Object[] row = new Object[NUM_COLUMNS];
		        clerks[i][0] = u.getAccountUsername();
		        clerks[i][1] = u.getNameFirst();
		        clerks[i][2] = u.getNameLast();
		        ((DefaultTableModel)table.getModel()).insertRow(i, row);
		        i++;
            }
        }
    }
    
    @Override
    public void deleteSelected() {
    	int[] viewIndices = table.getSelectedRows();
        if (viewIndices.length == 0) {
            JOptionPane.showMessageDialog(this, "No row selected.");
        } else {
            // Show the confirmation dialog
            String message = "Are you sure you want to delete the selected clerks(s)?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
            	
                for (int i = viewIndices.length - 1; i >= 0; i--) {
                    int modelIndex = viewIndices[i];
                    Object username = model.getValueAt(modelIndex, 0);
                    Hotel.deleteClerk((String) username);
                    model.removeRow(modelIndex);
                }

                JOptionPane.showMessageDialog(null, "Clerk(s) successfully deleted");
                System.out.println("After Clerks: " + Hotel.getClerkAccounts());
            }
        }
    }
}
