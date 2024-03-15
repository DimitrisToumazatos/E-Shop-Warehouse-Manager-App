package com.team12.view.customerMain;

/**
 * This class is used as a view for the needs of the tests.
 */
public class CustomerMainViewStub implements CustomerMainView {
    private int successfulNewOrder;
    private int showToastCount;
    private int successfulLogOut;
    private int successfulDraftOrder;

    @Override
    public void onSuccessfulViewMail(String emailBody) {

    }

    @Override
    public void onSuccessfulNewOrder() {
        successfulNewOrder++;
    }

    @Override
    public void onSuccessfulDraftOrder() {
        successfulDraftOrder++;
    }

    @Override
    public void showToast(String message) {
        showToastCount++;
    }

    @Override
    public void onSuccessfulLogOut() {
        successfulLogOut++;
    }

    public int getSuccessfulNewOrder() {
        return successfulNewOrder;
    }

    public int getShowToastCount() {
        return showToastCount;
    }

    public int getSuccessfulLogOut() {
        return successfulLogOut;
    }

    public int getSuccessfulDraftOrder() {
        return successfulDraftOrder;
    }
}
