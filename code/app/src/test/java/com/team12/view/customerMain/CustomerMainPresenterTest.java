package com.team12.view.customerMain;

import static junit.framework.TestCase.assertEquals;

import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.UserAccountDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Customer Main Presenter
 */
public class CustomerMainPresenterTest {
    private CustomerMainViewStub view;
    private CustomerMainPresenter presenter;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new CustomerMainViewStub();
        presenter = new CustomerMainPresenter(view, new UserAccountDAOMemory());
    }

    /**
     * Tests how many times the onNewOrder method was called successfully.
     */
    @Test
    public void onNewOrderTest() {
        for (int i = 0; i < 6; i++) {
            presenter.onNewOrder();
        }
        assertEquals(6, view.getSuccessfulNewOrder());
        assertEquals(0, view.getShowToastCount());
    }

    /**
     * Tests the getUserFirstName method.
     */
    @Test
    public void getUserFirstNameTest() {
        presenter.setUser("toumazatos@mail.com");
        assertEquals("Dimitris", presenter.getUserFirstName());

        presenter.setUser("papadopoulos@mail.com");
        assertEquals("Alexios", presenter.getUserFirstName());

        presenter.setUser("fotogiannopoulos@mail.com");
        assertEquals("Dimitris", presenter.getUserFirstName());
    }

    /**
     * Test how many "Draft" Orders each user has.
     */
    @Test
    public void getDraftOrderResultsTest() {
        presenter.setUser("toumazatos@mail.com");
        assertEquals(0, presenter.getDraftOrderResults().size());

        presenter.setUser("papadopoulos@mail.com");
        assertEquals(1, presenter.getDraftOrderResults().size());

        presenter.setUser("fotogiannopoulos@mail.com");
        assertEquals(2, presenter.getDraftOrderResults().size());
    }

    /**
     * Tests how many times the ShowToast method was called successfully.
     */
    @Test
    public void onDraftOrderTest() {
        presenter.setUser("toumazatos@mail.com");
        for (int i = 0; i < 6; i++) {
            presenter.onDraftOrder(true);
        }
        assertEquals(0, view.getShowToastCount());
        assertEquals(6, view.getSuccessfulDraftOrder());

        presenter.setUser("fotogiannopoulos@mail.com");
        for (int i = 0; i < 4; i++) {
            presenter.onDraftOrder(false);
        }
        assertEquals(4, view.getShowToastCount());
    }

    /**
     * Tests how many times the onLogOutT method was called successfully.
     */
    @Test
    public void onLogOutTest() {
        for (int i = 0; i < 6; i++) {
            presenter.setUser("toumazatos@mail.com");
            presenter.onLogOut();
        }
        assertEquals(0, view.getShowToastCount());
        assertEquals(0, view.getSuccessfulNewOrder());
        assertEquals(6, view.getSuccessfulLogOut());
    }
}
