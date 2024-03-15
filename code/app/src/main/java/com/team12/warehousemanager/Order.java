package com.team12.warehousemanager;

import java.time.LocalDate;

/**
 * The Order.
 * It represents an order made by the customer.
 * It can be saved as a draft order to be completed in the future or
 * it can be set to the managers inbox and wait to be executed.
 * It contains 0 or more order lines.
 */
public class Order extends AbstractOrder {

    private boolean backOrderOption;

    /**
     * Constructor.
     * Simpler constructor, sets the back Order Option as false by default
     *
     * @param orderId The Order ID
     * @param user    The User
     */
    public Order(String orderId, UserAccount user) {
        super(LocalDate.now(), orderId, user);
        this.backOrderOption = true;
    }

    /**
     * Overloaded Constructor for Testing
     * Sets up the basic attributes of an Order.
     *
     * @param dateOfSubmission The date of submission of the order
     * @param orderID          The ID of the order
     * @param user             The account of the user who submitted the order
     * @param backOrderOption  Whether the option is eligible for a backorder
     */
    public Order(LocalDate dateOfSubmission, String orderID, UserAccount user, boolean backOrderOption) {
        super(dateOfSubmission, orderID, user);
        this.backOrderOption = backOrderOption;
    }

    /**
     * Overloaded Constructor for Testing
     * Run the above constructor with backOrderOption as false
     *
     * @param dateOfSubmission The date of submission of the order
     * @param orderID          The ID of the order
     * @param user             The account of the user who submitted the order
     */
    public Order(LocalDate dateOfSubmission, String orderID, UserAccount user) {
        this(dateOfSubmission, orderID, user, false);
    }

    /**
     * Creates a new order line with the given ProductType
     * and quantity and adds it to the list of order lines.
     *
     * @param product  The ProductType
     * @param quantity The quantity of the given product
     */
    public void addOrderLine(ProductType product, int quantity) {
        OrderLine newOrderLine = new OrderLine(product, quantity);
        orderLineList.add(newOrderLine);
    }

    /**
     * Returns whether the order is eligible for a backorder
     *
     * @return True if the order is eligible for a backorder
     */
    public boolean getBackOrderOption() {
        return this.backOrderOption;
    }

    /**
     * Sets the backOrderOption.
     *
     * @param backOrderOption The backOrderOption (Boolean)
     */
    public void setBackOrderOption(boolean backOrderOption) {
        this.backOrderOption = backOrderOption;
    }

    /**
     * Sets the status of the Order as "Cancelled".
     */
    public void cancel() {
        setStatus("Cancelled");
        for (OrderLine orderLine : orderLineList) {
            orderLine.setPendingQuantity(0);
        }
    }
}
