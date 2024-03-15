package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the functions of the OrderLine class.
 */
public class TestOrderLine {
    OrderLine orderLine;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        ProductType product = new ProductType("Depon", "Meds", "A43", 5);
        orderLine = new OrderLine(product, 100);
    }

    /**
     * Tests the getOrderedQuantity method.
     */
    @Test
    public void getOrderedQuantityTest() {
        assertEquals(100, orderLine.getOrderedQuantity());
    }

    /**
     * Tests the getName method.
     */
    @Test
    public void getNameTest() {
        assertEquals("Depon", orderLine.getName());
    }

    /**
     * Tests the getEOF method.
     */
    @Test
    public void getEOFTest() {
        assertEquals("A43", orderLine.getEOF());
    }

    /**
     * Tests the setOrderedQuantity method.
     */
    @Test
    public void setOrderedQuantityTest() {
        orderLine.setOrderedQuantity(15);
        assertEquals(15, orderLine.getOrderedQuantity());
    }

    /**
     * Tests the getSubtotal method.
     */
    @Test
    public void getSubtotalTest() {
        assertEquals(500f, orderLine.getSubtotal().getAmount(), 0.0f);
    }

    /**
     * Tests the getPendingQuantity method.
     */
    @Test
    public void getPendingQuantityTest() {
        assertEquals(100.0f, orderLine.getPendingQuantity(), 0.0f);
    }
}
