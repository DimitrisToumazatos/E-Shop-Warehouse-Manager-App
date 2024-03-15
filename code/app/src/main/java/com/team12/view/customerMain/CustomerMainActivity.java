package com.team12.view.customerMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.team12.memorydao.UserAccountDAOMemory;
import com.team12.view.draftOrders.DraftOrdersActivity;
import com.team12.view.homePage.HomePageActivity;
import com.team12.view.orderPage.OrderPageActivity;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.R;

import java.util.List;

/**
 * This is the Customer Main Page of the app. It gives
 * the Customer the option to create a new order or continue a draft order.
 */
public class CustomerMainActivity extends AppCompatActivity implements CustomerMainView {

    Button draftOrderButton;
    boolean draftOrderButtonEnabled;

    private RelativeLayout relativeLayout;

    private CustomerMainPresenter presenter;
    private String userEmail = "";

    /**
     * Creates the layout and sets ups the activity.
     *
     * @param savedInstanceState The Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        if (savedInstanceState == null) {
            userEmail = getIntent().getStringExtra("userEmail");
        }

        presenter = new CustomerMainPresenter(this, new UserAccountDAOMemory());
        presenter.setUser(userEmail);

        // Personalize Welcome Message
        TextView welcomeMessageElement = findViewById(R.id.customer_main_header);
        String userFirstName = presenter.getUserFirstName();
        String welcomeMessage = String.format("Welcome,\n%s 👋", userFirstName);
        welcomeMessageElement.setText(welcomeMessage);

        // New Mail Popup
        relativeLayout = findViewById(R.id.customer_main_relative_layout);

        // Draft Order Button is disabled and on low opacity
        // if the user does not have any draft orders
        List<AbstractOrder> draftOrders = presenter.getDraftOrderResults();
        draftOrderButton = findViewById(R.id.customer_main_draft_order_button);
        if (!draftOrders.isEmpty()) {
            draftOrderButton.setAlpha(1.0f);
            draftOrderButtonEnabled = true;
        } else {
            draftOrderButton.setAlpha(0.5f);
            draftOrderButtonEnabled = false;
        }

        findViewById(R.id.customer_main_new_order_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onNewOrder();
            }
        });

        draftOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onDraftOrder(draftOrderButtonEnabled);
            }
        });

        findViewById(R.id.customer_main_log_out_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogOut();
            }
        });

        findViewById(R.id.customer_main_view_mail_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onViewMail();
            }
        });
    }

    @Override
    public void onSuccessfulViewMail(String emailBody) {
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_mail, null);

        // Create and show mail popup
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        // New Mail Popup
        PopupWindow mailPopup = new PopupWindow(pop_up, width, height, true);
        mailPopup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        TextView body = pop_up.findViewById(R.id.popup_email_body);
        body.setText(emailBody);
    }

    /**
     * This when the Customer clicks on the "Create New Order" button
     * and the operation is successful, the ???????
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulNewOrder() {
        Intent intent = new Intent(CustomerMainActivity.this, OrderPageActivity.class);
        intent.putExtra("userEmail", userEmail);
        startActivity(intent);
    }

    /**
     * This when the Customer clicks on the "Log Out" button
     * and the operation is successful, the HomePageActivity
     * starts and they are taken to this page.
     */
    @Override
    public void onSuccessfulDraftOrder() {
        Intent intent = new Intent(CustomerMainActivity.this, DraftOrdersActivity.class);
        intent.putExtra("userEmail", userEmail);
        startActivity(intent);
    }

    @Override
    public void onSuccessfulLogOut() {
        Intent intent = new Intent(CustomerMainActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    /**
     * This when the Customer clicks on the "Continue Draft" button
     * and the operation is unsuccessful
     * (the customer did not have any draft orders),
     * then a pop up message appears on their screen.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
