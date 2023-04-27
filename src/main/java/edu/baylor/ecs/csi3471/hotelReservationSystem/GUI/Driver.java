/*
USER LOGIN: user pass
CLERK LOGIN: clerk pass
ADMIN LOGIN: admin pass
 */

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    static Hotel tacticalTrivagoHotel = new Hotel();
    public static void loadRoomsFromCSV(String file) throws FileNotFoundException {
        List<Room> rooms = new ArrayList<>();
        BufferedReader reader = null;
        if(!file.endsWith(".csv")){
            System.err.println("file must be csv");
            throw new RuntimeException();
        }
        try {
            reader = new BufferedReader(new FileReader(file));
            // ignore header line
            String line;
            reader.readLine();
            // read each row
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                Room r = new Room(split);
                rooms.add(r);
            }
            tacticalTrivagoHotel.setRooms(rooms);
        } catch (IOException e) {
            String hint;
            try {
                hint = "Current dir is: " + new File(".").getCanonicalPath();
            } catch (Exception local) {
                hint = local.getLocalizedMessage();
            }
            throw new FileNotFoundException(e.getLocalizedMessage() + "\n" + hint);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }
    public static void main(String[] args) {
        tacticalTrivagoHotel.addAccount(new Guest("Alex", "", new AccountInformation("user", "pass")));
        tacticalTrivagoHotel.addAccount(new Clerk("Clerk", "", new AccountInformation("clerk", "pass")));
        tacticalTrivagoHotel.addAccount(new Admin("Admin", "", new AccountInformation("admin", "pass")));
        try{
            loadRoomsFromCSV("src/main/resources/testRooms1.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        LoginPageGUI lp = new LoginPageGUI();
    }
}
