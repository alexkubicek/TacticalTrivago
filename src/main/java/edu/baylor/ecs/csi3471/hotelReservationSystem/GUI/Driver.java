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
            CSVHotelUtils csvLoader = new CSVHotelUtils();
            tacticalTrivagoHotel = csvLoader.load();
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        LoginPageGUI lp = new LoginPageGUI();
    }
}
