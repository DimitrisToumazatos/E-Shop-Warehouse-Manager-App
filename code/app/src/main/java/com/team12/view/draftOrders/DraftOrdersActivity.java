package com.team12.view.draftOrders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.view.orderPage.OrderPageActivity;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.R;

import java.util.List;

public class DraftOrdersActivity extends AppCompatActivity implements DraftOrdersView, DraftOrdersRecyclerViewAdapter.ItemSelectionListener {

    private String userEmail = "";
    private DraftOrdersViewModel viewModel;

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_inbox);

        if (savedInstanceState == null) {
            userEmail = getIntent().getStringExtra("userEmail");
        }

        viewModel = new ViewModelProvider(this).get(DraftOrdersViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().setUser(userEmail);
        List<AbstractOrder> orderList = viewModel.getPresenter().getOrderResults();

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.order_inbox_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DraftOrdersRecyclerViewAdapter(orderList, this));
    }

    /**
     * When the user clicks on the "View Order" button
     * and the operation is successful, the OrderPageActivity.
     */
    @Override
    public void onSuccessfulViewOrder(String orderId) {
        Intent intent = new Intent(DraftOrdersActivity.this, OrderPageActivity.class);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("orderId", orderId);
        intent.putExtra("isDraft", true);
        startActivity(intent);
    }

    /**
     * When the user clicks on the "View Order" button
     * this method is called and starts the view order proccess.
     */
    @Override
    public void viewOrder(String orderId) {
        viewModel.getPresenter().onViewOrder(orderId);
    }
}
