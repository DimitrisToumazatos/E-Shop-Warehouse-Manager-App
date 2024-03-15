package com.team12.view.orderPage;

/**
 * This class is used as a view for the needs of the tests.
 */
public class OrderPageViewStub implements OrderPageView{
    private int SuccessfulAddProduct;
    private int SuccessfulProductRemoval;
    private int SuccessfulOrderCompletion;
    private int showErrorCount;
    private int showToastCount;
    @Override
    public void onSuccessfulAddProduct() {
        SuccessfulAddProduct++;
    }

    @Override
    public void onSuccessfulProductRemoval() {
        SuccessfulProductRemoval++;
    }

    @Override
    public void onSuccessfulOrderCompletion() {
        SuccessfulOrderCompletion++;
    }

    @Override
    public void showError(String title, String message) {
        showErrorCount++;
    }

    @Override
    public void showToast(String message) {
        showToastCount++;
    }

    public int getSuccessfulAddProduct() {
        return SuccessfulAddProduct;
    }

    public int getSuccessfulProductRemoval() {
        return SuccessfulProductRemoval;
    }

    public int getSuccessfulOrderCompletion() {
        return SuccessfulOrderCompletion;
    }

    public int getShowErrorCount() {
        return showErrorCount;
    }

    public int getShowToastCount() {
        return showToastCount;
    }
}
