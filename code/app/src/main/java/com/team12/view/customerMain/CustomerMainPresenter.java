package com.team12.view.customerMain;

import com.team12.dao.UserAccountDAO;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Customer's Main page Presenter.
 */
public class CustomerMainPresenter {

    private final CustomerMainView view;
    private final UserAccountDAO users;
    private final List<AbstractOrder> draftOrderResults = new ArrayList<>();
    private UserAccount user;

    /**
     * Constructor.
     * Sets up the presenter.
     * @param view The Instance of the view
     */
    public CustomerMainPresenter(CustomerMainView view, UserAccountDAO users) {
        this.view = view;
        this.users = users;
    }

    /**
     * Returns the list of Draft Orders of the User/Customer.
     * @return The list of Draft Orders of the User/Customer
     */
    public List<AbstractOrder> getDraftOrderResults() {
        return this.draftOrderResults;
    }

    /**
     * Finds the User with the given Email and sets them as the active user.
     * @param userEmail The Email of the user
     */
    public void setUser(String userEmail) {
        this.user = users.findUserByEmail(userEmail);
        this.findAll();
    }

    /**
     * Finds all the Draft Orders of the User.
     */
    private void findAll() {
        this.draftOrderResults.clear();
        for (AbstractOrder order : user.getOrderList()) {
            if (order.getStatus().equals("Draft")) {
                this.draftOrderResults.add(order);
            }
        }
    }

    /**
     * Returns the name of the Customer.
     * @return The name of the Customer
     */
    public String getUserFirstName() {
        return user.getName().split(" ")[0];
    }

    public void onViewMail() {
        String TOAST_MSG = "You don't have any mail.";
        ArrayList<String> userInbox = user.getInbox();

        if (userInbox.isEmpty()) {
            view.showToast(TOAST_MSG);
            return;
        }

        view.onSuccessfulViewMail(userInbox.get(0));
        userInbox.remove(0);
    }

    /**
     * When the customer clicks on the "Create New Order" button,
     * the creation of a New Order begins.
     */
    public void onNewOrder() {
        view.onSuccessfulNewOrder();
    }

    /**
     * When the customer clicks on the "Continue Draft" button,
     * the Draft Order menu open if the Customer has saved Draft Orders,
     * else the system will provide an appropriate message.
     */
    public void onDraftOrder(boolean draftOrderButtonEnabled) {
        String TOAST_BUTTON_MSG = "You don't have any draft orders.";
        if (!draftOrderButtonEnabled) {
            view.showToast(TOAST_BUTTON_MSG);
            return;
        }

        view.onSuccessfulDraftOrder();
    }

    public void onLogOut() {
        view.onSuccessfulLogOut();
    }
}
