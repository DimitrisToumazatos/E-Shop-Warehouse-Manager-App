package com.team12.memorydao;

import com.team12.dao.OrderDAO;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.UserAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used by the app as the database for the Orders.
 * It provides necessary methods that help with the management of the the data.
 */
public class OrderDAOMemory implements OrderDAO {
    protected static ArrayList<AbstractOrder> orders = new ArrayList<>();
    protected static ArrayList<AbstractOrder> ordersInProgress = new ArrayList<>();

    protected static HashMap<String, AbstractOrder> idToOrder = new HashMap<String, AbstractOrder>();

    public int nextId() {
        return orders.size() + 1;
    }

    /**
     * Deletes the given Order from the database.
     *
     * @param order The Order
     */
    public void delete(AbstractOrder order) {
        orders.remove(order);
        idToOrder.remove(order.getOrderID());
    }

    /**
     * Returns a list containing all the Orders in the database.
     *
     * @return A list containing all the Orders in the database.
     */
    public List<AbstractOrder> getAllOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Saves a Order in the database.
     *
     * @param order The Order
     */
    public void save(AbstractOrder order) {
        orders.add(order);
        idToOrder.put(order.getOrderID(), order);
    }

    /**
     * Finds the The Order with the given ID and returns it.
     * Otherwise, if it does not exist in the database, it returns NULL.
     *
     * @param orderId The ID of a Order
     * @return The Order with the given ID or
     * NULL if it does not exist in the database
     */
    public AbstractOrder findByOrderId(String orderId) {
        return idToOrder.getOrDefault(orderId, null);
    }

    /**
     * Returns the next available ID for item to get put in the database.
     *
     * @return The next available ID for item to get put in the database
     */
    public List<AbstractOrder> getOrdersByStatus(String status) {
        ArrayList<AbstractOrder> results = new ArrayList<>();
        for (AbstractOrder order : orders) {
            if (order.getStatus().equals(status)) {
                results.add(order);
            }
        }
        return results;
    }

    /**
     * Returns a list with orders that are still in progress
     * (being created at the same time of this method call).
     * This is different than a draft order.
     *
     * @return A list containing in progress orders.
     */
    public List<AbstractOrder> getOrdersInProgress() {
        return ordersInProgress;
    }

    public AbstractOrder getOrderInProgressByUser(UserAccount user) {
        for (AbstractOrder order : ordersInProgress) {
            if (order.getUserAccount().equals(user)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Saves order that is still in progress
     * (being created at the same time of this method call).
     * This is different than a draft order.
     */
    public void saveOrderInProgress(AbstractOrder order) {
        ordersInProgress.add(order);
    }

    /**
     * Deletes order that is still in progress
     * (being created at the same time of this method call).
     * This is different than a draft order.
     */
    public void deleteOrderInProgress(AbstractOrder order) {
        ordersInProgress.remove(order);
    }

    /**
     * Deletes all order that are still in progress
     * from a specific user.
     */
    public void clearOrdersInProgress(String userEmail) {
        List<AbstractOrder> ordersToRemove = new ArrayList<>();

        for (AbstractOrder order : ordersInProgress) {
            String email = order.getUserAccount().getEmail().toString();
            if (email.equals(userEmail)) {
                ordersToRemove.add(order);
            }
        }

        for (AbstractOrder order : ordersToRemove) {
            deleteOrderInProgress(order);
        }
    }
}
