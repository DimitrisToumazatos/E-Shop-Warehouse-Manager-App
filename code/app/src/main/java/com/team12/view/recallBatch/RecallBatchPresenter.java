package com.team12.view.recallBatch;

import com.team12.dao.OrderDAO;
import com.team12.dao.ProductTypeDAO;
import com.team12.dao.UserAccountDAO;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.Batch;
import com.team12.warehousemanager.BatchAllocation;
import com.team12.warehousemanager.Email;
import com.team12.warehousemanager.ProductType;

import java.util.ArrayList;
import java.util.List;

public class RecallBatchPresenter {

    RecallBatchView view;
    OrderDAO orders;
    ProductTypeDAO products;
    UserAccountDAO users;

    /**
     * Constructor.
     * Sets up the presenter.
     *
     * @param view   The Instance of the view
     * @param orders The memory of the app customers accounts'
     */
    public RecallBatchPresenter(RecallBatchView view, OrderDAO orders, ProductTypeDAO products, UserAccountDAO users) {
        this.view = view;
        this.orders = orders;
        this.products = products;
        this.users = users;
    }

    public void onRecallButton(String EOF, String batchID, boolean recallButtonEnabled) {
        String ERROR_TITLE = "Unsuccessful Recall";
        String ERROR_EOF_MSG = "The EOF number provided is invalid.";
        String ERROR_BATCH_MSG = "There is not a batch with that ID for this product.";

        String TOAST_BUTTON_MSG = "Please fill all required fields.";
        if (!recallButtonEnabled) {
            view.showToast(TOAST_BUTTON_MSG);
            return;
        }

        ProductType product = products.findByEOF(EOF);

        // Check if EOF number is valid
        if (product == null) {
            view.showError(ERROR_TITLE, ERROR_EOF_MSG);
            return;
        }

        // Check if batchID exists
        boolean batchExists = false;
        for (Batch batch : product.getBatchList()) {
            if (batch.getBatchID().equals(batchID)) {
                batchExists = true;
                break;
            }
        }

        if (!batchExists) {
            view.showError(ERROR_TITLE, ERROR_BATCH_MSG);
            return;
        }

        // Recall Batch
        List<Email> emailsToAlert = new ArrayList<>();
        for (AbstractOrder order : orders.getOrdersByStatus("Executed")) {
            Email userEmail = order.getUserAccount().getEmail();

            for (BatchAllocation batchAllocation : order.getBatchAllocationList()) {
                String orderBatchId = batchAllocation.getBatch().getBatchID();

                if (!emailsToAlert.contains(userEmail) && orderBatchId.equals(batchID)) {
                    emailsToAlert.add(order.getUserAccount().getEmail());
                }
            }
        }


        String EMAIL_MSG = String.format("EOF has ordered a recall on medicine you purchased.\nEOF Code: %s\nBatch ID: %s", EOF, batchID);
        for (Email email : emailsToAlert) {
            users.findUserByEmail(email.toString()).addMessageToInbox(EMAIL_MSG);
        }

        view.onSuccessfulRecall();
    }
}
