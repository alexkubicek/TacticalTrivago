/*
USER LOGIN: user pass
CLERK LOGIN: clerk pass
ADMIN LOGIN: admin pass
 */

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;
import hotelReadWriteUtils.CSVHotelUtils;

import java.io.*;

public class Driver {
    static Hotel tacticalTrivagoHotel = new Hotel();
    public static void main(String[] args) {
        try{
            tacticalTrivagoHotel = CSVHotelUtils.load();
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        LoginPageGUI lp = new LoginPageGUI();
        // TODO: call save anytime the application is exited (where should we allow this?)
        // should you have to log out to close the application?
        lp.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                CSVHotelUtils.doSave(tacticalTrivagoHotel);
                System.exit(0);
            }
        });
    }
}
