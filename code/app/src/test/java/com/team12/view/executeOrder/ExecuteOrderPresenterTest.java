package com.team12.view.executeOrder;

import static junit.framework.TestCase.assertEquals;

import com.team12.dao.Initializer;
import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.OrderDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the basic functions of the Execute Order Presenter.
 */
public class ExecuteOrderPresenterTest {
    private ExecuteOrderPresenter presenter;
    private ExecuteOrderViewStub view;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
        presenter = new ExecuteOrderPresenter();
        presenter.setOrderDAO(new OrderDAOMemory());
        view = new ExecuteOrderViewStub();
        presenter.setView(view);
        presenter.setOrder("1");
    }

    /**
     * Tests counts the times orders were
     * executed successfully.
     */
    @Test
    public void onExecuteOrderTest() {
        presenter.onExecuteOrder();
        assertEquals(1, view.getOnSuccessfulOrderExecutionCorrect());

        presenter.setOrder("2");
        presenter.onExecuteOrder();
        assertEquals(2, view.getOnSuccessfulOrderExecutionCorrect());

        presenter.setOrder("3");
        presenter.onExecuteOrder();
        assertEquals(3, view.getOnSuccessfulOrderExecutionCorrect());
    }

    /**
     * Tests counts the times orders were
     * canceled successfully.
     */
    @Test
    public void onCancelOrderTest() {
        presenter.onCancelOrder();
        assertEquals(1, view.getOnSuccessfulOrderCancelCorrect());

        presenter.setOrder("2");
        presenter.onCancelOrder();
        assertEquals(2, view.getOnSuccessfulOrderCancelCorrect());

        presenter.setOrder("3");
        presenter.onCancelOrder();
        assertEquals(3, view.getOnSuccessfulOrderCancelCorrect());
    }

    /**
     * Tests if the Order Line results
     * change depending on which order
     * is currently active.
     */
    @Test
    public void getOrderLineResultsTest() {
        assertEquals(3, presenter.getOrderLineResults().size());
        presenter.setOrder("2");
        assertEquals(1, presenter.getOrderLineResults().size());
    }
}
