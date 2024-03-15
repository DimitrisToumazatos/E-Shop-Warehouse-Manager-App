package com.team12.view.managerMain;

import com.team12.memorydao.ManagerAccountDAOMemory;

/**
 * This class is used as a view for the needs of the tests.
 */
public class ManagerMainViewStub implements ManagerMainView {
    private int viewOrderInboxButtonClicks;
    private int restockProductsButtonClicks;
    private int recallOrderedMedicineButtonClicks;
    private int viewClientStatisticsButtonClicks;
    private int SuccessfulLogOut;

    @Override
    public void viewOrderInboxButton(ManagerAccountDAOMemory managerMemory) {
        viewOrderInboxButtonClicks++;
    }

    @Override
    public void restockProductsButton(ManagerAccountDAOMemory managerMemory) {
        restockProductsButtonClicks++;
    }

    @Override
    public void recallOrderedMedicineButton(ManagerAccountDAOMemory managerMemory) {
        recallOrderedMedicineButtonClicks++;
    }

    @Override
    public void viewClientStatisticsButton(ManagerAccountDAOMemory managerMemory) {
        viewClientStatisticsButtonClicks++;
    }

    @Override
    public void onSuccessfulLogOut() {
        SuccessfulLogOut++;
    }

    public int getViewOrderInboxButtonClicks() {
        return viewOrderInboxButtonClicks;
    }

    public int getRecallOrderedMedicineButton() {
        return recallOrderedMedicineButtonClicks;
    }

    public int getRestockProductsButton() {
        return restockProductsButtonClicks;
    }

    public int getViewClientStatisticsButtonClicks() {
        return viewClientStatisticsButtonClicks;
    }

    public int getSuccessfulLogOut() {
        return SuccessfulLogOut;
    }
}
