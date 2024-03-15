package com.team12.warehousemanager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the functions of the Address class.
 */
public class TestAddress {
    Address address;

    /**
     * Sets up an object before each the test.
     */
    @Before
    public void setUp() {
        address = new Address("18198", "Greece", "Attica", "Athens", "Vouliagmenis", "134");
    }

    /**
     * Tests the setBuildingNumber method.
     */
    @Test
    public void setBuildingNumberTest() {
        address.setBuildingNumber("1");
        assertEquals("1", address.getBuildingNumber());
    }

    /**
     * Tests the setPostalCode method.
     */
    @Test
    public void setPostalCodeTest() {
        address.setPostalCode("234");
        assertEquals("234", address.getPostalCode());
    }

    /**
     * Tests the setCountry method.
     */
    @Test
    public void setCountryTest() {
        address.setCountry("America");
        assertEquals("America", address.getCountry());
    }

    /**
     * Tests the setState method.
     */
    @Test
    public void setStateTest() {
        address.setState("Washington");
        assertEquals("Washington", address.getState());
    }

    /**
     * Tests the setStreet method.
     */
    @Test
    public void setStreetTest() {
        address.setStreet("Long George");
        assertEquals("Long George", address.getStreet());
    }

    /**
     * Tests the setCity method.
     */
    @Test
    public void setCityTest() {
        address.setCity("LA");
        assertEquals("LA", address.getCity());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void toStringTest() {
        assertEquals("Vouliagmenis 134, Athens 18198, Attica, Greece", address.toString());
    }
}
