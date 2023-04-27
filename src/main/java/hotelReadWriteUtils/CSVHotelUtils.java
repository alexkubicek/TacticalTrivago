package hotelReadWriteUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

public class CSVHotelUtils extends HotelReadWriteUtils {
    // loads "database" for hotel (rooms, current reservations, all user types)
	@Override
	public Hotel load() throws FileNotFoundException {
        Hotel hotel = new Hotel();
        String file = "src/main/resources/TacticalTrivagoRooms.csv";
        BufferedReader reader = null;
        try {
            // read rooms from file
            reader = new BufferedReader(new FileReader(file));
            // ignore header line
            String line;
            reader.readLine();
            // read each row
            String[] split;
            List<Room> rooms = new ArrayList<>();
            Room r;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                r = new Room(split);
                rooms.add(r);
            }
            Hotel.rooms = rooms;

            // read guests from file
            file = "src/main/resources/TacticalTrivagoGuests.csv";
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            List<Guest> guests = new ArrayList<>();
            Guest g;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                g = new Guest(split);
                guests.add(g);
            }
            // add guests to the hotel
            Hotel.addAccounts(guests);

            // read clerks from file
            file = "src/main/resources/TacticalTrivagoClerks.csv";
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            List<Clerk> clerks = new ArrayList<>();
            Clerk c;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                c = new Clerk(split);
                clerks.add(c);
            }
            // add clerks to the hotel
            Hotel.addAccounts(clerks);

            // read admins from file
            file = "src/main/resources/TacticalTrivagoAdmins.csv";
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            List<Admin> admins = new ArrayList<>();
            Admin a;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                a = new Admin(split);
                admins.add(a);
            }
            // add admins to the hotel
            Hotel.addAccounts(admins);

            // read reservations from file
            file = "src/main/resources/TacticalTrivagoReservations.csv";
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            List<Reservation> reservations = new ArrayList<>();
            Reservation res;
            while ((line = reader.readLine()) != null) {
                split = line.split(",");
                res = new Reservation(split);
                reservations.add(res);
            }
            // add reservations to the hotel
            Hotel.setReservations(reservations);

            for(Reservation reservation : Hotel.getReservations()){
                Guest guestOnReservation = reservation.getGuest();
                guestOnReservation.addUpcomingReservations(reservation);
            }

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
