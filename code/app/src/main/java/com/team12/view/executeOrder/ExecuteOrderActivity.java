package com.team12.view.executeOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.view.managerMain.ManagerMainActivity;
import com.team12.warehousemanager.OrderLine;
import com.team12.warehousemanager.R;

import java.util.List;

/**
 * This class serves as the activity of the Execute Order Page.
 * Only the manager has access to this page.
 * It allows them to execute an order.
 */
public class ExecuteOrderActivity extends AppCompatActivity implements ExecuteOrderView {

    private ExecuteOrderViewModel viewModel;
    private String orderId = "";

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_order);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            orderId = intent.getStringExtra("chosenOrderId");
        }

        viewModel = new ViewModelProvider(this).get(ExecuteOrderViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().setOrder(orderId);
        List<OrderLine> orderLineList = viewModel.getPresenter().getOrderLineResults();

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.execute_order_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExecuteOrderRecyclerViewAdapter(orderLineList));

        findViewById(R.id.execute_order_execute_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.getPresenter().onExecuteOrder();
            }
        });

        findViewById(R.id.execute_order_cancel_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.getPresenter().onCancelOrder();
            }
        });
    }

    /**
     * When the manager clicks on the "Execute Order" button
     * and the operation is successful, the OrderInboxActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulOrderExecution() {
        Intent intent = new Intent(ExecuteOrderActivity.this, ManagerMainActivity.class);
        startActivity(intent);
    }

    /**
     * When the manager clicks on the "Cancel Order" button
     * and the operation is successful, the OrderInboxActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulOrderCancel() {
        Intent intent = new Intent(ExecuteOrderActivity.this, ManagerMainActivity.class);
        startActivity(intent);
    }
}
