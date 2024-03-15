package com.team12.view.restockProducts;

import com.team12.dao.OrderDAO;
import com.team12.dao.ProductTypeDAO;
import com.team12.memorydao.ProductTypeDAOMemory;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.Batch;
import com.team12.warehousemanager.ProductType;
import com.team12.warehousemanager.UserAccount;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * This is the Restock Products page Presenter.
 */
public class RestockProductsPresenter {

    RestockProductsView view;
    ProductTypeDAO products;
    OrderDAO orders;

    /**
     * Constructor.
     * Sets up the presenter.
     *
     * @param view The Instance of the view
     */
    public RestockProductsPresenter(RestockProductsView view, ProductTypeDAOMemory products, OrderDAO orders) {
        this.view = view;
        this.products = products;
        this.orders = orders;
    }

    /**
     * When the customer clicks on the "restock" button,
     * the sign up operation starts.
     * If successful, a new Batch is created
     * and gets added to the stock of the warehouse,
     * else the system will provide an appropriate message.
     */
    public void onRestockButton(String EOF, String batchID, String quantity, boolean restockButtonEnabled) {
        String ERROR_TITLE = "Unsuccessful Restocking";
        String ERROR_EOF_MSG = "The EOF number provided is invalid.";
        String ERROR_BATCH_MSG = "There is already a batch with that ID for this product.";
        String ERROR_QUANTITY_ZERO_MSG = "Quantity can not be zero.";

        String TOAST_BUTTON_MSG = "Please fill all required fields.";
        if (!restockButtonEnabled) {
            view.showToast(TOAST_BUTTON_MSG);
            return;
        }

        ProductType product = products.findByEOF(EOF);

        // Check if EOF number is invalid
        if (product == null) {
            view.showError(ERROR_TITLE, ERROR_EOF_MSG);
            return;
        }

        // Check if batchID is duplicate (same product only)
        for (Batch batch : product.getBatchList()) {
            if (batch.getBatchID().equals(batchID)) {
                view.showError(ERROR_TITLE, ERROR_BATCH_MSG);
                return;
            }
        }

        int parsedQuantity = Integer.parseInt(quantity);
        if (parsedQuantity == 0) {
            view.showError(ERROR_TITLE, ERROR_QUANTITY_ZERO_MSG);
            return;
        }

        // Create new batch and restock product
        Batch newBatch = new Batch(LocalDate.now(), EOF, parsedQuantity, batchID);
        product.addBatch(newBatch);

        // Try to execute any backorders
        String BACKORDER_MSG = "Your backorder with id %s for %d units of EOF# %s has been executed.";
        List<AbstractOrder> backOrders = orders.getOrdersByStatus("BackOrdered");
        for (AbstractOrder backOrder : backOrders) {
            UserAccount backOrderUser = backOrder.getUserAccount();
            ProductType backOrderProduct = backOrder.getOrderLineList().get(0).getProduct();

            String backOrderId = backOrder.getOrderID();
            String backOrderEOF = backOrderProduct.getEOF();
            int backOrderQuantity = backOrder.getOrderLineList().get(0).getPendingQuantity();

            if (backOrderProduct.hasQuantity(backOrderQuantity)) {
                backOrder.execute();
                backOrderUser.addMessageToInbox(String.format(
                        Locale.ENGLISH,
                        BACKORDER_MSG,
                        backOrderId, backOrderQuantity, backOrderEOF
                ));
            }
        }

        view.onSuccessfulRestock();
    }
}
