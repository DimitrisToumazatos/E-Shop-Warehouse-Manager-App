package com.team12.view.customerMain;

/**
 * The Interface of the Customer's Main Page of the app.
 */
public interface CustomerMainView {

    void onSuccessfulViewMail(String emailBody);

    void onSuccessfulNewOrder();

    void onSuccessfulDraftOrder();

    void onSuccessfulLogOut();

    void showToast(String message);
}
