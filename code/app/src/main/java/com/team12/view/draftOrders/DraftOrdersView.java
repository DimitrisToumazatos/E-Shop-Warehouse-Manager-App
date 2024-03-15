package com.team12.view.draftOrders;

public interface DraftOrdersView {

    /**
     * When the user clicks on the "View Order" button
     * and the operation is successful, the OrderPageActivity
     * starts and they are taken to the corresponding.
     */
    void onSuccessfulViewOrder(String orderId);
}
