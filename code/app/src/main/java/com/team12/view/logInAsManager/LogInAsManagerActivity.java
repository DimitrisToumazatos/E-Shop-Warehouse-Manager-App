package com.team12.view.logInAsManager;

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

import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.view.managerMain.ManagerMainActivity;
import com.team12.view.utils.ViewUtils;
import com.team12.warehousemanager.ManagerAccount;
import com.team12.warehousemanager.R;

/**
 * This activity serves as the Managers's Log in page of the app.
 */
public class LogInAsManagerActivity extends AppCompatActivity implements LogInAsManagerView {

    private EditText emailField, passwordField;
    private Button logInButton;
    private boolean logInButtonEnabled;
    private String email, password;
    TextWatcher inputFieldsWatcher = new TextWatcher() {
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
            email = ViewUtils.getTextFromEditTextElement(emailField);
            password = ViewUtils.getTextFromEditTextElement(passwordField);
            if (!email.isEmpty() && !password.isEmpty()) {
                logInButton.setAlpha(1.0f);
                logInButtonEnabled = true;
            } else {
                logInButton.setAlpha(0.5f);
                logInButtonEnabled = false;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_as_manager);

        final LogInAsManagerPresenter presenter = new LogInAsManagerPresenter(this, new ManagerAccountDAOMemory());

        emailField = findViewById(R.id.log_in_manager_email_field);
        passwordField = findViewById(R.id.log_in_manager_password_field);
        logInButton = findViewById(R.id.log_in_manager_button);

        // Log in button starts disabled on low opacity
        logInButton.setAlpha(.5f);
        emailField.addTextChangedListener(inputFieldsWatcher);
        passwordField.addTextChangedListener(inputFieldsWatcher);

        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onLogInButton(email, password, logInButtonEnabled);
            }
        });
    }

    /**
     * This when the Manager clicks on the "Log In" button
     * and the operation is successful, the ManagerMainActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulLogIn(ManagerAccount manager) {
        Intent intent = new Intent(LogInAsManagerActivity.this, ManagerMainActivity.class);
        startActivity(intent);
    }

    /**
     * This when the Manager clicks on the "Log In" button
     * and the operation is unsuccessful (wrong email or password),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showError(String title, String message) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * This when the Manager clicks on the "Log In" button
     * and the operation is unsuccessful
     * (the customer did not provide an email or a password),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
