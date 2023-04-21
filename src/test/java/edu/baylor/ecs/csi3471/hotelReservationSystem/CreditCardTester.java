package edu.baylor.ecs.csi3471.hotelReservationSystem;
import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.CreditCard;

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
public class CreditCardTester {
    CreditCard c;
    @Test
    public void validCardNum(){
        //do something with size if too large or too small then invalid
    }
    @Test
    public void Cvv(){
        //do something with size and input specifics to make sure its a real Cvv
    }

    @Test
    public void UserName(){
        //do something with size and input specifics to make sure it follows
        //username rules if there is a set max length or if the username already exists
    }
}
