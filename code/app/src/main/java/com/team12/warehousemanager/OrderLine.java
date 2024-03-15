package com.team12.warehousemanager;

import java.util.ArrayList;

/**
 * The Order line.
 * It represents a set of ordered products and it is used
 * by the Order and Back Order objects.
 * The way it achieves this is by having a Product Type
 * and an ordered quantity as attributes.
 */
public class OrderLine {
    private final ProductType product;
    private int orderedQuantity;
    private int pendingQuantity;

    /**
     * Constructor.
     * Sets up the basic attributes of an Order Line.
     *
     * @param product         The product type
     * @param orderedQuantity The ordered quantity
     */
    public OrderLine(ProductType product, int orderedQuantity) {
        this.product = product;
        this.orderedQuantity = this.pendingQuantity = orderedQuantity;
    }

    /**
     * Executes the order line and tries to order
     * the wanted quantity. Saves any quantity
     * that could not be ordered.
     *
     * @param orderBatchAllocationList The list containing the batch allocations
     */
    public void execute(ArrayList<BatchAllocation> orderBatchAllocationList) {
        pendingQuantity = product.orderProduct(orderedQuantity, orderBatchAllocationList);
    }

    /**
     * Returns the name of the product of the order line.
     *
     * @return The name of the product of the order line
     */
    public String getName() {
        return product.getName();
    }

    /**
     * Returns the ordered quantity of the order line.
     *
     * @return The ordered quantity of the order line
     */
    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    /**
     * Sets the ordered quantity of the order line.
     *
     * @param orderedQuantity The ordered quantity
     */
    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    /**
     * Returns the quantity that has not yet been ordered;
     *
     * @return pendingQuantity The unorderedQuantity
     */
    public int getPendingQuantity() {
        return this.pendingQuantity;
    }

    /**
     * Sets pendingQuantity
     *
     * @param pendingQuantity The new pending quantity
     */
    public void setPendingQuantity(int pendingQuantity) {
        this.pendingQuantity = pendingQuantity;
    }

    /**
     * Returns the EOF of the products of the order line.
     *
     * @return The EOF of the product of the order line
     */
    public String getEOF() {
        return product.getEOF();
    }

    /**
     * Returns the cost of the order line, which is the
     * wanted quantity times the price of the product.
     *
     * @return The cost of the order line
     */
    public Money getSubtotal() {
        return new Money(orderedQuantity * product.getPrice());
    }

    /**
     * Returns the product of the order line.
     *
     * @return The product of the order line
     */
    public ProductType getProduct() {
        return product;
    }
}
