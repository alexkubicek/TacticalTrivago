package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;

import hotelReadWriteUtils.CSVHotelUtils;

import java.awt.*;

public class LogOutGui {

    public static void displayLogoutPopup(Component parentComponent) {
        int confirmLogout = JOptionPane.showConfirmDialog(
                parentComponent,
                "Are you sure you want to log out?",
                "Log Out",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmLogout == JOptionPane.YES_OPTION) {
            int saveWork = JOptionPane.showConfirmDialog(
                    parentComponent,
                    "Do you want to save your work?",
                    "Save Work",
                    JOptionPane.YES_NO_OPTION
            );

            if (saveWork == JOptionPane.YES_OPTION) {
                // Save user work here
            	 try {
					CSVHotelUtils.doSave(Driver.tacticalTrivagoHotel);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }

            // Close the parent component
            if (parentComponent instanceof JFrame) {
                ((JFrame) parentComponent).dispose();
            }
            

            // Show login page
            new LoginPageGUI();
        }
    }
}
