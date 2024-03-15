package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;

import com.team12.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for the functions of the ManagerAccount class.
 */
public class TestManagerAccount {
    ManagerAccount account;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        Address address = new Address("18344", "Greece", "Attica", "Athens", "Makrigianni", "12");
        Email email = new Email("hipimax283@mainoj.com");
        account = new ManagerAccount(1, "John", "123", address, email, "123");
    }

    /**
     * Tests the getId method.
     */
    @Test
    public void getIdTest() {
        assertEquals(1, account.getId());
    }

    /**
     * Tests the getName method.
     */
    @Test
    public void getNameTest() {
        assertEquals("John", account.getName());
    }

    /**
     * Tests the getTIN method.
     */
    @Test
    public void getTINTest() {
        assertEquals("123", account.getTIN());
    }

    /**
     * Tests the getAddress method.
     */
    @Test
    public void getAddressTest() {
        assertEquals("18344", account.getAddress().getPostalCode());
        assertEquals("12", account.getAddress().getBuildingNumber());
        assertEquals("Attica", account.getAddress().getState());
        assertEquals("Greece", account.getAddress().getCountry());
        assertEquals("Makrigianni", account.getAddress().getStreet());
    }

    /**
     * Tests the getEmail method.
     */
    @Test
    public void getEmailTest() {
        assertEquals("mainoj.com", account.getEmail().getDomain());
        assertEquals("hipimax283", account.getEmail().getUsername());
    }

    /**
     * Tests the getPendingOrderList method.
     */
    @Test
    public void getPendingOrderListTest() {
        ArrayList<Order> temp = new ArrayList<>();
        LocalDate submission = LocalDate.of(2023, 12, 3);
        Order order = new Order(submission, "1", new MemoryInitializer().getUserAccountDAO().find(1));
        account.addToPendingOrderList(order);
        temp.add(order);
        assertEquals(temp, account.getOrderList());
    }

    /**
     * Tests the setName method.
     */
    @Test
    public void setNameTest() {
        account.setName("John");
        assertEquals("John", account.getName());
    }

    /**
     * Tests the setTIN method.
     */
    @Test
    public void setTINTest() {
        account.setTIN("123");
        assertEquals("123", account.getTIN());
    }

    /**
     * Tests the setAddress method.
     */
    @Test
    public void setAddressTest() {
        Address address = new Address("18111", "Italy", "Rome District", "Rome", "via Bella", "34");
        account.setAddress(address);
        assertEquals("Italy", account.getAddress().getCountry());
        assertEquals("34", account.getAddress().getBuildingNumber());
        assertEquals("Rome District", account.getAddress().getState());
        assertEquals("Rome", account.getAddress().getCity());
        assertEquals("via Bella", account.getAddress().getStreet());
        assertEquals("18111", account.getAddress().getPostalCode());
    }

    /**
     * Tests the setEmail method.
     */
    @Test
    public void setEmailTest() {
        account.setEmail("hipimax283@mainoj.com");
        assertEquals("hipimax283@mainoj.com", account.getEmail().toString());
    }

    /**
     * Tests the addToPendingOrderList method.
     */
    @Test
    public void addToPendingOrderListTest() {
        LocalDate submission = LocalDate.of(2023, 12, 3);
        Order order = new Order(submission, "1", new MemoryInitializer().getUserAccountDAO().find(1));
        account.addToPendingOrderList(order);
        assertEquals(1, account.getOrderList().size());
    }
}
