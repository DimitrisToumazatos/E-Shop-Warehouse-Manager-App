package com.team12.warehousemanager;

import static com.team12.warehousemanager.Email.isValid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the functions of the Email class.
 */
public class TestEmail {
    Email email;

    /**
     * Sets up the object before each the test.
     */
    @Before
    public void setUp() throws Exception {
        email = new Email("test@mail.com");
    }

    /**
     * Tests the setUsername method.
     */
    @Test
    public void setUsernameTest() {
        email.setUsername("Jake");
        assertEquals("Jake", email.getUsername());
    }

    /**
     * Tests the setDomain method.
     */
    @Test
    public void setDomainTest() {
        email.setDomain("mymail");
        assertEquals("mymail", email.getDomain());
    }

    /**
     * Tests the isValid method.
     */
    @Test
    public void isValidTest() {
        assertFalse(isValid(null));
    }
}
