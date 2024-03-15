package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for the functions of the ProductType class.
 */
public class TestProductType {

    ProductType p;

    /**
     * Sets up the object before each the test.
     */
    @Before
    public void setUp() {
        p = new ProductType("Depon", "Aspirin", "ABC12", 10.0f);
    }

    /**
     * Tests the getName method.
     */
    @Test
    public void getNameTest() {
        assertEquals("Depon", p.getName());
    }

    /**
     * Tests the getCategory method.
     */
    @Test
    public void getCategoryTest() {
        assertEquals("Aspirin", p.getCategory());
    }

    /**
     * Tests the getEOF method.
     */
    @Test
    public void getEOFTest() {
        assertEquals("ABC12", p.getEOF());
    }

    /**
     * Tests the getPrice method.
     */
    @Test
    public void getPriceTest() {
        assertEquals(10.0f, p.getPrice(), 0.0f);
    }

    /**
     * Tests the getTotalQuantity method.
     */
    @Test
    public void getTotalQuantityTest() {
        LocalDate d1 = LocalDate.of(2023, 12, 1);
        Batch b1 = new Batch(d1, "ABC12", 100, "D123");
        p.addBatch(b1);
        assertEquals(100, p.getTotalQuantity());
    }

    /**
     * Tests the getBatchList method.
     */
    @Test
    public void getBatchListTest() {
        ArrayList<Batch> temp = new ArrayList<>();
        LocalDate d = LocalDate.of(2023, 12, 2);
        Batch b = new Batch(d, "ABC12", 100, "D123");
        p.addBatch(b);
        temp.add(b);
        assertEquals(temp, p.getBatchList());
    }

    /**
     * Tests the setPrice method.
     */
    @Test
    public void setPriceTest() {
        p.setPrice(11.0f);
        assertEquals(11.0f, p.getPrice(), 0.0f);
    }

    /**
     * Tests the hasQuantity method.
     */
    @Test
    public void hasQuantityTest() {
        assertFalse(p.hasQuantity(0));
        assertFalse(p.hasQuantity(-1));
        assertFalse(p.hasQuantity(101));
    }

    /**
     * Tests the addBatch method.
     */
    @Test
    public void addBatchTest() {
        LocalDate d = LocalDate.of(2023, 12, 2);
        Batch b1 = new Batch(d, "ABC12", 100, "D123");
        p.addBatch(b1);
        assertEquals(1, p.getBatchList().size());
    }

    /**
     * Tests the orderProduct method.
     */
    @Test
    public void orderProductTest() {
        ProductType orderedProduct = new ProductType("Panadol", "Meds", "P1", 5);
        LocalDate batchDate = LocalDate.of(2023, 12, 1);
        Batch batchX = new Batch(batchDate, "P1", 10, "P1B1");
        Batch batchY = new Batch(batchDate, "P1", 50, "P1B2");
        orderedProduct.addBatch(batchX);
        orderedProduct.addBatch(batchY);
        ArrayList<BatchAllocation> productBatchAllocation = new ArrayList<>();

        orderedProduct.orderProduct(25, productBatchAllocation);
        orderedProduct.orderProduct(0, productBatchAllocation);
        assertEquals(0, batchX.getRunningQuantity());
        assertEquals(35, batchY.getRunningQuantity());

        assertEquals(2, productBatchAllocation.size());
        assertEquals(batchX, productBatchAllocation.get(0).getBatch());
        assertEquals(batchY, productBatchAllocation.get(1).getBatch());
        assertEquals(10, productBatchAllocation.get(0).getQuantity());
        assertEquals(15, productBatchAllocation.get(1).getQuantity());
    }
}
