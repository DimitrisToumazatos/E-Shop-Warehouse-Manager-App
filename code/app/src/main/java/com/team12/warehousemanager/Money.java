package com.team12.warehousemanager;

import androidx.annotation.NonNull;

/**
 * The Money object.
 * It is a class that represents money.
 * Every mathematical operation rounds the result to 2 decimal places.
 * Currently, it has as its currency Euros (€).
 */
public class Money {
    private float amount;

    /**
     * Constructor.
     * Sets up the basic attributes of a Money Object.
     *
     * @param amount The amount of money
     */
    public Money(float amount) {
        this.amount = (float) Math.round(amount * 100) / 100;
    }

    /**
     * Does the operation of addition between Money objects.
     *
     * @param m1 Other money object
     */
    public void addition(Money m1) {
        this.amount += m1.amount;
    }

    /**
     * Does the operation of subtraction between Money objects.
     *
     * @param m1 Other money object
     */
    public void subtraction(Money m1) {
        this.amount -= m1.amount;
    }

    /**
     * Does the operation of multiplication between Money objects.
     *
     * @param m1 Other money object
     */
    public void multiplication(Money m1) {
        this.amount = Math.round(this.amount * m1.amount * 100 / 100);
    }

    /**
     * Does the operation of division between Money objects.
     *
     * @param m1 Other money object
     */
    public void division(Money m1) {
        this.amount = (float) Math.round(this.amount / m1.amount * 100) / 100;
    }

    /**
     * Returns the amount of money as a float.
     *
     * @return The amount of money as a float
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money.
     *
     * @param amount The amount of money
     */
    public void setAmount(float amount) {
        this.amount = (float) Math.round(amount * 100) / 100;
    }

    /**
     * Returns the representation of the Money object in a String form
     *
     * @return The representation of the Money object in a String form
     */
    @NonNull
    public String toString() {
        return String.format("\u20ac %.2f", amount);
    }
}
