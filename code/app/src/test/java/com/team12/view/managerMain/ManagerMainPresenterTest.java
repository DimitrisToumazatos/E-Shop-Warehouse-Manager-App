package com.team12.view.managerMain;

import static org.junit.Assert.assertEquals;

import com.team12.dao.Initializer;
import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.memorydao.MemoryInitializer;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the basic functions of the Manager Main Page Presenter.
 */
public class ManagerMainPresenterTest {
    private ManagerMainPresenter presenter;
    private ManagerMainViewStub view;

    /**
     * Sets up the Manager account database, the
     * presenter and the view for the tests.
     */
    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ManagerMainViewStub();
        presenter = new ManagerMainPresenter(view, new ManagerAccountDAOMemory());
    }

    /**
     * Counts how many times the View Order Inbox
     * button was pressed successfully.
     */
    @Test
    public void testOnViewOrderInboxButton() {
        for (int i = 0; i < 10; i++) {
            presenter.onViewOrderInboxButton();
        }

        assertEquals(10, view.getViewOrderInboxButtonClicks());
    }

    /**
     * Counts how many times the Restock Products
     * button was pressed successfully.
     */
    @Test
    public void testOnRestockProductsButton() {
        for (int i = 0; i < 13; i++) {
            presenter.onRestockProductsButton();
        }

        assertEquals(13, view.getRestockProductsButton());
    }

    /**
     * Counts how many times the Recall Medicine
     * button was pressed successfully.
     */
    @Test
    public void testOnRecallOrderedMedicineButton() {
        for (int i = 0; i < 12; i++) {
            presenter.onRecallOrderedMedicineButton();
        }

        assertEquals(12, view.getRecallOrderedMedicineButton());
    }

    /**
     * Counts how many times the View Statistics
     * button was pressed successfully.
     */
    @Test
    public void testOnViewClientStatisticsButton() {
        for (int i = 0; i < 8; i++) {
            presenter.onViewClientStatisticsButton();
        }

        assertEquals(8, view.getViewClientStatisticsButtonClicks());
    }


    /**
     * Counts how many times the onLogOut
     * was called successfully.
     */
    @Test
    public void getSuccessfulLogOutTest() {
        for (int i = 0; i < 8; i++) {
            presenter.onLogOut();
        }
        assertEquals(8, view.getSuccessfulLogOut());
    }

    /**
     * Counts how many times the getManagerName
     * method was called successfully.
     */
    @Test
    public void testGetManagerName() {
        assertEquals("admin", presenter.getManagerName());
    }
}
