package com.team12.view.orderInbox;

/**
 * This Interface serves the View Order Inbox Page.
 * Only the manager has access to this page.
 * It allows them to execute an Arder.
 */
public interface OrderInboxView {

    /**
     * When the manager clicks on the "View Order" button
     * and the operation is successful, the ExecuteOrderActivity
     * starts and they are taken to this page.
     */
    void onSuccessfulViewOrder(String orderId);
}
