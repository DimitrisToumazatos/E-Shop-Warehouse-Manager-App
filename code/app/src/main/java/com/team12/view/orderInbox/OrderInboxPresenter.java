package com.team12.view.orderInbox;

import com.team12.dao.OrderDAO;
import com.team12.warehousemanager.AbstractOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Order Inbox Page Presenter
 */
public class OrderInboxPresenter {

    private final List<AbstractOrder> orderResults = new ArrayList<>();
    private OrderInboxView view;
    private OrderDAO orders;

    /**
     * Returns the Order Results list.
     * @return The Order Results list
     */
    public List<AbstractOrder> getOrderResults() {
        return this.orderResults;
    }

    /**
     * Returns the Order DAO of the presenter.
     * @return The Order DAO
     */
    public OrderDAO getOrderDAO() {
        return this.orders;
    }

    /**
     * Sets the presenters Order DAO to the given.
     * @param orders The Order DAO
     */
    public void setOrderDAO(OrderDAO orders) {
        this.orders = orders;
    }

    /**
     * Sets the view of the presenter to the given.
     * @param view The Instance of the OrderInboxView
     */
    public void setView(OrderInboxView view) {
        this.view = view;
    }

    /**
     * Makes the Order Results List.
     * The mentioned list contains the Orders with
     * given status.
     * @param status The status of the Orders
     */
    public void findAll(String status) {
        this.orderResults.clear();
        orderResults.addAll(this.orders.getOrdersByStatus(status));
    }

    /**
     * When the manager clicks on the "View Order" button
     * this method is called and starts the view order process.
     */
    public void onViewOrder(String orderId) {
        view.onSuccessfulViewOrder(orderId);
    }
}
