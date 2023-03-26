package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
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
        
        // MakeReservation UseCase
        System.out.println("Displaying all rooms in the hotel...");
        h.displayAllRooms();
        
        
        Guest g = new Guest("Cool", "Guest", new AccountInformation("username", "password"));
        System.out.println("Assume Cool Guest wants to reserve the room from April 5th, 2023 to April 13th, 2023");
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2023, 3, 5, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(2023, 3, 13, 0, 0, 0);
        Date end = calendar.getTime();
        
        System.out.println("Displaying available rooms in that time:");
        h.displayAvailableRooms(start, end);
        
        System.out.println("Guest selects room 1");
        ArrayList<Integer> selectedRooms = new ArrayList<Integer>();
        selectedRooms.add(1);
        
        
        h.reserveRoom(selectedRooms, start, end, g, h);
        
        System.out.println("Outputting guest's upcoming reservations:");
        System.out.println(g.getUpcomingReservations());
        
        
    }
}
