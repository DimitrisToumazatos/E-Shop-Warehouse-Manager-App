package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test class for the functions of the Batch class.
 */
public class TestBatch {
    private Batch b;
    private LocalDate d;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        d = LocalDate.of(2023, 12, 2);
        b = new Batch(d, "ABC12", 100, "D123");
    }

    /**
     * Tests the getDate method.
     */
    @Test
    public void getDateTest() {
        assertEquals(b.getDate(), d);
    }

    /**
     * Tests the getEOF method.
     */
    @Test
    public void getEOFTest() {
        assertEquals(b.getEOF(), "ABC12");
    }

    /**
     * Tests the getInitialQuantity method.
     */
    @Test
    public void getInitialQuantityTest() {
        assertEquals(b.getInitialQuantity(), 100);
    }

    /**
     * Tests the getBatchID method.
     */
    @Test
    public void getBatchIDTest() {
        assertEquals(b.getBatchID(), "D123");
    }

    /**
     * Tests the increaseQuantity method.
     */
    @Test
    public void increaseQuantityTest() {
        b.increaseQuantity(10);
        assertEquals(b.getRunningQuantity(), 110);
    }

    /**
     * Tests the reduceQuantity method.
     */
    @Test
    public void reduceQuantityTest() {
        b.reduceQuantity(20);
        assertEquals(b.getRunningQuantity(), 80);
    }

    /**
     * Tests the hasQuantity method.
     */
    @Test
    public void hasQuantityTest() {
        assertTrue(b.hasQuantity(100));
        assertFalse(b.hasQuantity(110));
        assertFalse(b.hasQuantity(-1));
        assertFalse(b.hasQuantity(0));
    }
}
