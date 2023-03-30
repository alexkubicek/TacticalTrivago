package edu.baylor.ecs.csi3471.hotelReservationSystem;

import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@IncludeClassNamePatterns({"*"})
public class ReservationSystemTester {

    @Test
    public void loadNullCSV(){
        assertThrows(NullPointerException.class, () ->{
           ReservationSystem.loadRoomsFromCSV(null);
        });
    }

    @Test
    public void loadInvalidPath(){
        assertThrows(FileNotFoundException.class, () ->{
            ReservationSystem.loadRoomsFromCSV("invalidFilePath.csv");
        });
    }

    @Test
    public void loadEmptyCSV() throws FileNotFoundException {
        ReservationSystem.loadRoomsFromCSV("src/main/resources/empty.csv");
        assertTrue(true); //good as long as no exceptions thrown
    }

    @Test
    public void loadWrongFileType(){
        assertThrows(RuntimeException.class, () ->{
           ReservationSystem.loadRoomsFromCSV("src/main/resources/wrongFileType.txt");
        });
    }
}
