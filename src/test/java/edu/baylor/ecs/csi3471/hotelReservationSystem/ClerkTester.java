package edu.baylor.ecs.csi3471.hotelReservationSystem;
import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.AccountInformation;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Clerk;

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
public class ClerkTester {
    Clerk c;
    @Before
    public void initEach(){
        c = new Clerk("testFirst","testLast", new AccountInformation("testUser","testPass"));
    }
    @Test
    public void fullNameTest(){
        assertEquals(c.getFullName(),"testFirst testLast");
    }
}
