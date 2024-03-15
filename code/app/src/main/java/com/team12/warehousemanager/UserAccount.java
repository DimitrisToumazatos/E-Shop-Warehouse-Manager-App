package com.team12.warehousemanager;

import java.util.ArrayList;

/**
 * The Customer/User account.
 * This class inherits methods and attributes from the AbstractAccount class
 * and adds the orders list to represent the account of a customer.
 * This list has all orders that have been submitted by the customer
 * (both Draft Orders, Back Orders and Pending Orders).
 */
public class UserAccount extends AbstractAccount {
    private final ArrayList<AbstractOrder> orderList;

    private final ArrayList<String> inbox;

    /**
     * Constructor.
     * Sets up the basic attributes of an User Account.
     *
     * @param id       The ID of the customer's account
     * @param name     The name of the customer
     * @param TIN      The TIN of the customer
     * @param address  The address of the customer
     * @param email    The email of the customer
     * @param password The password of the password
     */
    public UserAccount(int id, String name, String TIN, Address address, Email email, String password) {
        super(id, name, TIN, address, email, password);
        this.orderList = new ArrayList<>();
        this.inbox = new ArrayList<>();
    }

    /**
     * Returns the list of the orders the customer has made.
     *
     * @return The list of the orders the customer has made
     */
    public ArrayList<AbstractOrder> getOrderList() {
        return orderList;
    }

    /**
     * Adds a new order to the list of orders of the customer.
     *
     * @param newOrder The new order
     */
    public void addToOrderList(AbstractOrder newOrder) {
        orderList.add(newOrder);
    }

    /**
     * Adds a new message to the User's Inbox.
     *
     * @param message The Message
     */
    public void addMessageToInbox(String message) {
        this.inbox.add(message);
    }

    /**
     * Returns the User's Inbox.
     *
     * @return The User's Inbox
     */
    public ArrayList<String> getInbox() {
        return inbox;
    }
}
