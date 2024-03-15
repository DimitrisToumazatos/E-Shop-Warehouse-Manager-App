package com.team12.view.restockProducts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.team12.memorydao.OrderDAOMemory;
import com.team12.memorydao.ProductTypeDAOMemory;
import com.team12.view.managerMain.ManagerMainActivity;
import com.team12.view.utils.ViewUtils;
import com.team12.warehousemanager.R;

/**
 * This activity serves as the Restock Products page of the app.
 * Only the Manager has access to this page and it gives them
 * the option to Restock the Products in the warehouse.
 */
public class RestockProductsActivity extends AppCompatActivity implements RestockProductsView {

    private EditText eofField, batchIdField, quantityField;
    private Button restockButton;
    private boolean restockButtonEnabled;
    private String EOF, batchID, quantity;
    TextWatcher inputFieldsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        /**
         * When the user writes or deletes text on the screen this method is called.
         * If the user has not satisfied all the fields, then the sign up
         * button gets dimmed and turned off.
         * @param s CharSequence
         * @param start int
         * @param before int
         * @param count int
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EOF = ViewUtils.getTextFromEditTextElement(eofField);
            batchID = ViewUtils.getTextFromEditTextElement(batchIdField);
            quantity = ViewUtils.getTextFromEditTextElement(quantityField);
            if (!EOF.isEmpty() && !batchID.isEmpty() && !quantity.isEmpty()) {
                restockButton.setAlpha(1.0f);
                restockButtonEnabled = true;
            } else {
                restockButton.setAlpha(0.5f);
                restockButtonEnabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock_products);

        final RestockProductsPresenter presenter = new RestockProductsPresenter(this, new ProductTypeDAOMemory(), new OrderDAOMemory());

        eofField = findViewById(R.id.restock_products_EOF_field);
        batchIdField = findViewById(R.id.restock_products_batch_field);
        quantityField = findViewById(R.id.restock_products_quantity_field);
        restockButton = findViewById(R.id.restock_products_restock_button);

        // Restock button starts disabled on low opacity
        restockButton.setAlpha(.5f);
        eofField.addTextChangedListener(inputFieldsWatcher);
        batchIdField.addTextChangedListener(inputFieldsWatcher);
        quantityField.addTextChangedListener(inputFieldsWatcher);

        restockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onRestockButton(EOF, batchID, quantity, restockButtonEnabled);
            }
        });
    }

    /**
     * This when the Customer clicks on the "Restock" button
     * and the operation is successful, the ManagerMainActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulRestock() {
        Intent intent = new Intent(RestockProductsActivity.this, ManagerMainActivity.class);
        startActivity(intent);
    }

    /**
     * This when the Customer clicks on the "Restock" button
     * and the operation is unsuccessful (some field is wrong),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showError(String title, String message) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * This when the Customer clicks on the "Restock" button
     * and the operation is unsuccessful
     * (the customer did not provide all of the fields),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
