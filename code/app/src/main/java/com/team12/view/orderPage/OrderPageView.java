package com.team12.view.orderPage;

/**
 * This Interface serves the Order Inbox Page.
 * It allows the Customers to Create/Save Arders.
 */
public interface OrderPageView {

    /**
     * This method is called when the Customer
     * adds a product to their cart successfully.
     */
    void onSuccessfulAddProduct();

    /**
     * This method is called when the Customer
     * clicks on the "Remove" button
     * and the operation is successful.
     */
    void onSuccessfulProductRemoval();

    /**
     * This method is called when the Customer
     * clicks on the "Submit Order" button
     * and the operation is successful.
     */
    void onSuccessfulOrderCompletion();

    /**
     * This method is called when the Customer
     * clicks on the "Confirm" button
     * and the operation is unsuccessful
     * (the Customer has given given invalid inputs).
     * then a pop up message appears on their screen.
     */
    void showError(String title, String message);

    /**
     * This when the Customer clicks on the "Confirm" button
     * and the operation is unsuccessful
     * (the customer did not provide all the fields),
     * then a pop up message appears on their screen.
     */
    void showToast(String message);
}
