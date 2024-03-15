package com.team12.view.managerMain;

import com.team12.memorydao.ManagerAccountDAOMemory;

/**
 * The Interface of the Manager's Main Page of the app.
 */
public interface ManagerMainView {

    /**
     * When the manager clicks on the "View Order Inbox" button,
     * the OrderInboxActivity starts and they are taken to this page.
     */
    void viewOrderInboxButton(ManagerAccountDAOMemory managerMemory);

    /**
     * When the manager clicks on the "Restock Products" button,
     * the RestockProductsActivity starts and they are taken to this page.
     */
    void restockProductsButton(ManagerAccountDAOMemory managerMemory);

    /**
     * When the manager clicks on the "Recall Medicine" button,
     * the ????? starts and they are taken to this page.
     */
    void recallOrderedMedicineButton(ManagerAccountDAOMemory managerMemory);

    /**
     * When the manager clicks on the "View Statistics" button,
     * the ????? starts and they are taken to this page.
     */
    void viewClientStatisticsButton(ManagerAccountDAOMemory managerMemory);

    void onSuccessfulLogOut();
}
