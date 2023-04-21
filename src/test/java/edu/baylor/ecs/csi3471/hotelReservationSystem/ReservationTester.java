package edu.baylor.ecs.csi3471.hotelReservationSystem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.CleanStatus;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Reservation;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@IncludeClassNamePatterns({"*"})
public class ReservationTester {
    Reservation r;
    @ParameterizedTest
    @MethodSource("badReservationData")
    public void testConstructor(Date s, Date e, Guest g, List<Room> rooms, Hotel h){
        assertThrows(IllegalArgumentException.class,()->{
            r = new Reservation(s,e,g,rooms);
        });
    }
    private Stream<Arguments> badReservationData(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2023);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date start = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,5);
        Date end = cal.getTime();
        Guest g = new Guest();
        List<Room> roomList = new ArrayList<>();
        roomList.add(new MockRoom());
        Hotel h = new Hotel();
        return Stream.of(
                arguments(end,start,g,roomList,h)
        );
    }

    @Before
    public void init(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2023);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date start = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,5);
        Date end = cal.getTime();
        Guest g = new Guest();
        List<Room> roomList = new ArrayList<>();
        roomList.add(new MockRoom());
        Hotel h = new Hotel();
        r = new Reservation(start,end,g,roomList);
    }
    @Test
    public void testNights(){
        assertEquals(r.getNights(),4);
    }
    @Test
    public void testRate(){
        assertEquals(r.getRate(),80.0);
    }
    @Test
    public void testCheckIn(){
        r.checkIn();
        assertEquals(r.getRooms().get(0).clean_status,CleanStatus.OCCUPIED);
    }
    @Test
    public void testCheckOut(){
        r.checkOut();
        assertEquals(r.getRooms().get(0).clean_status,CleanStatus.DIRTY);
    }
    @Test
    public void testCalcTotal(){
        assertEquals(r.calculateTotal(),80*4);
    }
    @Test
    public void testCancel(){
        r.cancel();
        assertTrue(Hotel.reservations.isEmpty());
    }
}
