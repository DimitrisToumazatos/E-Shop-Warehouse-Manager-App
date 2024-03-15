package com.team12.warehousemanager;

import static org.junit.Assert.assertEquals;

import com.team12.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for the functions of the UserAccount class.
 */
public class TestUserAccount {
    UserAccount user;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        Address address = new Address("18344", "Greece", "Attica", "Athens", "Makrigianni", "12");
        Email email = new Email("hipimax283@mainoj.com");
        user = new UserAccount(1, "John", "123", address, email, "123");
    }

    /**
     * Tests the getOrderList method.
     */
    @Test
    public void getOrderListTest() {
        ArrayList<Order> temp = new ArrayList<>();
        LocalDate submission = LocalDate.of(2023, 12, 3);
        Order order = new Order(submission, "1", new MemoryInitializer().getUserAccountDAO().find(1));
        user.addToOrderList(order);
        temp.add(order);
        assertEquals(temp, user.getOrderList());
    }

    /**
     * Tests the addToOrderList method.
     */
    @Test
    public void addToOrderListTest() {
        LocalDate submission = LocalDate.of(2023, 12, 3);
        Order order = new Order(submission, "1", new MemoryInitializer().getUserAccountDAO().find(1));
        user.addToOrderList(order);
        assertEquals(1, user.getOrderList().size());
    }

    /**
     * Tests the getPassword method.
     */
    @Test
    public void getPasswordTest() {
        assertEquals("123", user.getPassword());
    }

    /**
     * Tests the setPassword method.
     */
    @Test
    public void setPasswordTest() {
        user.setPassword("321");
        assertEquals("321", user.getPassword());
    }

    /**
     * Tests the getInbox method.
     */
    @Test
    public void getInboxTest() {
        assertEquals(0, user.getInbox().size());
    }

    /**
     * Tests the addMessageToInbox method.
     */
    @Test
    public void addMessageToInboxTest() {
        assertEquals(0, user.getInbox().size());
        user.addMessageToInbox("Hi");
        assertEquals(1, user.getInbox().size());
    }
}
