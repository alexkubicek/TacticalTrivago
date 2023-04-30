/*
 * An interface to serve as a template for table models needed in other GUIs.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

public interface LaunchEditor {
    public void launch();
    public JTable getTable();
    public String getMessage();
    public void deleteSelected();
}
