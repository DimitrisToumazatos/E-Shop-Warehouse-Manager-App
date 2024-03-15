package com.team12.view.homePage;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the basic functions of the Home Page Presenter.
 */
public class HomePagePresenterTest {

    private HomePagePresenter presenter;
    private HomePageViewStub view;

    /**
     * Sets up the presenter and the view for the tests.
     */
    @Before
    public void setUp() {
        view = new HomePageViewStub();
        presenter = new HomePagePresenter(view);
    }

    /**
     * Counts how many times the Manager Log In
     * button was pressed successfully.
     */
    @Test
    public void testOnLogInAsManager() {
        for (int i = 0; i < 19; i++) {
            presenter.onLogInAsManager();
        }

        assertEquals(19, view.getLogInAsManagerClicks());
    }

    /**
     * Counts how many times the Customer Log In
     * button was pressed successfully.
     */
    @Test
    public void testOnLogInAsCustomer() {
        for (int i = 0; i < 18; i++) {
            presenter.onLogInAsCustomer();
        }

        assertEquals(18, view.getLogInAsCustomerClicks());
    }

    /**
     * Counts how many times the Customer Sign Up
     * button was pressed successfully.
     */
    @Test
    public void testOnCreateAccount() {
        for (int i = 0; i < 13; i++) {
            presenter.onCreateAccount();
        }

        assertEquals(13, view.getCreateAccountClicks());
    }
}
