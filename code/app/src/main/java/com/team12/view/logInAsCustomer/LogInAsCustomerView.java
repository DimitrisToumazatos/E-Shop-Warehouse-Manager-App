package com.team12.view.logInAsCustomer;

import com.team12.warehousemanager.UserAccount;

/**
 * The Interface of the Log In As Customer Page of the app.
 */
public interface LogInAsCustomerView {

    /**
     * This when the Customer clicks on the "Log In" button
     * and the operation is successful, the NewOrDraftActivity
     * starts and they are taken to this page.
     */
    void onSuccessfulLogIn(UserAccount user);


    /**
     * This when the Customer clicks on the "Log In" button
     * and the operation is unsuccessful (wrong email or password),
     * then a pop up message appears on their screen.
     */
    void showError(String title, String message);

    /**
     * This when the Customer clicks on the "Log In" button
     * and the operation is unsuccessful
     * (the customer did not provide an email or a password),
     * then a pop up message appears on their screen.
     */
    void showToast(String message);
}
