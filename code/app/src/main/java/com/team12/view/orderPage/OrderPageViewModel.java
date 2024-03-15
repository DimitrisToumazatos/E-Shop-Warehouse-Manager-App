package com.team12.view.orderPage;

import androidx.lifecycle.ViewModel;

import com.team12.memorydao.OrderDAOMemory;
import com.team12.memorydao.ProductTypeDAOMemory;
import com.team12.memorydao.UserAccountDAOMemory;

/**
 * The Order Page View Model,
 */
public class OrderPageViewModel extends ViewModel {
    private final OrderPagePresenter presenter;

    /**
     * Constructor.
     * Sets up the presenter and the Order DAO.
     */
    public OrderPageViewModel() {
        this.presenter = new OrderPagePresenter();
        presenter.setProductTypeDAO(new ProductTypeDAOMemory());
        presenter.setOrderDAO(new OrderDAOMemory());
        presenter.setUserAccountDAO(new UserAccountDAOMemory());
    }

    /**
     * Returns the presenter
     * @return The presenter
     */
    public OrderPagePresenter getPresenter() {
        return this.presenter;
    }

    /**
     * Destructor.
     * This method is called when the Order Inbox View Model
     * is no longer used and will be destroyed.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
