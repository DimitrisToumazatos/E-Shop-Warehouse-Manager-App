package com.team12.warehousemanager;

/**
 * The Batch Allocation object.
 * It displays how much quantity of a batch has been allocated.
 */
public class BatchAllocation {
    private final Batch batch;
    private final int quantity;

    /**
     * Constructor
     * Sets up the basic attributes of a Batch Allocation object.
     *
     * @param batch    The batch
     * @param quantity The allocated quantity
     */
    public BatchAllocation(Batch batch, int quantity) {
        this.batch = batch;
        this.quantity = quantity;
    }

    /**
     * Returns the batch.
     *
     * @return The batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Returns the allocated quantity.
     *
     * @return The allocated quantity
     */
    public int getQuantity() {
        return quantity;
    }
}
