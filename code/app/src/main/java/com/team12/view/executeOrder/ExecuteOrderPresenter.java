package com.team12.view.executeOrder;

import com.team12.dao.OrderDAO;
import com.team12.warehousemanager.BackOrder;
import com.team12.warehousemanager.Order;
import com.team12.warehousemanager.OrderLine;
import com.team12.warehousemanager.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This is the Execute Order Page Presenter
 */
public class ExecuteOrderPresenter {
    private final List<OrderLine> orderLineResults = new ArrayList<>();
    private OrderDAO orderDAO;
    private ExecuteOrderView view;
    private Order order;

    /**
     * Sets the orderDao of the ExecuteOrderPresenter to the given.
     * @param orderDAO The Order's database object
     */
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * Finds the Order with the given ID and sets it as the
     * Order of the ExecuteOrderPresenter.
     * After that, this method makes again the Order Line Results list.
     * @param orderId The Order's id
     */
    public void setOrder(String orderId) {
        this.order = (Order) orderDAO.findByOrderId(orderId);
        this.findAll();
    }

    /**
     * Sets the View of the Presenter to the given.
     * @param view The Instance of the current view
     */
    public void setView(ExecuteOrderView view) {
        this.view = view;
    }

    /**
     * Returns the Order Line Results list.
     * @return The Order Line Results list
     */
    public List<OrderLine> getOrderLineResults() {
        return this.orderLineResults;
    }

    /**
     * Makes the Order Line Results list.
     */
    public void findAll() {
        this.orderLineResults.clear();
        this.orderLineResults.addAll(order.getOrderLineList());
    }

    /**
     * This when the manager click on the "Execute Order" button
     * this method is called and executes the Order.
     */
    public void onExecuteOrder() {
        order.execute();

        // Checks if any product was unavailable
        for (OrderLine orderLine : order.getOrderLineList()) {
            int pendingQuantity = orderLine.getPendingQuantity();

            if (pendingQuantity > 0 && order.getBackOrderOption()) {
                // Creates BackOrder for the remaining amount
                BackOrder backOrder = new BackOrder(
                        LocalDate.now(),
                        String.valueOf(orderDAO.nextId()),
                        orderLine.getProduct(),
                        pendingQuantity,
                        order.getUserAccount()
                );
                orderDAO.save(backOrder);

                UserAccount userWithBackOrder = backOrder.getUserAccount();
                String backOrderId = backOrder.getOrderID();
                String backOrderProductEOF = orderLine.getEOF();

                // Informs user
                String BACKORDER_MSG = String.format(
                        Locale.ENGLISH,
                        "There has been a backorder created for your order #%s.\nProduct EOF: %s\nQuantity: %d",
                        backOrderId,
                        backOrderProductEOF,
                        pendingQuantity
                );
                userWithBackOrder.addMessageToInbox(BACKORDER_MSG);
            }
        }

        view.onSuccessfulOrderExecution();
    }

    /**
     * This when the manager click on the "Cancel Order" button
     * this method is called and cancels the Order.
     */
    public void onCancelOrder() {
        order.cancel();
        view.onSuccessfulOrderCancel();
    }
}
