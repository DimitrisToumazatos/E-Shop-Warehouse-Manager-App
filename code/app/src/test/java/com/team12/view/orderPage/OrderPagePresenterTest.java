package com.team12.view.orderPage;

import static junit.framework.TestCase.assertEquals;

import com.team12.dao.Initializer;
import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.OrderDAOMemory;
import com.team12.memorydao.ProductTypeDAOMemory;
import com.team12.memorydao.UserAccountDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the basic functions of the Order Page Presenter.
 */
public class OrderPagePresenterTest {
    private OrderPagePresenter presenter;
    private OrderPageViewStub view;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
        presenter = new OrderPagePresenter();
        presenter.setOrderDAO(new OrderDAOMemory());
        view = new OrderPageViewStub();
        presenter.setView(view);
        presenter.setOrderDAO(new OrderDAOMemory());
        presenter.setUserAccountDAO(new UserAccountDAOMemory());
        presenter.setProductTypeDAO(new ProductTypeDAOMemory());
        presenter.setUser("fotogiannopoulos@mail.com");
        presenter.setOrder("3");
    }

    /**
     * Tests the onConfirm method.
     * Successful operation.
     */
    @Test
    public void onConfirmSuccessfullyTest() {
        presenter.onConfirm("3", "10", true);
        assertEquals(1, view.getSuccessfulAddProduct());
        assertEquals(0, view.getShowToastCount());
        assertEquals(0, view.getShowErrorCount());
    }

    /**
     * Tests the loadOrderInProgress methods.
     * Successful operation.
     */
    @Test
    public void loadOrderInProgressTest() {
        presenter.setOrder("5");
        presenter.loadOrderInProgress();
        assertEquals(1, presenter.getOrderLineResults().size());
    }

    /**
     * Tests the setOrder methods.
     * Successful operation.
     */
    @Test
    public void setOrderTest() {
        presenter.setOrder();
        assertEquals(0, presenter.getOrderLineResults().size());
    }


    /**
     * Tests the onConfirm method.
     * Wrong input.
     */
    @Test
    public void onConfirmWrongInputTest() {
        // Wrong quantity
        presenter.onConfirm("3", "0", true);
        assertEquals(0, view.getSuccessfulAddProduct());
        assertEquals(0, view.getShowToastCount());
        assertEquals(1, view.getShowErrorCount());

        // Wrong EOF
        presenter.onConfirm("31", "0", true);
        assertEquals(0, view.getSuccessfulAddProduct());
        assertEquals(0, view.getShowToastCount());
        assertEquals(2, view.getShowErrorCount());

        // Same EOF as existing item
        presenter.onConfirm("5", "0", true);
        assertEquals(0, view.getSuccessfulAddProduct());
        assertEquals(0, view.getShowToastCount());
        assertEquals(3, view.getShowErrorCount());
    }

    /**
     * Tests the onConfirm method.
     * Missing input.
     */
    @Test
    public void onConfirmMissingInputTest() {
        presenter.onConfirm("3", "10", false);
        assertEquals(0, view.getSuccessfulAddProduct());
        assertEquals(1, view.getShowToastCount());
        assertEquals(0, view.getShowErrorCount());
    }

    /**
     * Tests counts the times the
     * Order was saved successfully.
     */
    @Test
    public void onOrderSaveTest() {
        presenter.onOrderSave();
        assertEquals(1, view.getSuccessfulOrderCompletion());
    }

    /**
     * Tests counts the times the
     * Order was submitted successfully.
     */
    @Test
    public void onOrderSubmissionTest() {
        presenter.onOrderSubmission();
        assertEquals(1, view.getSuccessfulOrderCompletion());
    }

    /**
     * Tests counts the times an
     * Order Line was removed successfully.
     */
    @Test
    public void onRemoveOrderLineTest() {
        presenter.onRemoveOrderLine(presenter.getOrderLineResults().get(0));
        assertEquals(1, view.getSuccessfulProductRemoval());
    }
}
