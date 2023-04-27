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
        // TODO: load (somehow) guest associations with creditcards and upcomingreservations (can't get from the csv file)
        // TODO: Alex: maybe make a creditcard.csv? or put credit cards with guests
        /*
        Alex: I would do something like this so that we don't have to have any redundant data

            Hotel.accounts <- all users from admins/clerks/guests csv
            Hotel.rooms <- rooms from CSV
            for each reservation in CSV:
                Hotel.reservations.append(reservation)
                Hotel.accounts[reservation's associated guest].upcomingRes.append(reservation)
                Hotel.rooms[rooms booked].unavailable.append(dates of reservation)
         */
        LoginPageGUI lp = new LoginPageGUI();
    }
}
