package edu.baylor.ecs.csi3471.hotelReservationSystem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.BedType;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.QualityLevel;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Room;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@IncludeClassNamePatterns({"*"})
public class RoomTester {
    Room r;
    @ParameterizedTest
    @MethodSource("badRoomParts")
    public void constructBadAddress(Integer roomNumber, Integer bedCount, Boolean smoking, QualityLevel quality, BedType bedSizes){
        assertThrows(IllegalArgumentException.class,()->{
            r = new Room(roomNumber,bedCount,smoking,quality,bedSizes);
        });
    }
    private static Stream<Arguments> badRoomParts(){
        return Stream.of(
                arguments(-1,2,false,QualityLevel.COMFORT,BedType.QUEEN),
                arguments(1,-2,false,QualityLevel.COMFORT,BedType.QUEEN),
                arguments(1,2,null,QualityLevel.COMFORT,BedType.QUEEN),
                arguments(1,2,false,"economy",BedType.QUEEN),
                arguments(1,2,false,QualityLevel.COMFORT,"Queen")
        );
    }
    @ParameterizedTest
    @ValueSource(strings = {})
    public void arrayConstructorFailures(String s){
        String[] line = s.split(",");
        r = new Room(line);
    }

    @ParameterizedTest
    @MethodSource("dates")
    public void bookAndCheckAvailable(Date bookedStart, Date bookedEnd, Date checkAvailStart, Date checkAvailEnd){
        r = new Room();
        r.bookRoom(bookedStart,bookedEnd);
        assertFalse(r.isAvailable(checkAvailStart,checkAvailEnd));
    }
    private static Stream<Arguments> dates(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2023);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dateFirst = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 5);
        Date dateSecond = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,10);
        Date dateThird = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,15);
        Date dateFourth = cal.getTime();
        return Stream.of(
                arguments(dateFirst,dateFourth,dateSecond,dateThird),
                arguments(dateFirst,dateThird,dateSecond,dateFourth),
                arguments(dateSecond,dateFourth,dateFirst,dateThird),
                arguments(dateFirst,dateSecond,dateFirst,dateSecond),
                arguments(dateFirst,dateSecond,dateSecond,dateFourth)
        );
    }
}
