package hotelReadWriteUtils.java;
import java.io.FileNotFoundException;

//FIXME have a load guests function so that we're affecting the same hotel?
// Rename the class to HotelReadWriteUtils and ensure that it loads all info
// to initialize the fully populated hotel object? 

import edu.baylor.ecs.csi3471.hotelReservationSystem.*;

public abstract class HotelReadWriteUtils {
	//FIXME make sure the load function also loads users. Right now it only loads rooms into the hotel
	abstract public Hotel load() throws FileNotFoundException;
	
	public final void save(Hotel hotel) {
		hook(hotel);
		doSave(hotel);
	}
	
	abstract void doSave(Hotel hotel);
	
	protected Hotel hook(Hotel hotel) {
		return hotel;
	}
	

}
