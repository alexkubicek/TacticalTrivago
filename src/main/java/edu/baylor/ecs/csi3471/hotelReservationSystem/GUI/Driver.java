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
        // TODO: load (somehow) guest associations with creditcards and upcomingreservations (can't get from the csv file)
        // would have to give reservations a unique id to look up with that goes in TacticalTrivagoGuests.csv
        LoginPageGUI lp = new LoginPageGUI();
    }
}
