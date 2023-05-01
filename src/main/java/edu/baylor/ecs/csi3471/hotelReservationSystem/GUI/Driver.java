/*
 * This class contains the main method to run the Hotel Reservation System. 
 * It represents the controller of the system.
 */

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
        
        for(User u: tacticalTrivagoHotel.accounts) {
        	if(u instanceof Guest) {
        		Guest g = (Guest) u;
        		System.out.println(g.toStringForUI());
        	}
        }
        System.out.println(tacticalTrivagoHotel.reservations.size());
        for(Reservation r: tacticalTrivagoHotel.reservations) {
        	System.out.println(r.getGuest().getAccountUsername());
        }
        LoginPageGUI lp = new LoginPageGUI();
        lp.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                CSVHotelUtils.doSave(tacticalTrivagoHotel);
                System.exit(0);
            }
        });
        
    }
}
