package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;

import com.team12.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test class for the functions of the BackOrder class.
 */
public class TestBackOrder {
    BackOrder backOrder;
    ProductType product;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        LocalDate submission = LocalDate.of(2023, 12, 3);
        product = new ProductType("Depon", "Meds", "A43", 5);
        backOrder = new BackOrder(submission, "B1", product, 100, new MemoryInitializer().getUserAccountDAO().find(1));
    }

    /**
     * Test the superConstructor method.
     */
    @Test
    public void superConstructorTest() {
        LocalDate submission = LocalDate.of(2023, 12, 3);
        assertEquals(submission, backOrder.getDateOfSubmission());
        assertEquals("B1", backOrder.getOrderID());
    }

    /**
     * Test the getProduct method.
     */
    @Test
    public void getProductTest() {
        assertEquals(product, backOrder.getBackOrderedProduct());
    }
}
