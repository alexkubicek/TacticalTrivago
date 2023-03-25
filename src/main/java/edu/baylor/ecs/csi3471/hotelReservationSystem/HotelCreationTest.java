package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelCreationTest {
    public static List<Room> loadCSV(String file) throws FileNotFoundException{
        List<Room> rooms = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(file)));
            // ignore header line
            String line = reader.readLine();
            line = null;
            // read each row
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                Room r = new Room(split);
                rooms.add(r);
            }
            return rooms;
        } catch (IOException e) {
            String hint = "";
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
        Hotel h = new Hotel();
        List<Room> rooms = null;

        try {
            rooms = loadCSV("src/main/resources/testRooms1.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }

        h.setRooms(rooms);
        System.out.println("Displaying all rooms in the hotel...");
        h.displayAllRooms();
    }
}
