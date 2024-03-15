package com.team12.view.createAccount;

import static junit.framework.TestCase.assertEquals;

import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.UserAccountDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Create Account Presenter
 */
public class CreateAccountPresenterTest {
    private CreateAccountViewStub view;
    private CreateAccountPresenter presenter;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new CreateAccountViewStub();
        presenter = new CreateAccountPresenter(view, new UserAccountDAOMemory());
    }

    /**
     * Tests when all the fields are valid.
     */
    @Test
    public void signUpCorrectFieldsTest() {
        presenter.onSignUp("John", "123", "18443", "Greece", "Attica", "Piraeus",
                    "Agiou Spiridonos", "12", "John@mail.com", "534", true);

        assertEquals(1, view.getSuccessfullySignedUp());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());
    }

    /**
     * Tests when some the fields are invalid.
     */
    @Test
    public void signUpIncorrectFieldsTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onSignUp("John", "123", "18443", "Greece", "Attica", "Piraeus",
                    "Agiou Spiridonos", "12", "toumazatos@mail.com", "534", true);
        }
        assertEquals(0, view.getSuccessfullySignedUp());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(5, view.getShowErrorNumber());
    }

    /**
     * Tests when some the fields are missing.
     */
    @Test
    public void signUpMissingFieldsTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onSignUp("", "123", "", "Greece", "Attica", "Piraeus",
                    "Agiou Spiridonos", "12", "toumazatos@mail.com", "534", false);
        }
        assertEquals(0, view.getSuccessfullySignedUp());
        assertEquals(5, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());
    }

    /**
     * Tests when some the fields are invalid.
     */
    @Test
    public void signUpInvalidEmailTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onSignUp("John", "123", "18443", "Greece", "Attica", "Piraeus",
                    "Agiou Spiridonos", "12", "", "534", true);
        }
        assertEquals(0, view.getSuccessfullySignedUp());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(5, view.getShowErrorNumber());
    }
}
