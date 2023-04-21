package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import javax.swing.table.DefaultTableModel;

public class ClerkTableModel extends DefaultTableModel {
    public static final Class<?>[] columnClass = new Class[] {String.class, String.class, String.class};
    public static final String[] columnNames = {"Username", "First Name", "Last Name"};
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
        Hotel.accounts.forEach(u->{
            String[] curUser = u.getTableInfo();
            if(curUser != null){
                this.addRow(curUser);
            }
        });
    }

    public ClerkTableModel() {
        super();
        this.populate();
    }
}
