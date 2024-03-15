package com.team12.view.homePage;

/**
 * This class serves as the Presenter of the Home Page of the app.
 */
public class HomePagePresenter {
    private final HomePageView view;

    /**
     * Constructor.
     * Sets up the presenter.
     * @param view The Instance of the view
     */
    public HomePagePresenter(HomePageView view) {
        this.view = view;
    }

    /**
     * When the user clicks on the "Manager Log In" button,
     * the LogInAsManagerActivity starts and they are taken to this page.
     */
    void onLogInAsManager() {
        view.logInAsManager();
    }

    /**
     * When the user clicks on the "Customer Log In" button,
     * the LogInAsCustomerActivity starts and they are taken to this page.
     */
    void onLogInAsCustomer() {
        view.logInAsCustomer();
    }

    /**
     * When the user clicks on Customer Sign Up, the LogInAsManagerActivity
     * starts and they are taken to this page.
     */
    void onCreateAccount() {
        view.createAccount();
    }
}
