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
public class GuestTester {
    Guest g;
    @Test
    public void constructorNull(){
        assertThrows(NullPointerException.class,()->{
            g = new Guest("Alex",null,null);
        });
    }
    @Test
    public void stringConstructor(){
        String[] line = {"Alex",null,"Alex","Password","false"};
        assertThrows(NullPointerException.class,()->{
            g = new Guest(line);
        });
    }
}
