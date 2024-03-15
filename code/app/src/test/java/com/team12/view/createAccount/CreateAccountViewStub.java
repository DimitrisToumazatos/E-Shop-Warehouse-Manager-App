package com.team12.view.createAccount;

/**
 * This class is used as a view for the needs of the tests.
 */
public class CreateAccountViewStub implements  CreateAccountView{
    private int successfullySignedUp;
    private int showToastNumber;
    private int showErrorNumber;

    @Override
    public void onSuccessfulSignUp(String newUserEmail) {
        successfullySignedUp++;
    }
    @Override
    public void showError(String title, String message) {
        showErrorNumber++;
    }

    @Override
    public void showToast(String message) {
        showToastNumber++;
    }

    public int getSuccessfullySignedUp() {
        return successfullySignedUp;
    }

    public int getShowToastNumber() {
        return showToastNumber;
    }

    public int getShowErrorNumber() {
        return showErrorNumber;
    }
}
