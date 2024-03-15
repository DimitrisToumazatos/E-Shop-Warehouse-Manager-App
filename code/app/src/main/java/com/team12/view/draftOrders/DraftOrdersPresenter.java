package com.team12.view.draftOrders;

import com.team12.dao.UserAccountDAO;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class DraftOrdersPresenter {
    private final List<AbstractOrder> orderResults = new ArrayList<>();
    private DraftOrdersView view;
    private UserAccountDAO userAccountDAO;
    private UserAccount user;

    public List<AbstractOrder> getOrderResults() {
        return this.orderResults;
    }

    public void setView(DraftOrdersView view) {
        this.view = view;
    }

    public void setUser(String userEmail) {
        this.user = userAccountDAO.findUserByEmail(userEmail);
        this.findAll();
    }

    public void findAll() {
        this.orderResults.clear();
        for (AbstractOrder order : user.getOrderList()) {
            if (order.getStatus().equals("Draft")) {
                this.orderResults.add(order);
            }
        }
    }

    public void onViewOrder(String orderId) {
        view.onSuccessfulViewOrder(orderId);
    }

    public void setUserAccountDAO(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }
}
