/*
 * 
/**
* This class represents the table model for displaying the list of upcoming reservations in the hotel. 
* It extends JPanel and implements the LaunchEditor interface. 
* The table is sortable and filterable by column.
*/

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class UpcomingResTableModel extends JPanel implements LaunchEditor{
    public static final Class<?>[] columnClass = new Class[] {Date.class, Date.class, String.class, Double.class};
    public static final String[] columnNames = {"Start Date", "End Date", "Rooms", "Rate"};
    protected JTable table;
    private final Guest myGuest;
    private static final int MAX_RESERVATIONS = 40;
    private static final int NUM_COLUMNS = 4;
    private static final Object[][] reservations = new Object[MAX_RESERVATIONS][NUM_COLUMNS];
    public UpcomingResTableModel(Guest g){
        super();
        myGuest = g;
        System.out.println(g.getAccountUsername());
        for(Reservation r: Hotel.getReservationsByGuestName(g.getAccountUsername())) {
        	System.out.println(r.getRoomsString());
        }
        // get all reservations from hotel
        loadReservationsIntoTable(Hotel.getReservationsByGuestName(g.getAccountUsername()));
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
            if(i >= MAX_RESERVATIONS){
                return;
            }
            reservations[i] = new Object[NUM_COLUMNS];
            reservations[i][0] = r.getStartDate();
            reservations[i][1] = r.getEndDate();
            reservations[i][2] = r.getRoomsString();
            reservations[i][3] = r.getRate();
            i++;
        }
    }


    @Override
    public void launch() {
        int index = table.getSelectedRow();
        if(index < 0){
            JOptionPane.showMessageDialog(this, "No row selected.");
        }else{
            EditReservationGUI editReservationGUI = new EditReservationGUI(myGuest.getUpcomingReservations().get(index));
            editReservationGUI.setVisible(true);
            editReservationGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    //If updated is true, update the reservation
                    if(editReservationGUI.isReservationUpdated()){
                    	 Reservation updatedRes = editReservationGUI.getUpdatedReservation(); 
                         int index = table.getSelectedRow();
                         DefaultTableModel model = (DefaultTableModel) table.getModel();
                         model.setValueAt(updatedRes.getStartDate(), index, 0);
                         model.setValueAt(updatedRes.getEndDate(), index, 1);
                         model.setValueAt(updatedRes.getRoomsString(), index, 2);
                         model.setValueAt(updatedRes.getRate(), index, 3);
                         model.fireTableRowsUpdated(index, index);
                    }
                }
            });
        }
        /*
        int index = table.getSelectedRow();
        if (index == 0) {
            JOptionPane.showMessageDialog(this, "No row selected.");
        } else {
            ReservationEditorGUI reservationEditorGUI = new
                    ReservationEditorGUI(myGuest.getUpcomingReservations().get(index));
            reservationEditorGUI.setVisible(true);

            // Wait for the ReservationEditorGUI to close
            reservationEditorGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (reservationEditorGUI.isReservationUpdated()) {
                        // Update the table model after editing a room
                        Reservation updatedRes = Hotel.rooms.get(index);
                        model.setValueAt(updatedRes.getRoomNumber(), index, 0);
                        model.setValueAt(updatedRes.getBedCount(), index, 1);
                        model.setValueAt(updatedRes.getBedSize(), index, 2);
                        model.setValueAt(updatedRes.getQuality(), index, 3);
                        model.setValueAt(updatedRes.getSmoking(), index, 4);
                        model.setValueAt(updatedRes.getQuality().getRate(), index, 5);
                        model.fireTableRowsUpdated(index, index);
                    }
                }
            });
        }

         */

    }

    @Override
    public JTable getTable() {
        return table;
    }

    @Override
    public String getMessage() {
        return "No reservation selected";
    }
    
   
    @Override
    public void deleteSelected() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Confirm Deletion");
        dialog.setSize(400, 300);
        dialog.setVisible(true);
        JPanel myInfo = new JPanel();
        String text = "Are you sure you want to cancel your reservation?";
        int index = table.getSelectedRow();
        JLabel myText = new JLabel(text);
        JButton confirm = new JButton("Confirm cancellation");
        myInfo.add(myText);
        myInfo.add(confirm);
        dialog.add(myInfo);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (index >= 0 && index < Hotel.reservations.size()) {
            	    Hotel.reservations.remove(index);
            	    ((DefaultTableModel) table.getModel()).removeRow(index);
            	    JOptionPane.showMessageDialog(null, "Reservation successfully deleted");
            	    dialog.dispose();
            	} else {
            	    JOptionPane.showMessageDialog(null, "No reservation selected");
            	}

            }
        });
    }

}
