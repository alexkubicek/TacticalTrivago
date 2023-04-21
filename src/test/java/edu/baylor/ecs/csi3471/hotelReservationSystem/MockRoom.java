package edu.baylor.ecs.csi3471.hotelReservationSystem;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

public class MockRoom extends Room {
    public MockRoom() {
        quality = QualityLevel.BUSINESS;
    }
}
