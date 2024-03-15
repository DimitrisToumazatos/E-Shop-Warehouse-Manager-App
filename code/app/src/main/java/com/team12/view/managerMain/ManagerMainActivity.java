package com.team12.view.managerMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.view.homePage.HomePageActivity;
import com.team12.view.orderInbox.OrderInboxActivity;
import com.team12.view.recallBatch.RecallBatchActivity;
import com.team12.view.restockProducts.RestockProductsActivity;
import com.team12.warehousemanager.R;

/**
 * This is the Manager's Main page of the app.
 * It serves as an action manu that allows the
 * manager to:
 * 1) View his inbox
 * 2) Restock products
 * 3) Recall ordered medicines
 * 4) View Historical statistics
 */
public class ManagerMainActivity extends AppCompatActivity implements ManagerMainView {

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        final ManagerMainPresenter presenter = new ManagerMainPresenter(this, new ManagerAccountDAOMemory());

        // Personalize Welcome Message
        TextView welcomeMessageElement = findViewById(R.id.manager_main_header);
        String managerName = presenter.getManagerName();
        String welcomeMessage = String.format("Welcome,\n%s 👋", managerName);
        welcomeMessageElement.setText(welcomeMessage);

        findViewById(R.id.viewOrderInboxBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onViewOrderInboxButton();
            }
        });

        findViewById(R.id.restockProductsBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onRestockProductsButton();
            }
        });

        findViewById(R.id.onRecallOrderedMedicine).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onRecallOrderedMedicineButton();
            }
        });

        findViewById(R.id.manager_main_log_out_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogOut();
            }
        });
    }

    /**
     * When the manager clicks on the "View Order Inbox" button,
     * the OrderInboxActivity starts and they are taken to this page.
     */
    @Override
    public void viewOrderInboxButton(ManagerAccountDAOMemory managerMemory) {
        Intent intent = new Intent(ManagerMainActivity.this, OrderInboxActivity.class);
        startActivity(intent);
    }

    /**
     * When the manager clicks on the "Restock Products" button,
     * the RestockProductsActivity starts and they are taken to this page.
     */
    @Override
    public void restockProductsButton(ManagerAccountDAOMemory managerMemory) {
        Intent intent = new Intent(ManagerMainActivity.this, RestockProductsActivity.class);
        startActivity(intent);
    }

    /**
     * When the manager clicks on the "Recall Medicine" button,
     * the ????? starts and they are taken to this page.
     */
    @Override
    public void recallOrderedMedicineButton(ManagerAccountDAOMemory managerMemory) {
        Intent intent = new Intent(ManagerMainActivity.this, RecallBatchActivity.class);
        startActivity(intent);
    }

    /**
     * When the manager clicks on the "View Statistics" button,
     * the ????? starts and they are taken to this page.
     */
    @Override
    public void viewClientStatisticsButton(ManagerAccountDAOMemory managerMemory) {

    }

    @Override
    public void onSuccessfulLogOut() {
        Intent intent = new Intent(ManagerMainActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}
