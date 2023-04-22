package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.User;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class ClerkTableModel extends JPanel implements LaunchEditor{
    public static final Class<?>[] columnClass = new Class[] {String.class, String.class, String.class};
    public static final String[] columnNames = {"Username", "First Name", "Last Name"};
    protected JTable table;

    private static final int MAX_USERS = 50;
    private static final int NUM_COLUMNS = 5;
    private static final Object[][] reservations = new Object[MAX_USERS][NUM_COLUMNS];
    public ClerkTableModel(){
        super();
        // get all rooms from hotel
        loadClerksIntoTable(Hotel.getClerks());
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
    public void loadClerksIntoTable(List<Clerk> users){
        int i = 0;
        for (Clerk u : users){
            if(i > MAX_USERS){
                return;
            }
            reservations[i] = new Object[NUM_COLUMNS];
            reservations[i][0] = u.getAccountUsername();
            reservations[i][1] = u.getNameFirst();
            reservations[i][2] = u.getNameLast();
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

    @Override
    public void deleteSelected() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Confirm Deletion");
        dialog.setSize(400, 300);
        dialog.setVisible(true);
        JPanel myInfo = new JPanel();
        String text = "Are you sure you want to cancel ";
        int index = table.getSelectedRow();
        text += table.getValueAt(index, 0) + "'s account?";
        JLabel myText = new JLabel(text);
        JButton confirm = new JButton("Confirm deletion");
        myInfo.add(myText);
        myInfo.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel.reservations.remove(index);
                JOptionPane.showMessageDialog(null, "Clerk successfully deleted");
                dialog.dispose();
            }
        });
    }
}
