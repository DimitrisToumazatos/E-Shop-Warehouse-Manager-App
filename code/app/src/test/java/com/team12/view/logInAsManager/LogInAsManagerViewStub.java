package com.team12.view.logInAsManager;

import com.team12.warehousemanager.ManagerAccount;

/**
 * This class is used as a view for the needs of the tests.
 */
public class LogInAsManagerViewStub implements LogInAsManagerView {
    private int successfullyLoggedInCorrect;
    private int showToastNumber;
    private int showErrorNumber;

    @Override
    public void onSuccessfulLogIn(ManagerAccount manager) {
        successfullyLoggedInCorrect++;
    }

    @Override
    public void showError(String title, String message) {
        showErrorNumber++;
    }

    @Override
    public void showToast(String message) {
        showToastNumber++;
    }

    public int getSuccessfullyLoggedInCorrect() {
        return successfullyLoggedInCorrect;
    }

    public int getShowToastNumber() {
        return showToastNumber;
    }

    public int getShowErrorNumber() {
        return showErrorNumber;
    }
}
