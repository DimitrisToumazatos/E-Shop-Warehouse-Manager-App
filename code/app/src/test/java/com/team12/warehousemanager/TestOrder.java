package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.team12.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for the functions of the Order class.
 */
public class TestOrder {
    Order order;
    UserAccount user = new MemoryInitializer().getUserAccountDAO().find(1);
    Batch batchX;
    Batch batchY;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        LocalDate submission = LocalDate.of(2023, 12, 3);
        order = new Order(submission, "23", user);
        ProductType product = new ProductType("Depon", "Aspirin", "1", 5);

        LocalDate batchDate = LocalDate.of(2023, 12, 1);
        batchX = new Batch(batchDate, "1", 80, "P1B1");
        batchY = new Batch(batchDate, "1", 250, "P1B2");
        product.addBatch(batchX);
        product.addBatch(batchY);

        order.addOrderLine(product, 100);
    }

    /**
     * Tests the getOrderID method.
     */
    @Test
    public void getOrderIDTest() {
        assertEquals(order.getOrderID(), "23");
    }

    /**
     * Tests the getUserAccount method.
     */
    @Test
    public void getUserAccountTest() {
        assertEquals(user, order.getUserAccount());
    }

    /**
     * Tests the getBackOrderOption method.
     */
    @Test
    public void getBackOrderOptionTest() {
        LocalDate submission = LocalDate.of(2023, 12, 4);
        Order order1 = new Order(submission, "23", user, true);
        assertTrue(order1.getBackOrderOption());
    }

    /**
     * Tests the cancel method.
     */
    @Test
    public void cancelTest() {
        order.cancel();
        assertEquals("Cancelled", order.getStatus());
    }

    /**
     * Tests the getStatus method.
     */
    @Test
    public void getStatusTest() {
        assertEquals(order.getStatus(), "New");
    }

    /**
     * Tests the getStatus method.
     */
    @Test
    public void setBackOrderOptionTest() {
        UserAccount user = new UserAccount(6, "Dimitris Toumazatos", "123",
                new Address("18344", "Greece", "Attica", "Piraeus", "Makrigianni", "12"),
                new Email("toumazatos@mail.com"), "12");
        Order order1 = new Order("1", user);
        order1.setBackOrderOption(true);
        assertTrue(order1.getBackOrderOption());
    }

    /**
     * Tests the setStatus method.
     */
    @Test
    public void setStatusTest() {
        order.setStatus("Draft");
        assertEquals(order.getStatus(), "Draft");
    }

    /**
     * Tests the getDateOfSubmission method.
     */
    @Test
    public void getDateOfSubmissionTest() {
        LocalDate submission = LocalDate.of(2023, 12, 3);
        assertEquals(order.getDateOfSubmission(), submission);
    }

    /**
     * Tests the createOrderLine method.
     */
    @Test
    public void createOrderLineTest() {
        assertEquals(1, order.getOrderLineList().size());
    }

    /**
     * Tests the getTotalCost method.
     */
    @Test
    public void getTotalCostTest() {
        assertEquals(order.getTotalCost().getAmount(), 500.0, 0);
    }

    /**
     * Tests the execute method.
     */
    @Test
    public void executeTest() {
        order.execute();

        ArrayList<BatchAllocation> orderBatchAllocation = order.getBatchAllocationList();
        assertEquals(0, batchX.getRunningQuantity());
        assertEquals(230, batchY.getRunningQuantity());

        assertEquals(2, orderBatchAllocation.size());
        assertEquals(batchX, orderBatchAllocation.get(0).getBatch());
        assertEquals(batchY, orderBatchAllocation.get(1).getBatch());
        assertEquals(80, orderBatchAllocation.get(0).getQuantity());
        assertEquals(20, orderBatchAllocation.get(1).getQuantity());

        assertEquals(500.0f, order.getTotalCost().getAmount(), 0);
    }
}
