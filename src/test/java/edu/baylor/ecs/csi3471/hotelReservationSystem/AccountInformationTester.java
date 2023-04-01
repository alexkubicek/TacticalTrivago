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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IncludeClassNamePatterns({"*"})
public class AccountInformationTester {
    AccountInformation ac1, ac2, nullAC, sameUser;
    @Before
    public void initEmpty(){
        ac1 = new AccountInformation("alex","1234");
        ac2 = new AccountInformation("joe","4321");
        sameUser = new AccountInformation("alex","4321");
        nullAC = null;
    }

    @Test
    public void testEqualsNull(){
        assertFalse(ac1.equals(nullAC));
    }

    @Test
    public void testHashCodeNull(){
        assertThrows(NullPointerException.class,()->{nullAC.hashCode();});
    }

    @Test
    public void sameValueEquals(){
        assertTrue(ac1.equals(ac1));
    }

    @Test
    public void sameValueHashCode(){
        assertEquals(ac1.hashCode(),ac1.hashCode());
    }

    @Test
    public void notEqual(){
        ac1.setUsername("alex");
        ac1.setPassword("1234");
        ac2.setUsername("joe");
        ac2.setPassword("4321");
        assertFalse(ac1.equals(ac2));
    }

    @Test
    public void notEqualHashCode(){
        assertNotEquals(ac1,ac2);
    }

    @Test
    public void differentPasswords(){
        assertTrue(ac1.equals(sameUser));
    }

    @Test
    public void differentPasswordsHashCode(){
        assertEquals(ac1.hashCode(),sameUser.hashCode());
    }
}
