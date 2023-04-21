package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import java.util.Date;
import java.util.Objects;

import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

public class RoomTableModel extends DefaultTableModel {
    public static final Class<?>[] columnClass = new Class[] {Integer.class, QualityLevel.class, Integer.class, BedType.class, boolean.class, Double.class};
    public static final String[] columnNames = {"Room Number", "Quality Level", "Bed Count", "Bed Size", "Smoking", "Rate"};
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

    public void populate() {
        Hotel.rooms.forEach(r->{
            Object[] curRoom = {r.getRoomNumber(), r.getQuality(), r.getBedCount(), r.getBedSize(), r.getSmoking(), r.getQuality().getRate()};
            this.addRow(curRoom);
        });
    }

    public RoomTableModel() {
        super();
        this.populate();
    }

    public void filter(QualityLevel ql, Integer bedCount, BedType bedSize, Boolean s, Date start, Date end) {
        this.populate();
        for(int i = 0; i < this.getRowCount(); i++) {
            Room curRoom = Hotel.getRoom((int)(this.getValueAt(i, 0)));
            if(ql != QualityLevel.ALL && curRoom.getQuality() != ql) {
                this.removeRow(i--);
            } else if(bedCount != null && (!Objects.equals(curRoom.getBedCount(), bedCount))) {
                this.removeRow(i--);
            } else if(bedSize != BedType.ALL && this.getValueAt(i, 3) != bedSize) {
                this.removeRow(i--);
            } else if(s != null && s != this.getValueAt(i, 4)) {
                this.removeRow(i--);
            } else if(start != null){
                if(!curRoom.isAvailable(start, end)) {
                    this.removeRow(i--);
                }
            }
        }

    }

}
