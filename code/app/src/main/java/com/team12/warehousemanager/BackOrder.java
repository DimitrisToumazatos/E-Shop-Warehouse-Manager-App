package com.team12.warehousemanager;

import java.time.LocalDate;

/**
 * The Back Order.
 * Its is an Order containing only 1 product at a given amount.
 * The given amount is currently out of stock, so the back order
 * will be executed when new stock arrives in the warehouse.
 */
public class BackOrder extends AbstractOrder {

    /**
     * Constructor.
     * Sets up the basic attributes of a Back Order and creates
     * an Order Line for the back ordered product at the wanted quantity.
     *
     * @param dateOfSubmission The date of submission of the Back Order
     * @param orderID          The ID of the Back Order
     * @param product          The given product
     * @param orderedQuantity  The ordered quantity
     */
    public BackOrder(LocalDate dateOfSubmission, String orderID, ProductType product, int orderedQuantity, UserAccount user) {
        super(dateOfSubmission, orderID, user);
        orderLineList.add(new OrderLine(product, orderedQuantity));
        setStatus("BackOrdered");
    }

    /**
     * Returns the back ordered product.
     *
     * @return The back ordered product
     */
    public ProductType getBackOrderedProduct() {
        return orderLineList.get(0).getProduct();
    }
}
