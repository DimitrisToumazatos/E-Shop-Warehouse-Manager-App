package com.team12.view.logInAsCustomer;

import static junit.framework.TestCase.assertEquals;

import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.UserAccountDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Log In As Customer Presenter
 */
public class LogInAsCustomerPresenterTest {
    private LogInAsCustomerViewStub view;
    private LogInAsCustomerPresenter presenter;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new LogInAsCustomerViewStub();
        presenter = new LogInAsCustomerPresenter(view, new UserAccountDAOMemory());
    }

    /**
     * Tests when the password is correct, but the email is wrong.
     */
    @Test
    public void logInAsCustomerWrongEmailTest() {
        for (int i = 0; i < 6; i++) {
            presenter.onLogInButton("wrong@mail.com", "12", true);
        }
        assertEquals(0, view.getSuccessfullyLoggedInCorrect());
        assertEquals(6, view.getShowErrorNumber());
        assertEquals(0, view.getShowToastNumber());
    }

    /**
     * Tests when the email is correct, but the password is wrong.
     */
    @Test
    public void logInAsCustomerWrongPasswordTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onLogInButton("papadopoulos@mail.com", "wrongPassword", true);
        }
        assertEquals(0, view.getSuccessfullyLoggedInCorrect());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(5, view.getShowErrorNumber());
    }

    /**
     * Tests when both the email and the password are correct.
     */
    @Test
    public void logInAsCustomerCorrectEmailAndPasswordTest() {
        for (int i = 0; i < 5; i++) {
        presenter.onLogInButton("toumazatos@mail.com", "12", true);
        }
        assertEquals(5, view.getSuccessfullyLoggedInCorrect());
        assertEquals(0, view.getShowToastNumber());

    }

    /**
     * Tests when the user has not entered all the necessary log in details.
     */
    @Test
    public void logInAsCustomerLogInButtonDisabledTest() {
        for (int i = 0; i < 6; i++) {
            presenter.onLogInButton("toumazatos@mail.com", "", false);

        }
        assertEquals(0, view.getSuccessfullyLoggedInCorrect());
        assertEquals(6, view.getShowToastNumber());
    }
}
