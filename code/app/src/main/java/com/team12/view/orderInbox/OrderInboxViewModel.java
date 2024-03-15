package com.team12.view.orderInbox;

import androidx.lifecycle.ViewModel;

import com.team12.dao.OrderDAO;
import com.team12.memorydao.OrderDAOMemory;

/**
 * The Order Inbox View Model,
 */
public class OrderInboxViewModel extends ViewModel {
    private final OrderInboxPresenter presenter;

    /**
     * Constructor.
     * Sets up the presenter and the Order DAO.
     */
    public OrderInboxViewModel() {
        this.presenter = new OrderInboxPresenter();
        OrderDAO orderDAO = new OrderDAOMemory();
        presenter.setOrderDAO(orderDAO);
    }

    /**
     * Returns the presenter.
     * @return The presenter
     */
    public OrderInboxPresenter getPresenter() {
        return this.presenter;
    }

    /**
     * Destructor.
     * This method is called when the Order Inbox View Model
     * is no longer used and will be destroyed.
     */
    @Override
    protected void onCleared() {
        // Release all associated resources
        super.onCleared();
    }
}
