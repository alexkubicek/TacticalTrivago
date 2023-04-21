package edu.baylor.ecs.csi3471.hotelReservationSystem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Address;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.State;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AddressTester {
    Address a;
    final static Map<String,String> goodState;

    static {
        goodState = new HashMap<>();
        goodState.put("TX","Texas");
    }
    final static Map<String,String> badState;

    static {
        badState = new HashMap<>();
        badState.put("bang","Splat");
    }

    @ParameterizedTest
    @MethodSource("badAddressParts")
    public void constructBadAddress(Integer buildingNumber, Integer zipCode, String street, String city){
        assertThrows(IllegalArgumentException.class,()->{
            a = new Address(buildingNumber,zipCode,street,city,State.TX);
        });
    }
    private static Stream<Arguments> badAddressParts(){
        return Stream.of(
                arguments(-1,77584,"street","city"),
                arguments(1,1,"street","city"),
                arguments(1,-77584,"street","city"),
                arguments(1,77584,"1234","city"),
                arguments(1,77584,"street","1234")
        );
    }

    @Before
    public void init(){
        a = new Address();
    }
    @ParameterizedTest
    @ValueSource(ints = {-111})
    public void setBadBuildingNumber(Integer i){
        assertThrows(IllegalArgumentException.class,()->{
            a.setBuildingNumber(i);
        });
    }
    @ParameterizedTest
    @ValueSource(ints = {-111, 775841, 77, -77584})
    public void setBadZipcode(Integer i){
        assertThrows(IllegalArgumentException.class,()->{
            a.setZipCode(i);
        });
    }
    @ParameterizedTest
    @ValueSource(strings = {"1234"})
    public void setBadStreet(String s){
        assertThrows(IllegalArgumentException.class,()->{
            a.setStreet(s);
        });
    }
    @ParameterizedTest
    @ValueSource(strings = {"1234"})
    public void setBadCity(String s){
        assertThrows(IllegalArgumentException.class,()->{
            a.setCity(s);
        });
    }
}
