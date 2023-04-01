package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.io.*;
import java.util.*;

public class ReservationSystem {
    private static Hotel hotel = new Hotel();

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
            hotel.setRooms(rooms);
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

    public static void loadGuestsFromCSV(String file) throws FileNotFoundException {
        List<Guest> guests = new ArrayList<>();
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
                Guest g = new Guest(split);
                guests.add(g);
            }

            hotel.addAccounts(guests);

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
        // fill Hotel with rooms
        /*
        try{
            loadGuestsFromCSV("src/main/resources/testUsers1.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        
        try{
            loadRoomsFromCSV("src/main/resources/testRooms1.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        
        // MakeReservation UseCase
        /*
        System.out.println("Displaying all rooms in the hotel...");
        hotel.displayAllRooms();
        
        
        Guest g = new Guest("Cool", "Guest", new AccountInformation("username", "password"));
        System.out.println("Assume Cool Guest wants to reserve the room from April 5th, 2023 to April 13th, 2023");
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2023, Calendar.APRIL, 5, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(2023, Calendar.APRIL, 13, 0, 0, 0);
        Date end = calendar.getTime();

        System.out.println("Displaying available rooms in that time:");
        hotel.displayAvailableRooms(start, end);
        
        System.out.println("Guest selects room 1");
        ArrayList<Integer> selectedRooms = new ArrayList<>();
        selectedRooms.add(1);


        hotel.reserveRoom(selectedRooms, start, end, g, hotel);
        
        System.out.println("Outputting guest's upcoming reservations:");
        System.out.println(g.getUpcomingReservations());*/
        HotelApp hotelApp = new HotelApp();
        hotelApp.setVisible(true);
        //RoomReservationUI reservationInterface = new RoomReservationUI();
        //reservationInterface.display();

    }
}
