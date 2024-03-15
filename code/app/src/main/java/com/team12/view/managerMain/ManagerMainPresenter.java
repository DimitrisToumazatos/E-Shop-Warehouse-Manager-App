package com.team12.view.managerMain;

import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.warehousemanager.ManagerAccount;

/**
 * This class serves as the Presenter of the Manager's Main Page of the app.
 */
public class ManagerMainPresenter {
    ManagerMainView view;
    ManagerAccountDAOMemory managerMemory;
    ManagerAccount manager;

    /**
     * Constructor.
     * Sets up the presenter.
     *
     * @param view The Instance of the view
     */
    public ManagerMainPresenter(ManagerMainView view, ManagerAccountDAOMemory managerMemory) {
        this.view = view;
        this.managerMemory = managerMemory;
        this.manager = managerMemory.findAll().get(0);
    }


    /**
     * Returns the name of the Manager.
     *
     * @return The name of the Manager
     */
    String getManagerName() {
        return manager.getName();
    }

    /**
     * When the manager clicks on the "View Order Inbox" button,
     * the OrderInboxActivity starts and they are taken to this page.
     */
    void onViewOrderInboxButton() {
        view.viewOrderInboxButton(this.managerMemory);
    }

    /**
     * When the manager clicks on the "Restock Products" button,
     * the RestockProductsActivity starts and they are taken to this page.
     */
    void onRestockProductsButton() {
        view.restockProductsButton(this.managerMemory);
    }

    /**
     * When the manager clicks on the "Recall Medicine" button,
     * the ????? starts and they are taken to this page.
     */
    void onRecallOrderedMedicineButton() {
        view.recallOrderedMedicineButton(this.managerMemory);
    }

    /**
     * When the manager clicks on the "View Statistics" button,
     * the ????? starts and they are taken to this page.
     */
    void onViewClientStatisticsButton() {
        view.viewClientStatisticsButton(this.managerMemory);
    }

    void onLogOut() {
        view.onSuccessfulLogOut();
    }
}
