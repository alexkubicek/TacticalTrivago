package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class ReservationTableModel extends DefaultTableModel {
    public static final Class<?>[] columnClass = new Class[] {String.class, Date.class, Date.class, Integer.class, Integer.class};
    public static final String[] columnNames = {"Guest", "Start Date", "End Date", "Rooms", "Beds"};

    public void populate() {
        Hotel.reservations.forEach(r->{
            String bedString = "";
            if(r.getRooms().size() == 1) {
                bedString = r.getRooms().get(0).getBedCount().toString() + " " + r.getRooms().get(0).getBedSize();
            } else {
                Map<String, Integer> beds = new HashMap<>();
                r.getRooms().forEach(room->{
                    if(beds.containsKey(room.getBedSize())) {
                        beds.replace(room.getBedSize(), beds.get(room.getBedSize() + room.getBedCount()));
                    } else {
                        beds.put(room.getBedSize(), room.getBedCount());
                    }
                });
                for(String key : beds.keySet()) {
                    bedString += beds.get(key) + " " + key + "S\n";
                }
            }
            Object[] curRes = {r.getGuest().getFullName(), r.getStartDate(), r.getEndDate(), r.getRooms().size(), bedString};
            this.addRow(curRes);
        });
    }

    public ReservationTableModel() {
        super();
        populate();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public boolean isCellEditable(int row, int column) {return false;}

    public void filter(String firstName, String lastName, Date startDate) {
        this.populate();
        for(int i = 0; i < this.getRowCount(); i++) {
            if(firstName != null && ((String) this.getValueAt(i, 0)).split(" ")[0] != firstName) {
                this.removeRow(i--);
            } else if(lastName != null && ((String)this.getValueAt(i, 0)).split(" ")[1] != lastName) {
                this.removeRow(i--);
            } else if(startDate != null && this.getValueAt(i, 1) != startDate) {
                this.removeRow(i--);
            }
        }
    }
}

