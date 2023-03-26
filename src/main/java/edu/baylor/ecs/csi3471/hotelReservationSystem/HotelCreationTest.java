package edu.baylor.ecs.csi3471.hotelReservationSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelCreationTest {
    private static Hotel hotel = new Hotel();

    static void loadRoomsFromCSV(String file) throws FileNotFoundException {
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
            hotel.setRooms(rooms);
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
        // fill Hotel with rooms
        try{
            loadRoomsFromCSV("src/main/resources/testRooms1.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }

        System.out.println("Displaying all rooms in the hotel...");
        hotel.displayAllRooms();


        RoomReservationUI reservationInterface = new RoomReservationUI();
        reservationInterface.display(hotel);
    }
}
