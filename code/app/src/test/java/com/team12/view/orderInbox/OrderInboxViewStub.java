package com.team12.view.orderInbox;

/**
 * This class is used as a view for the needs of the tests.
 */
public class OrderInboxViewStub implements OrderInboxView{
    private int onSuccessfulViewOrderCorrect;
    @Override
    public void onSuccessfulViewOrder(String orderId) {
        onSuccessfulViewOrderCorrect++;
    }

    public int getOnSuccessfulViewOrderCorrect() {
        return onSuccessfulViewOrderCorrect;
    }
}
