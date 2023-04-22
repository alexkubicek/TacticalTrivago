package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;

public class UpcomingResTableModel extends DefaultTableModel {
    public static final Class<?>[] columnClass = new Class[] {Date.class, Date.class, Integer.class, Integer.class, BedType.class};
    public static final String[] columnNames = {"Start Date", "End Date", "Rooms", "Beds"};

    public void launchEditor(){
        CreateEditReservationGUI myRes = new CreateEditReservationGUI();
    }
    public void populate(Guest g) {
        Hotel.reservations.stream().filter((res)-> res.getGuest() == g).forEach(r -> {
            StringBuilder bedString = new StringBuilder();
            if(r.getRooms().size() == 1) {
                bedString = new StringBuilder(r.getRooms().get(0).getBedCount().toString() + " " + r.getRooms().get(0).getBedSize());
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
                    bedString.append(beds.get(key)).append(" ").append(key).append("S\n");
                }
            }
            Object[] curRes = {r.getStartDate().toString(), r.getEndDate().toString(), r.getRooms().size(), bedString.toString()};
            this.addRow(curRes);
        });
    }

    public UpcomingResTableModel(Guest g) {
        super();
        populate(g);
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
}
