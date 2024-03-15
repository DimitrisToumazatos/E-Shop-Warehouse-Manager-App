package com.team12.view.logInAsManager;

import com.team12.warehousemanager.ManagerAccount;

/**
 * The Interface of the Log In As Manager Page of the app.
 */
public interface LogInAsManagerView {

    /**
     * This when the Manager clicks on the "Log In" button
     * and the operation is successful, the ManagerMainActivity
     * starts and they are taken to this page.
     */
    void onSuccessfulLogIn(ManagerAccount manager);

    /**
     * This when the Manager clicks on the "Log In" button
     * and the operation is unsuccessful (wrong email or password),
     * then a pop up message appears on their screen.
     */
    void showError(String title, String message);

    /**
     * This when the Manager clicks on the "Log In" button
     * and the operation is unsuccessful
     * (the customer did not provide an email or a password),
     * then a pop up message appears on their screen.
     */
    void showToast(String message);
}
