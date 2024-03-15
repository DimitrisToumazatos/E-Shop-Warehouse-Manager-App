package com.team12.view.logInAsCustomer;

import com.team12.memorydao.UserAccountDAOMemory;
import com.team12.warehousemanager.UserAccount;

/**
 * This is the Customer's Log in page Presenter.
 */
public class LogInAsCustomerPresenter {

    LogInAsCustomerView view;
    UserAccountDAOMemory users;

    /**
     * Constructor.
     * Sets up the presenter.
     * @param view The Instance of the view
     */
    public LogInAsCustomerPresenter(LogInAsCustomerView view, UserAccountDAOMemory users) {
        this.view = view;
        this.users = users;
    }

    /**
     * When the Customer clicks on the "Log In" button,
     * the log in operation starts.
     * If successful, the Customer gets logged in,
     * else the system will provide an appropriate message.
     */
    void onLogInButton(String email, String password, boolean logInButtonEnabled) {
        String ERROR_INVALID_CREDENTIALS_TITLE = "Invalid Credentials";
        String ERROR_INVALID_CREDENTIALS_MSG = "No account found that corresponds to this email and password combination. Try again.";

        String TOAST_BUTTON_MSG = "Please fill all required fields.";
        if (!logInButtonEnabled) {
            view.showToast(TOAST_BUTTON_MSG);
            return;
        }

        email = email.toLowerCase();

        // Search for matching user email and password combination
        for (UserAccount user : users.findAll()) {
            String userEmail = user.getEmail().toString();
            String userPassword = user.getPassword();

            if (userEmail.equals(email) && userPassword.equals(password)) {
                view.onSuccessfulLogIn(user);
                return;
            }
        }

        view.showError(ERROR_INVALID_CREDENTIALS_TITLE, ERROR_INVALID_CREDENTIALS_MSG);
    }
}
