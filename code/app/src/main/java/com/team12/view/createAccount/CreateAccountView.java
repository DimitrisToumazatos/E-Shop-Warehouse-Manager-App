package com.team12.view.createAccount;

/**
 * The Interface of the Sign Up Page of the app.
 */
public interface CreateAccountView {

    /**
     * This when the Customer clicks on the "Sign Up" button
     * and the operation is successful, the NewOrDraftActivity
     * starts and they are taken to this page.
     */
    void onSuccessfulSignUp(String newUserEmail);

    /**
     * This when the Customer clicks on the "Sign Up" button
     * and the operation is unsuccessful (some field is wrong),
     * then a pop up message appears on their screen.
     */
    void showError(String title, String message);

    /**
     * This when the Customer clicks on the "Sign Up" button
     * and the operation is unsuccessful
     * (the customer did not provide all of the fields),
     * then a pop up message appears on their screen.
     */
    void showToast(String message);
}
