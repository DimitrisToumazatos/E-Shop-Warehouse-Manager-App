package com.team12.warehousemanager;

import java.time.LocalDate;

/**
 * The Batch.
 * It is a set of specific products.
 * They are identified by a batch id (primary key).
 * They contain the information of when the batch was
 * created, the EOF of their product, the initial
 * quantity and the remaining quantity.
 */
public class Batch {
    private final LocalDate date;
    private final String EOF;
    private final String batchID;
    private final int initialQuantity;
    private int runningQuantity;

    /**
     * Constructor.
     * Sets up the basic attributes of a Batch.
     *
     * @param date     of creation
     * @param EOF      of product
     * @param quantity initial quantity
     * @param batchID  id of the batch
     */
    public Batch(LocalDate date, String EOF, int quantity, String batchID) {
        this.date = date;
        this.EOF = EOF;
        this.initialQuantity = this.runningQuantity = quantity;
        this.batchID = batchID;
    }

    /**
     * Returns the date the batch was created.
     *
     * @return The date the batch was created
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the EOF of the product of the batch.
     *
     * @return The EOF of the product of the batch
     */
    public String getEOF() {
        return EOF;
    }

    /**
     * Returns the initial quantity of the batch.
     *
     * @return The initial quantity of the batch
     */
    public int getInitialQuantity() {
        return initialQuantity;
    }

    /**
     * Returns the running quantity of the batch.
     *
     * @return the running quantity of the batch
     */
    public int getRunningQuantity() {
        return runningQuantity;
    }

    /**
     * Returns the id of the batch.
     *
     * @return The id of the batch
     */
    public String getBatchID() {
        return batchID;
    }

    /**
     * Increases the running quantity of the batch
     * by the given amount.
     *
     * @param amount Amount
     */
    public void increaseQuantity(int amount) {
        this.runningQuantity += Math.max(amount, 0);
    }

    /**
     * Reduces the running quantity of the batch
     * by the given amount.
     *
     * @param amount Amount
     */
    public void reduceQuantity(int amount) {
        this.runningQuantity -= Math.max(amount, 0);
    }

    /**
     * Returns if the batch has enough quantity
     * to satisfy the given quantity.
     *
     * @param quantity Quantity
     * @return True/False depending if the batch has enough quantity
     */
    public boolean hasQuantity(int quantity) {
        // TODO: Replace with error catch when input non-positive
        return this.runningQuantity >= quantity && quantity > 0;
    }
}
