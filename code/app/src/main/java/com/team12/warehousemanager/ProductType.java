package com.team12.warehousemanager;

import java.util.ArrayList;

/**
 * The Product Type.
 * It contains attributes of a product.
 * ex. Name = "Depon", EOF = "D3214"
 * It also contains a list of batches. This helps
 * keep track of the quantity of the product that is in stock.
 */
public class ProductType {
    private final String name;
    private final String category;
    private final String EOF;
    private final ArrayList<Batch> batchList;
    private final Money price;

    /**
     * Constructor.
     * Sets up the basic attributes of a Product Type.
     *
     * @param name     The name of the product
     * @param category The category of the product
     * @param EOF      The EOF of the product
     * @param price    The price of the product
     */
    public ProductType(String name, String category, String EOF, float price) {
        this.name = name;
        this.category = category;
        this.EOF = EOF;
        this.price = new Money(price);
        this.batchList = new ArrayList<>();
    }

    /**
     * Returns the name of the product.
     *
     * @return The name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category of the product.
     *
     * @return The category of the product
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the EOF of the product.
     *
     * @return The EOF of the product
     */
    public String getEOF() {
        return EOF;
    }

    /**
     * Returns the price of the product.
     *
     * @return The price of the product
     */
    public float getPrice() {
        return price.getAmount();
    }

    /**
     * Sets the price of the product, if the given price is a positive number.
     *
     * @param price The price
     */
    public void setPrice(float price) {
        // TODO: Replace with error catch for negative input
        if (price > 0) {
            this.price.setAmount(price);
        }
    }

    /**
     * Returns the total quantity in stock.
     *
     * @return The total quantity in stock
     */
    public int getTotalQuantity() {
        int total = 0;
        for (Batch batch : batchList) {
            total += batch.getRunningQuantity();
        }
        return total;
    }

    /**
     * Returns the list of batches of the product.
     *
     * @return The list of batches of the product
     */
    public ArrayList<Batch> getBatchList() {
        return batchList;
    }

    /**
     * Returns True if there is enough quantity in
     * stock to cover the wanted, else it returns False.
     *
     * @param quantity The quantity
     * @return True/False depending if there is enough quantity in stock to cover the wanted
     */
    public boolean hasQuantity(int quantity) {
        // TODO: Replace with error catch
        return getTotalQuantity() >= quantity && quantity > 0;
    }

    /**
     * Adds a new batch to the list of Batches.
     *
     * @param b The Batch
     */
    public void addBatch(Batch b) {
        batchList.add(b);
    }

    /**
     * It allocates the order quantity from the stock or if the stock supplies
     * are insufficient then it only allocates the available quantity.
     *
     * @param orderedQuantity          The ordered quantity
     * @param orderBatchAllocationList The list containing the batch allocations
     * @return The product quanity that couldn't be ordered (out-of-stock)
     */
    public int orderProduct(int orderedQuantity, ArrayList<BatchAllocation> orderBatchAllocationList) {
        for (Batch batch : batchList) {
            if (orderedQuantity == 0) {
                break;
            }

            if (batch.getRunningQuantity() != 0) {
                int quantityFromBatch = Math.min(orderedQuantity, batch.getRunningQuantity());

                batch.reduceQuantity(quantityFromBatch);
                orderedQuantity -= quantityFromBatch;

                orderBatchAllocationList.add(new BatchAllocation(batch, quantityFromBatch));
            }
        }
        return orderedQuantity;
    }
}
