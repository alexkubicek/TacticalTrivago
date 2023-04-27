/*
package hotelReadWriteUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JAXBHotelUtils extends HotelReadWriteUtils {

	@Override
	public Hotel load() throws FileNotFoundException {
		Hotel hotel = null;
		try {
			
			String inputFile = "src/main/resources/testRooms1.xml";
			JAXBContext context = JAXBContext.newInstance(Hotel.class);
			Unmarshaller um = context.createUnmarshaller();
			hotel = (Hotel) um.unmarshal(new FileReader(inputFile));
	        List<Room> list = hotel.getRooms();
		} catch (JAXBException e) {
			e.printStackTrace();
        }
		return hotel;
	}
	
	@Override
	void doSave(Hotel hotel) {
		try {
			String outputFile = "output-JAXB.xml";
			
			JAXBContext context = JAXBContext.newInstance(Hotel.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        // Write to File
	        m.marshal(hotel, new File(outputFile));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
*/
