package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test class for the functions of the BatchAllocation class.
 */
public class TestBatchAllocation {
    private BatchAllocation batchAllocation;
    private Batch batch;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        LocalDate batchDate = LocalDate.of(2023, 12, 1);
        batch = new Batch(batchDate, "A1", 10, "A1B1");
        batchAllocation = new BatchAllocation(batch, 10);
    }

    /**
     * Tests the getBatch method.
     */
    @Test
    public void getBatchTest() {
        assertEquals(batch, batchAllocation.getBatch());
    }

    /**
     * Tests the getQuantity method.
     */
    @Test
    public void getQuantityTest() {
        assertEquals(10, batchAllocation.getQuantity());
    }
}
