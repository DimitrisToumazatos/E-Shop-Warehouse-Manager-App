package com.team12.view.recallBatch;

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
import com.team12.memorydao.UserAccountDAOMemory;
import com.team12.view.managerMain.ManagerMainActivity;
import com.team12.view.utils.ViewUtils;
import com.team12.warehousemanager.R;

public class RecallBatchActivity extends AppCompatActivity implements RecallBatchView {

    private EditText eofField, batchIdField;
    private Button recallButton;
    private boolean recallButtonEnabled;
    private String EOF, batchID;
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
            if (!EOF.isEmpty() && !batchID.isEmpty()) {
                recallButton.setAlpha(1.0f);
                recallButtonEnabled = true;
            } else {
                recallButton.setAlpha(0.5f);
                recallButtonEnabled = false;
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
        setContentView(R.layout.activity_recall_batch);

        final RecallBatchPresenter presenter = new RecallBatchPresenter(this, new OrderDAOMemory(), new ProductTypeDAOMemory(), new UserAccountDAOMemory());

        eofField = findViewById(R.id.recall_batch_EOF_field);
        batchIdField = findViewById(R.id.recall_batch_batch_field);
        recallButton = findViewById(R.id.recall_batch_button);

        // Restock button starts disabled on low opacity
        recallButton.setAlpha(.5f);
        eofField.addTextChangedListener(inputFieldsWatcher);
        batchIdField.addTextChangedListener(inputFieldsWatcher);

        recallButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onRecallButton(EOF, batchID, recallButtonEnabled);
            }
        });
    }

    @Override
    public void onSuccessfulRecall() {
        Intent intent = new Intent(RecallBatchActivity.this, ManagerMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String title, String message) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
