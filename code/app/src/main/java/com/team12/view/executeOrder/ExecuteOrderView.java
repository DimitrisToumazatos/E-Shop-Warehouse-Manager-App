package com.team12.view.executeOrder;

/**
 * This Interface serves the Execute Order Page.
 * Only the manager has access to this page.
 * It allows them to execute an order.
 */
public interface ExecuteOrderView {

    /**
     * This when the manager click on the "Execute Order" button
     * and the operation is successful, the OrderInboxActivity
     * starts and they are taken to this page.
     */
    void onSuccessfulOrderExecution();

    /**
     * This when the manager click on the "Cancel Order" button
     * and the operation is successful, the OrderInboxActivity
     * starts and they are taken to this page.
     */
    void onSuccessfulOrderCancel();
}
