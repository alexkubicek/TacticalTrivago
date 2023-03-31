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
public class AdminTester {
    Admin a;
    @Before
    public void initEach(){
        a = new Admin(0,"testFirst","testLast",new AccountInformation());
    }

    @Test
    public void createClerkNullUser(){
        assertThrows(NullPointerException.class,()->{
           a.createClerk(null,"testFirst","testLast");
        });
    }
    @Test
    public void createClerkNullFirst(){
        assertThrows(NullPointerException.class,()->{
            a.createClerk("testUser",null,"testLast");
        });
    }
    @Test
    public void createClerkNullLast(){
        assertThrows(NullPointerException.class,()->{
            a.createClerk("testUser","testFirst",null);
        });
    }
    @Test
    public void createClerkSuccess(){
        Clerk c = a.createClerk("testUser","testFirst","testLast");
        Clerk shouldBe = new Clerk("testFirst","testLast",new AccountInformation("testUser","testPass"));
        assertEquals(c,shouldBe);
    }
}
