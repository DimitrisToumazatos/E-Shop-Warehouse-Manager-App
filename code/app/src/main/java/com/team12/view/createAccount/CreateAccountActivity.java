package com.team12.view.createAccount;

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

import com.team12.memorydao.UserAccountDAOMemory;
import com.team12.view.customerMain.CustomerMainActivity;
import com.team12.view.utils.ViewUtils;
import com.team12.warehousemanager.R;

/**
 * This is the Create Customer Account Page of the app. It gives
 * the Customer the option to create a new customer account.
 */
public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    // Text Fields
    private EditText legalNameField;
    private EditText tinField;
    private EditText postalCodeField;
    private EditText countryField;
    private EditText stateField;
    private EditText cityField;
    private EditText streetField;
    private EditText buildingNumberField;
    private EditText emailField;
    private EditText passwordField;

    // User Input
    private String legalName, TIN, postalCode, country, state, city, street, buildingNumber, email, password;

    private Button signUpButton;
    private Boolean signUpButtonEnabled = false;

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
            legalName = ViewUtils.getTextFromEditTextElement(legalNameField);
            TIN = ViewUtils.getTextFromEditTextElement(tinField);
            postalCode = ViewUtils.getTextFromEditTextElement(postalCodeField);
            country = ViewUtils.getTextFromEditTextElement(countryField);
            state = ViewUtils.getTextFromEditTextElement(stateField);
            city = ViewUtils.getTextFromEditTextElement(cityField);
            street = ViewUtils.getTextFromEditTextElement(streetField);
            buildingNumber = ViewUtils.getTextFromEditTextElement(buildingNumberField);
            email = ViewUtils.getTextFromEditTextElement(emailField);
            password = ViewUtils.getTextFromEditTextElement(passwordField);

            if (!legalName.isEmpty() && !TIN.isEmpty() && !postalCode.isEmpty() && !country.isEmpty() &&
                    !state.isEmpty() && !city.isEmpty() && !street.isEmpty() && !buildingNumber.isEmpty() &&
                    !email.isEmpty() && !password.isEmpty()
            ) {
                signUpButton.setAlpha(1.0f);
                signUpButtonEnabled = true;
            } else {
                signUpButton.setAlpha(0.5f);
                signUpButtonEnabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    private CreateAccountPresenter presenter;

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Get Button and Field Elements
        signUpButton = findViewById(R.id.create_account_sign_up_button);

        legalNameField = findViewById(R.id.create_account_name_text_field);
        tinField = findViewById(R.id.create_account_TIN_text_field);
        postalCodeField = findViewById(R.id.create_account_postal_code_text_field);
        countryField = findViewById(R.id.create_account_country_text_field);
        stateField = findViewById(R.id.create_account_state_text_field);
        cityField = findViewById(R.id.create_account_city_text_field);
        streetField = findViewById(R.id.create_account_street_text_field);
        buildingNumberField = findViewById(R.id.create_account_number_text_field);
        emailField = findViewById(R.id.create_account_email_text_field);
        passwordField = findViewById(R.id.create_account_password_field);

        presenter = new CreateAccountPresenter(this, new UserAccountDAOMemory());

        // Sign Up button starts disabled on low opacity
        signUpButton.setAlpha(.5f);

        // Add watcher for when fields are changed
        legalNameField.addTextChangedListener(inputFieldsWatcher);
        tinField.addTextChangedListener(inputFieldsWatcher);
        postalCodeField.addTextChangedListener(inputFieldsWatcher);
        countryField.addTextChangedListener(inputFieldsWatcher);
        stateField.addTextChangedListener(inputFieldsWatcher);
        cityField.addTextChangedListener(inputFieldsWatcher);
        streetField.addTextChangedListener(inputFieldsWatcher);
        buildingNumberField.addTextChangedListener(inputFieldsWatcher);
        emailField.addTextChangedListener(inputFieldsWatcher);
        passwordField.addTextChangedListener(inputFieldsWatcher);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSignUp(legalName, TIN, postalCode, country, state, city, street, buildingNumber, email, password, signUpButtonEnabled);
            }
        });
    }

    /**
     * This when the Customer clicks on the "Sign Up" button
     * and the operation is successful, the NewOrDraftActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulSignUp(String newUserEmail) {
        // Go to main user page
        Intent intent = new Intent(CreateAccountActivity.this, CustomerMainActivity.class);
        intent.putExtra("userEmail", newUserEmail);
        startActivity(intent);
    }

    /**
     * This when the Customer clicks on the "Sign Up" button
     * and the operation is unsuccessful (some field is wrong),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showError(String title, String message) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * This when the Customer clicks on the "Sign Up" button
     * and the operation is unsuccessful
     * (the customer did not provide all of the fields),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
