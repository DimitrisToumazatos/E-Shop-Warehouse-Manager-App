package com.team12.warehousemanager;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Abstract Order.
 * This class has features essential for both the Orders and the Back Orders .
 */
public abstract class AbstractOrder {
    protected final ArrayList<BatchAllocation> batchAllocationList; // Batch - Quantity pairings sued for recalls
    protected final ArrayList<OrderLine> orderLineList;
    protected final LocalDate dateOfSubmission;
    protected final String orderID;
    protected final UserAccount user;
    protected final Money totalCost;
    protected String status;

    /**
     * Constructor.
     * Sets up the basic attributes of an Order.
     *
     * @param dateOfSubmission The date of Submission of the Order
     * @param orderID          The ID of the order
     * @param user             The user who submitted this order
     */
    public AbstractOrder(LocalDate dateOfSubmission, String orderID, UserAccount user) {
        this.dateOfSubmission = dateOfSubmission;
        this.orderID = orderID;
        this.user = user;
        this.status = "New";
        this.orderLineList = new ArrayList<>();
        this.batchAllocationList = new ArrayList<>();
        this.totalCost = new Money(0);
    }

    /**
     * Sets the order as "Executed" and
     * calculates the total cost of the order.
     */
    public void execute() {
        totalCost.setAmount(0f);
        for (OrderLine orderLine : orderLineList) {
            orderLine.execute(batchAllocationList);
            totalCost.addition(orderLine.getSubtotal());
        }
        status = "Executed";
    }

    /**
     * Calculates and returns the total cost of the order
     *
     * @return The total cost of the order
     */
    public Money getTotalCost() {
        totalCost.setAmount(0f);
        for (OrderLine orderLine : orderLineList) {
            totalCost.addition(orderLine.getSubtotal());
        }
        return totalCost;
    }

    /**
     * Returns the ID of the order.
     *
     * @return The ID of the order
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Returns the account of the user
     * who submitted the order
     *
     * @return The UserAccount of the order submitter
     */
    public UserAccount getUserAccount() {
        return user;
    }

    /**
     * Returns the status of the order.
     *
     * @return The status of the order
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the order status of the order.
     *
     * @param status The status of the order
     */
    public void setStatus(String status) {
        // TODO: Change to predefined status rather than custom input
        this.status = status;
    }

    /**
     * Returns the date of submission of the order.
     *
     * @return The date of submission of the order
     */
    public LocalDate getDateOfSubmission() {
        return this.dateOfSubmission;
    }

    /**
     * Returns the list of order lines.
     *
     * @return The list of order lines
     */
    public ArrayList<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    /**
     * Returns the list of the BatchAllocation objects of the order.
     *
     * @return The list of the BatchAllocation objects of the order
     */
    public ArrayList<BatchAllocation> getBatchAllocationList() {
        return batchAllocationList;
    }
}
