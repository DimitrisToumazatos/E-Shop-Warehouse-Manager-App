package com.team12.warehousemanager;

import java.util.ArrayList;

/**
 * The Manager Account.
 * This class inherits methods and attributes from the AbstractAccount class
 * and adds the pending orders list to represent the account of the manager
 * and the orders that have not been executed yet.
 */
public class ManagerAccount extends AbstractAccount {
    private final ArrayList<AbstractOrder> pendingOrderList;

    /**
     * Constructor.
     * Sets up the basic attributes of a Manager account.
     *
     * @param id       The ID of the manager account
     * @param name     The name of the manager
     * @param TIN      The TIN of the manager
     * @param address  The address of the manager
     * @param email    The email of the manager
     * @param password The password of the manager
     */
    public ManagerAccount(int id, String name, String TIN, Address address, Email email, String password) {
        super(id, name, TIN, address, email, password);
        this.pendingOrderList = new ArrayList<>();
    }

    /**
     * Returns the list of pending orders of the manager.
     *
     * @return The list of pending orders of the manager
     */
    public ArrayList<AbstractOrder> getOrderList() {
        return pendingOrderList;
    }

    /**
     * Adds a new order to the pending orders list of the manager.
     *
     * @param newOrder The new order
     */
    public void addToPendingOrderList(AbstractOrder newOrder) {
        pendingOrderList.add(newOrder);
    }
}
