package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
    public static final Class<?>[] columnClass = new Class[] {String.class, String.class, String.class, String.class, String.class};
    public static final String[] columnNames = {"Guest", "Start Date", "End Date", "Rooms", "Cost"};
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
            SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd");
            reservations[i] = new Object[NUM_COLUMNS];
            reservations[i][0] = r.getGuest().getAccountUsername();
            reservations[i][1] = r.getRoomsString();
            reservations[i][2] = formatter.format(r.getStartDate());
            reservations[i][3] = formatter.format(r.getEndDate());
            reservations[i][4] = String.format("$%.2f", r.calculateTotal());
        }
    }

    @Override
    public void launch() {
        int[] index = table.getSelectedRows();
        
        new RoomEditorGUI(Hotel.rooms.get(index[0]));
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
        String text = "Are you sure you want to cancel ";
        int index = table.getSelectedRow();
        text += table.getValueAt(index, 0) + "'s reservation?";
        JLabel myText = new JLabel(text);
        JButton confirm = new JButton("Confirm cancellation");
        myInfo.add(myText);
        myInfo.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel.reservations.remove(index);
                JOptionPane.showMessageDialog(null, "Reservation successfully deleted");
                dialog.dispose();
            }
        });
    }
}

