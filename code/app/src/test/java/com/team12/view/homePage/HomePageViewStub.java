package com.team12.view.homePage;

/**
 * This class is used as a view for the needs of the tests.
 */
public class HomePageViewStub implements HomePageView {

    private int logInAsManagerClicks;
    private int logInAsCustomerClicks;
    private int createAccountClicks;

    public HomePageViewStub()
    {

    }

    public void logInAsManager() {
        logInAsManagerClicks++;
    }

    public void logInAsCustomer() {
        logInAsCustomerClicks++;
    }

    public void createAccount() {
        createAccountClicks++;
    }

    public int getLogInAsManagerClicks() {
        return logInAsManagerClicks;
    }

    public int getLogInAsCustomerClicks() {
        return logInAsCustomerClicks;
    }

    public int getCreateAccountClicks() {
        return createAccountClicks;
    }
}
