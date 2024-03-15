package com.team12.view.restockProducts;

/**
 * This class is used as a view for the needs of the tests.
 */
public class RestockProductsViewStub implements RestockProductsView {
    private int showToastNumber;
    private int showErrorNumber;
    private int successfullyRestocked;

    @Override
    public void onSuccessfulRestock() {
        successfullyRestocked++;
    }

    @Override
    public void showError(String title, String message) {
        showErrorNumber++;
    }

    @Override
    public void showToast(String message) {
        showToastNumber++;
    }

    public int getSuccessfullyRestocked() {
        return successfullyRestocked;
    }

    public int getShowToastNumber() {
        return showToastNumber;
    }

    public int getShowErrorNumber() {
        return showErrorNumber;
    }
}
