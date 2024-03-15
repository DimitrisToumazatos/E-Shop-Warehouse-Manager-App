package com.team12.view.orderInbox;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.view.executeOrder.ExecuteOrderActivity;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.R;

import java.util.List;

/**
 * This class serves as the activity of the View Order Inbox Page.
 * Only the manager has access to this page.
 * It allows them to view his pending orders.
 */
public class OrderInboxActivity extends AppCompatActivity implements OrderInboxView, OrderInboxRecyclerViewAdapter.ItemSelectionListener {

    private OrderInboxViewModel viewModel;

    /**
     * Creates the layout and sets ups the activity.
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_inbox);

        String displayedOrderStatus = "New";

        viewModel = new ViewModelProvider(this).get(OrderInboxViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findAll(displayedOrderStatus);
        List<AbstractOrder> orderList = viewModel.getPresenter().getOrderResults();

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.order_inbox_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderInboxRecyclerViewAdapter(orderList, this));
    }

    /**
     * When the manager clicks on the "View Order" button
     * and the operation is successful, the ExecuteOrderActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulViewOrder(String orderId) {
        Intent intent = new Intent(OrderInboxActivity.this, ExecuteOrderActivity.class);
        intent.putExtra("chosenOrderId", orderId);
        startActivity(intent);
    }

    /**
     * When the manager clicks on the "View Order" button
     * this method is called and starts the view order process.
     */
    @Override
    public void viewOrder(String orderId) {
        viewModel.getPresenter().onViewOrder(orderId);
    }
}
