package com.team12.view.draftOrders;

import androidx.lifecycle.ViewModel;

import com.team12.memorydao.UserAccountDAOMemory;

public class DraftOrdersViewModel extends ViewModel {
    private final DraftOrdersPresenter presenter;

    public DraftOrdersViewModel() {
        this.presenter = new DraftOrdersPresenter();
        presenter.setUserAccountDAO(new UserAccountDAOMemory());
    }

    public DraftOrdersPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        // Release all associated resources
        super.onCleared();
    }
}
