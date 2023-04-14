package hotelReadWriteUtils.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import edu.baylor.ecs.csi3471.hotelReservationSystem.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.Room;
import edu.baylor.ecs.csi3471.hotelReservationSystem.User;

public class CSVHotelUtils extends HotelReadWriteUtils{

    // TODO Check if separate function would clean up code/be more efficient
    /**
     * public String[] stringCut(String s){
     *         String[] ss = s.split(",");
     *         return ss;
     *     }
     */
	@Override
	public Hotel load() throws FileNotFoundException {
		Hotel hotel = new Hotel();
		String file = "src/main/resources/testRooms1.csv";
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
            String[] split;     //WE SHOULD NOT BE MAKING A NEW ONE EACH LOOP
            Room r;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                r = new Room(split);
                rooms.add(r);
            }
            Hotel.rooms = (rooms);

            // This is also opening a new file and using the same
            // variables to see if it can read from the guest file
            file = "src/main/resources/testUsers1.csv";
            reader = new BufferedReader(new FileReader(file));
            //ignore header
            reader.readLine();

            List<Guest> guests = new ArrayList<>();
            Guest g;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                g = new Guest(split);
                guests.add(g);
            }
            //adds the guests (users) to the hotel
            hotel.addAccounts(guests);
            //hotel.setGuests(guests);        //THIS LINE IS FOR COPYING INTO BOTH THE GUEST AND USERS

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
    
		return hotel;
	}

	@Override
	protected void doSave(Hotel hotel) {
		// TODO Save the room and guest info into csv files
        FileWriter file = null;
        BufferedWriter writer = null;
        try{
            file = new FileWriter("testOutputRooms.csv");
            writer = new BufferedWriter(file);

            writer.write("roomNumber,numberOfBeds,smoking,qualityLevel,bedType\n");

            //TODO check if it can output booleans, CSV SHOULD BE ABLE TO OUTPUT THE room.getSmoking() FUNCTION
            String str = ""; //just in case set it to nothing
            for (Room room: Hotel.rooms) {
                writer.write(room.getRoomNumber() + "," + room.getBedCount() + ","
                        + room.getSmoking() + ",");

                switch(room.getQuality()){
                    case EXECUTIVE: str = "EXECUTIVE"; break;
                    case BUSINESS: str = "BUSINESS"; break;
                    case COMFORT: str = "COMFORT"; break;
                    case ECONOMY: str = "ECONOMY"; break;
                }
                //str should always become a string and must import the file for enum values
                writer.write(str + "," + room.getBedSize() + "\n");
            }
            //flush for first file
            writer.flush();

            file = new FileWriter("testOutputRooms.csv");
            writer = new BufferedWriter(file);
            writer.write("nameFirst,nameLast,username,password,isCorporate");
            for (User guest: hotel.accounts){
                writer.write(guest.getNameFirst() + "," + guest.getNameLast() + "," +
                        guest.getAccountUsername() + "," + guest.getAccountPassword()); //should be true/false or 0/1
            }

            writer.flush();
            writer.close(); //btw we dont need this
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
	}
	
}
