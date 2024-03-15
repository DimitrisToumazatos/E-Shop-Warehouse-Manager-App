package com.team12.view.orderInbox;

import static junit.framework.TestCase.assertEquals;

import com.team12.dao.Initializer;
import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.OrderDAOMemory;
import com.team12.warehousemanager.AbstractOrder;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the basic functions of the Order Inbox Page Presenter.
 */
public class OrderInboxPresenterTest {
    private OrderInboxPresenter presenter;
    private OrderInboxViewStub view;


    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
        presenter = new OrderInboxPresenter();
        presenter.setOrderDAO(new OrderDAOMemory());
        view = new OrderInboxViewStub();
        presenter.setView(view);
    }

    /**
     * Counts how many times the onViewOrder
     * mathod was call successfully.
     */
    @Test
    public void onViewOrderTest(){
        presenter.onViewOrder("1");
        assertEquals(1, this.view.getOnSuccessfulViewOrderCorrect());
    }

    /**
     * Checks if the findAll method of the OrderInboxPresenter
     * is returning the appropriate list of orders.
     */
    @Test
    public void findAllTest(){
        presenter.findAll("New");
        assertEquals(3, presenter.getOrderResults().size());
        presenter.findAll("Executed");
        assertEquals(0, presenter.getOrderResults().size());
    }

    /**
     * Checks if the getOrderResults method of the OrderInboxPresenter
     * is returning the appropriate list of orders depending on
     * the search input.
     */
    @Test
    public void getOrderResultsTest(){
        assertEquals(0, presenter.getOrderResults().size());
        presenter.findAll("New");
        assertEquals(3, presenter.getOrderResults().size());
        presenter.findAll("New1");
        assertEquals(0, presenter.getOrderResults().size());
    }

    /**
     * Test for the getOrderDAO method.
     * Checks if the method returns the attribute "orders"
     * (Instance of OrderDAO) correctly and if it
     * is consistent with the changes in the OrderDAO
     */
    @Test
    public void getOrderDAOTest(){
        assertEquals(new OrderDAOMemory().getAllOrders().size(), presenter.getOrderDAO().getAllOrders().size());
        AbstractOrder order = presenter.getOrderDAO().findByOrderId("1");
        presenter.getOrderDAO().delete(order);
        assertEquals(new OrderDAOMemory().getAllOrders().size(), presenter.getOrderDAO().getAllOrders().size());
    }
}
