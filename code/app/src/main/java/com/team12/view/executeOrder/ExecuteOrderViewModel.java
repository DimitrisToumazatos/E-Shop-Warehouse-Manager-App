package com.team12.view.executeOrder;

import androidx.lifecycle.ViewModel;

import com.team12.dao.OrderDAO;
import com.team12.memorydao.OrderDAOMemory;

/**
 * The Execute Order View Model.
 */
public class ExecuteOrderViewModel extends ViewModel {
    private final ExecuteOrderPresenter presenter;

    /**
     * Constructor.
     * Sets up the presenter and the Order DAO.
     */
    public ExecuteOrderViewModel() {
        this.presenter = new ExecuteOrderPresenter();
        OrderDAO orderDAO = new OrderDAOMemory();
        this.presenter.setOrderDAO(orderDAO);
    }

    /**
     * Returns the presenter.
     * @return The presenter
     */
    public ExecuteOrderPresenter getPresenter() {
        return this.presenter;
    }

    /**
     * Destructor.
     * This method is called when the Execute Order View Model
     * is no longer used and will be destroyed.
     */
    @Override
    public void onCleared() {
        super.onCleared();
    }
}
