package com.team12.view.logInAsManager;

import static junit.framework.TestCase.assertEquals;

import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Log In As Manager Presenter
 */
public class LogInAsManagerPresenterTest {
    private LogInAsManagerViewStub view;
    private LogInAsManagerPresenter presenter;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new LogInAsManagerViewStub();
        presenter = new LogInAsManagerPresenter(view, new ManagerAccountDAOMemory());
    }

    /**
     * Tests when the password is correct, but the email is wrong.
     */
    @Test
    public void logInAsManagerWrongEmailTest() {
        for (int i = 0; i < 6; i++) {
            presenter.onLogInButton("wrong@mail.com", "admin", true);
        }
        assertEquals(0, view.getSuccessfullyLoggedInCorrect());
        assertEquals(6, view.getShowErrorNumber());
        assertEquals(0, view.getShowToastNumber());
    }

    /**
     * Tests when the email is correct, but the password is wrong.
     */
    @Test
    public void logInAsManagerWrongPasswordTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onLogInButton("admin@mail.com", "wrongPassword", true);
        }
        assertEquals(0, view.getSuccessfullyLoggedInCorrect());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(5, view.getShowErrorNumber());
    }

    /**
     * Tests when both the email and the password are correct.
     */
    @Test
    public void logInAsManagerCorrectEmailAndPasswordTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onLogInButton("admin@mail.com", "admin", true);
        }
        assertEquals(5, view.getSuccessfullyLoggedInCorrect());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());
    }

    /**
     * Tests when the user has not entered all the necessary log in details.
     */
    @Test
    public void logInAsManagerLogInButtonDisabledTest() {
        for (int i = 0; i < 6; i++) {
            presenter.onLogInButton("admin@mail.com", "", false);
        }
        assertEquals(0, view.getSuccessfullyLoggedInCorrect());
        assertEquals(6, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());
    }
}
