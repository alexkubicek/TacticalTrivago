package hotelReadWriteUtils.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.baylor.ecs.csi3471.hotelReservationSystem.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.Room;

public class CSVHotelUtils extends HotelReadWriteUtils{

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
    
		return hotel;
	}

	@Override
	protected void doSave(Hotel hotel) {
		// TODO Save the room and guest info into csv files
		
	}
	
}
