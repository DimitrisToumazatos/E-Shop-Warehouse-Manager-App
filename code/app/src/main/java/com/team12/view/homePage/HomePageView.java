package com.team12.view.homePage;

/**
 * The Interface of the Home Page of the app.
 */
public interface HomePageView {

    /**
     * When the user clicks on the "Manager Log In" button,
     * the LogInAsManagerActivity starts and they are taken to this page.
     */
    void logInAsManager();

    /**
     * When the user clicks on the "Customer Log In" button,
     * the LogInAsCustomerActivity starts and they are taken to this page.
     */
    void logInAsCustomer();

    /**
     * When the user clicks on the "Customer Sign Up" button,
     * the LogInAsManagerActivity starts and they are taken to this page.
     */
    void createAccount();
}
