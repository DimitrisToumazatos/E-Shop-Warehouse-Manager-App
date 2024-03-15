package com.team12.view.orderPage;

import com.team12.dao.OrderDAO;
import com.team12.dao.ProductTypeDAO;
import com.team12.dao.UserAccountDAO;
import com.team12.warehousemanager.Order;
import com.team12.warehousemanager.OrderLine;
import com.team12.warehousemanager.ProductType;
import com.team12.warehousemanager.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Order Page Presenter
 */
public class OrderPagePresenter {
    private final List<OrderLine> orderLineResults = new ArrayList<>();
    private OrderPageView view;
    private ProductTypeDAO productTypeDAO;
    private OrderDAO orderDAO;
    private UserAccountDAO userAccountDAO;
    private Order order;
    private UserAccount user;

    /**
     * This method is called when an the Customer
     * clicks on the "Submit Order" button.
     */
    public void onOrderSubmission() {
        orderDAO.deleteOrderInProgress(order);

        if (!order.getStatus().equals("Draft")) {
            orderDAO.save(order);
            user.addToOrderList(order);
        }
        order.setStatus("New");
        view.onSuccessfulOrderCompletion();
    }

    /**
     * This method is called when an the Customer
     * clicks on the "Save As Draft" button.
     */
    public void onOrderSave() {
        orderDAO.deleteOrderInProgress(order);

        if (!user.getOrderList().contains(order)) {
            orderDAO.save(order);
            user.addToOrderList(order);
        }

        order.setStatus("Draft");
        view.onSuccessfulOrderCompletion();
    }

    /**
     * This method is called when an the Customer
     * clicks on the "Confirm" button.
     */
    public void onConfirm(String EOF, String quantity, boolean confirmButtonEnabled) {
        String ERROR_INVALID_EOF_TITLE = "Invalid EOF Number";
        String ERROR_INVALID_EOF_MSG = "Please provide a valid EOF number.";
        String ERROR_ZERO_QUANTITY_TITLE = "Invalid Quantity";
        String ERROR_ZERO_QUANTITY_MSG = "Quantity can not be zero.";
        String ERROR_ALREADY_ORDERED_TITLE = "Duplicate Product";
        String ERROR_ALREADY_ORDERED_MSG = "You have already ordered %s. Please remove it and add a new one to change the quantity";

        String TOAST_BUTTON_MSG = "Please fill all required fields";
        if (!confirmButtonEnabled) {
            view.showToast(TOAST_BUTTON_MSG);
            return;
        }

        ProductType product = productTypeDAO.findByEOF(EOF);

        // Check that the EOF number is valid
        if (product == null) {
            view.showError(ERROR_INVALID_EOF_TITLE, ERROR_INVALID_EOF_MSG);
            return;
        }

        // Check that this medicine has not been ordered already
        for (OrderLine orderLine : this.order.getOrderLineList()) {
            if (orderLine.getProduct().getEOF().equals(EOF)) {
                view.showError(
                        ERROR_ALREADY_ORDERED_TITLE,
                        String.format(ERROR_ALREADY_ORDERED_MSG,
                                orderLine.getProduct().getName()
                        )
                );
                return;
            }
        }

        // Check that quantity is valid
        int parsedQuantity = Integer.parseInt(quantity);
        if (parsedQuantity == 0) {
            view.showError(ERROR_ZERO_QUANTITY_TITLE, ERROR_ZERO_QUANTITY_MSG);
            return;
        }

        // Create New OrderLine
        this.order.addOrderLine(product, parsedQuantity);

        view.onSuccessfulAddProduct();
    }

    /**
     * Removes the given Order Line.
     * @param orderLine The Order Line
     */
    public void onRemoveOrderLine(OrderLine orderLine) {
        order.getOrderLineList().remove(orderLine);
        view.onSuccessfulProductRemoval();
    }

    /**
     * Returns the Order Line Results List.
     * @return The Order Line Results List
     */
    public List<OrderLine> getOrderLineResults() {
        return orderLineResults;
    }

    /**
     * Sets the User.
     * @param userEmail The Email of the user
     */
    void setUser(String userEmail) {
        this.user = userAccountDAO.findUserByEmail(userEmail);
    }

    /**
     * Sets the Order Line Results list.
     */
    void loadOrderInProgress() {
        this.order = (Order) orderDAO.getOrderInProgressByUser(this.user);
        this.findAll();
    }

    /**
     * Sets the Order.
     */
    void setOrder() {
        this.order = new Order(String.valueOf(orderDAO.nextId()), user);
        orderDAO.clearOrdersInProgress(user.getEmail().toString());
        orderDAO.saveOrderInProgress(this.order);
        this.findAll();
    }

    /**
     * Finds the Order with the given ID
     * and sets it as the Order of the presenter.
     * @param orderId The Order ID
     */
    void setOrder(String orderId) {
        this.order = (Order) orderDAO.findByOrderId(orderId);
        orderDAO.clearOrdersInProgress(user.getEmail().toString());
        orderDAO.saveOrderInProgress(this.order);
        this.findAll();
    }

    /**
     * Makes the Order Line Line Results list.
     */
    void findAll() {
        this.orderLineResults.clear();
        this.orderLineResults.addAll(order.getOrderLineList());
    }

    /**
     * Sets the Product Type
     * @param productTypeDAO The Product Type
     */
    void setProductTypeDAO(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    /**
     * Sets the view of the presenter.
     * @param view The Instance of the view
     */
    void setView(OrderPageView view) {
        this.view = view;
    }

    /**
     * Sets the Order's DAO of the presenter.
     * @param orderDAO The Order's database
     */
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * Sets the UserAccountDAO of the presenter.
     * @param userAccountDAO The User Accounts' database
     */
    public void setUserAccountDAO(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }
}
