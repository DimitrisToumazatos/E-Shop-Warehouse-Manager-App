package com.team12.dao;

import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.UserAccount;

import java.util.List;

/**
 * This Interface is used by the app as the database for the Orders.
 */
public interface OrderDAO {
    void delete(AbstractOrder order);

    List<AbstractOrder> getAllOrders();

    void save(AbstractOrder order);

    AbstractOrder findByOrderId(String orderId);

    List<AbstractOrder> getOrdersByStatus(String status);

    List<AbstractOrder> getOrdersInProgress();

    AbstractOrder getOrderInProgressByUser(UserAccount user);

    void saveOrderInProgress(AbstractOrder order);

    void deleteOrderInProgress(AbstractOrder order);

    void clearOrdersInProgress(String userEmail);

    int nextId();
}
