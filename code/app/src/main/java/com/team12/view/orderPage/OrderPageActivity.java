package com.team12.view.orderPage;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.view.customerMain.CustomerMainActivity;
import com.team12.view.utils.ViewUtils;
import com.team12.warehousemanager.OrderLine;
import com.team12.warehousemanager.R;

import java.util.List;

/**
 * This class serves as the activity of the Order Page.
 * It allows the Customer to create/Save Orders.
 */
public class OrderPageActivity extends AppCompatActivity implements OrderPageView, OrderPageRecyclerViewAdapter.ItemSelectionListener {

    OrderPageViewModel viewModel;
    String userEmail = "";
    
    // Add New Product Popup
    private PopupWindow addProductPopup;
    private EditText eofField, quantityField;

    private Button confirmButton;
    private boolean confirmButtonEnabled;
    private String newProductEOF, newProductQuantity;
    TextWatcher addProductWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        /**
         * When the user writes or deletes text on the screen this method is called.
         * If the user has not given an email or a password, then the log in
         * button gets dimmed and turned off.
         * @param s CharSequence
         * @param start int
         * @param before int
         * @param count int
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new employee popup
            newProductEOF = ViewUtils.getTextFromEditTextElement(eofField);
            newProductQuantity = ViewUtils.getTextFromEditTextElement(quantityField);
            if (!newProductEOF.isEmpty() && !newProductQuantity.isEmpty()) {
                confirmButton.setAlpha(1.0f);
                confirmButton.setAlpha(1.0f);
                confirmButtonEnabled = true;
            } else {
                confirmButton.setAlpha(.5f);
                confirmButtonEnabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * Creates the layout and sets ups the activity.
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        boolean isDraft = false;
        boolean inProgress = false;

        // Setup Order
        if (savedInstanceState == null) {
            userEmail = getIntent().getStringExtra("userEmail");
            isDraft = getIntent().getBooleanExtra("isDraft", false);
            inProgress = getIntent().getBooleanExtra("inProgress", false);
        }

        viewModel = new ViewModelProvider(this).get(OrderPageViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().setUser(userEmail);

        // Determine how to load order
        if (inProgress) {
            // This is only available after an activity refresh
            viewModel.getPresenter().loadOrderInProgress();
        } else if (isDraft) {
            // Continue draft order
            viewModel.getPresenter().setOrder(getIntent().getStringExtra("orderId"));
        } else {
            // Start new order
            viewModel.getPresenter().setOrder();
        }

        List<OrderLine> orderLineList = viewModel.getPresenter().getOrderLineResults();

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.order_page_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderPageRecyclerViewAdapter(orderLineList, this));

        // Add New Product Popup
        Button addProductButton = findViewById(R.id.order_page_add_product_button);
        RelativeLayout relativeLayout = findViewById(R.id.order_page_relative_layout);

        // Add On Click Listeners for the three page buttons
        findViewById(R.id.order_page_submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onOrderSubmission();
            }
        });

        findViewById(R.id.order_page_save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onOrderSave();
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_product, null);

                // Create and show add product popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                addProductPopup = new PopupWindow(pop_up, width, height, true);
                addProductPopup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                eofField = pop_up.findViewById(R.id.popup_add_product_EOF_field);
                quantityField = pop_up.findViewById(R.id.popup_add_product_quantity_field);
                eofField.addTextChangedListener(addProductWatcher);
                quantityField.addTextChangedListener(addProductWatcher);

                confirmButton = pop_up.findViewById(R.id.popup_add_product_confirm_button);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the confirm button
                    // inside the add new product popup
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onConfirm(newProductEOF, newProductQuantity, confirmButtonEnabled);
                    }
                });

                // Confirm button starts disabled and on low opacity
                confirmButtonEnabled = false;
                confirmButton.setAlpha(.5f);
            }
        });
    }

    /*
        OrderPageRecyclerViewAdapter.ItemSelectionListener
                       Methods
     */

    /**
     * User clicked the remove button inside
     * an order line list item
     *
     * @param orderLine The order line to be removed
     */
    @Override
    public void removeOrderLine(OrderLine orderLine) {
        viewModel.getPresenter().onRemoveOrderLine(orderLine);
    }

    /*
        OrderPageView Methods
     */

    /**
     * Closes the popup and
     * restarts the current activity
     */
    @Override
    public void onSuccessfulAddProduct() {
        getIntent().putExtra("inProgress", true);
        addProductPopup.dismiss();
        finish();
        startActivity(getIntent());
    }

    /**
     * Restarts the current activity
     */
    @Override
    public void onSuccessfulProductRemoval() {
        getIntent().putExtra("inProgress", true);
        finish();
        startActivity(getIntent());
    }

    /**
     * Order has been submitted or saved as draft
     * and the activity ends.
     * Returns to the customer main page activity.
     */
    @Override
    public void onSuccessfulOrderCompletion() {
        Intent intent = new Intent(OrderPageActivity.this, CustomerMainActivity.class);
        intent.putExtra("userEmail", userEmail);
        startActivity(intent);
    }

    /**
     * This when the user clicks on the "Confirm" button
     * inside the add new product popup and the operation
     * is unsuccessful (invalid EOF or quantity),
     * then an error message appears.
     */
    @Override
    public void showError(String title, String message) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * This when the user clicks on the "Confirm" button
     * inside the add new product popup and the operation
     * is unsuccessful. (the user did not provide an EOF number or a quantity).
     * A toast message appears on their screen.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
