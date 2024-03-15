package com.team12.view.homePage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.team12.memorydao.MemoryInitializer;
import com.team12.view.createAccount.CreateAccountActivity;
import com.team12.view.logInAsCustomer.LogInAsCustomerActivity;
import com.team12.view.logInAsManager.LogInAsManagerActivity;
import com.team12.warehousemanager.R;

/**
 * This is the Home page of the app. It gives the user the option to log in as
 * a customer/manager or create a new customer account.
 */
public class HomePageActivity extends AppCompatActivity implements HomePageView {

    private static boolean initialized = false;

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final HomePagePresenter presenter = new HomePagePresenter(this);

        findViewById(R.id.home_manager_log_in_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onLogInAsManager();
            }
        });

        findViewById(R.id.home_customer_log_in_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onLogInAsCustomer();
            }
        });

        findViewById(R.id.home_sign_up_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onCreateAccount();
            }
        });

        if (!initialized) {
            new MemoryInitializer().prepareData();
            initialized = true;
        }
    }


    /**
     * When the user clicks on Manager Log In, the LogInAsManagerActivity
     * starts and they are taken to this page.
     */
    @Override
    public void logInAsManager() {
        Intent intent = new Intent(HomePageActivity.this, LogInAsManagerActivity.class);
        startActivity(intent);
    }

    /**
     * When the user clicks on Customer Log In, the LogInAsCustomerActivity
     * starts and they are taken to this page.
     */
    @Override
    public void logInAsCustomer() {
        Intent intent = new Intent(HomePageActivity.this, LogInAsCustomerActivity.class);
        startActivity(intent);
    }

    /**
     * When the user clicks on Customer Sign Up, the LogInAsManagerActivity
     * starts and they are taken to this page.
     */
    @Override
    public void createAccount() {
        Intent intent = new Intent(HomePageActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
