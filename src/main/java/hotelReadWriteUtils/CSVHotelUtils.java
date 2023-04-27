/**
 * file: CSVHotelUtils.java
 * author: KayLynn Beard
 *
 * Loads Hotel from CSV files and saves Hotel
 * to CSV files so data persists
 */

package hotelReadWriteUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.*;

public class CSVHotelUtils {
    // loads "database" for hotel (rooms, current reservations, all user types)
	public static Hotel load() throws FileNotFoundException {
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


    // save "database" for hotel
    public static void doSave(Hotel hotel) {
        FileWriter file = null;
        BufferedWriter writer = null;
        try{
            file = new FileWriter("src/main/resources/TacticalTrivagoRooms.csv");
            writer = new BufferedWriter(file);
            writer.write("roomNumber,numberOfBeds,smoking,qualityLevel,bedType,currentCleanStatus,datesBooked\n");
            for (Room room: Hotel.rooms) {
                writer.write(room.getRoomNumber() + "," + room.getBedCount() + ","
                        + room.getSmoking() + "," + room.getQuality() + "," + room.getBedSize()
                        + "," + room.getStatus());
                List<Date> unavailable = room.getUnavailable();
                if(unavailable.size() > 0){
                    for(Date d : unavailable){
                        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                        writer.write("," + formatter.format(d));
                    }
                }
                writer.write("\n");
            }
            writer.flush();

            file = new FileWriter("src/main/resources/TacticalTrivagoGuests.csv");
            writer = new BufferedWriter(file);
            writer.write("nameFirst,nameLast,username,password,isCorporate,cardNum,cvv,expiration,addressSeparatedByPeriods,name\n");
            for (Guest g: Hotel.getGuestAccounts()) {
                writer.write(g.getNameFirst() + "," + g.getNameLast() + ","
                        + g.getAccountUsername() + "," + g.getAccountPassword() + "," + g.corporate());
                CreditCard card = g.getPaymentMethod();
                if(card != null) {
                    writer.write("," + card.getCardNum() + "," + card.getCvv() + ",");
                    Date date = card.getExpiration();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                    writer.write("," + formatter.format(date));
                    Address address = card.getAddress();
                    writer.write("," + address.getBuildingNumber() + "." + address.getZipCode() + "."
                            + address.getStreet() + "." + address.getCity() + "." + address.getState());
                    writer.write("," + card.getName());
                }
                writer.write("\n");
            }
            writer.flush();

            file = new FileWriter("src/main/resources/TacticalTrivagoClerks.csv");
            writer = new BufferedWriter(file);
            writer.write("nameFirst,nameLast,username,password\n");
            for (Clerk c : Hotel.getClerkAccounts()) {
                writer.write(c.getNameFirst() + "," + c.getNameLast() + ","
                        + c.getAccountUsername() + "," + c.getAccountPassword() + "\n");
            }
            writer.flush();


            file = new FileWriter("src/main/resources/TacticalTrivagoAdmins.csv");
            writer = new BufferedWriter(file);
            writer.write("nameFirst,nameLast,username,password\n");
            for (Admin a : Hotel.getAdminAccounts()) {
                writer.write(a.getNameFirst() + "," + a.getNameLast() + ","
                        + a.getAccountUsername() + "," + a.getAccountPassword() + ","
                        + a.getAdminId() + "\n");
            }
            writer.flush();


            file = new FileWriter("src/main/resources/TacticalTrivagoReservations.csv");
            writer = new BufferedWriter(file);
            writer.write("room,checkInDate,checkOutDate,guestUsername\n");
            for (Reservation reservation: Hotel.getReservations()) {
                List<Room> rooms = reservation.getRooms();
                int roomNum = rooms.get(0).getRoomNumber();
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                writer.write(roomNum + "," + formatter.format(reservation.getStartDate()) +
                        "," + formatter.format(reservation.getEndDate()));
                writer.write("," + reservation.getGuest().getAccountUsername() + "\n");
            }
            writer.flush();


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
