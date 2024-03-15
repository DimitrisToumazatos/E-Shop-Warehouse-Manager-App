package com.team12.view.createAccount;

import com.team12.memorydao.UserAccountDAOMemory;
import com.team12.warehousemanager.Address;
import com.team12.warehousemanager.Email;
import com.team12.warehousemanager.UserAccount;

/**
 * This is the Sign Up in page Presenter.
 */
public class CreateAccountPresenter {

    CreateAccountView view;

    UserAccountDAOMemory users;

    /**
     * Constructor.
     * Sets up the presenter.
     * @param view The Instance of the view
     */
    public CreateAccountPresenter(CreateAccountView view, UserAccountDAOMemory users) {
        this.view = view;
        this.users = users;
    }

    /**
     * When the customer clicks on the "Sign Up" button,
     * the sign up operation starts.
     * If successful, a new User/Customer account is created
     * and the customer gets logged in with that account,
     * else the system will provide an appropriate message.
     */
    void onSignUp(
            String legalName,
            String TIN,
            String postalCode,
            String country,
            String state,
            String city,
            String street,
            String buildingNumber,
            String email,
            String password,
            boolean signUpButtonEnabled
    ) {
        String ERROR_DUPLICATE_EMAIL_TITLE = "Email Already In Use";
        String ERROR_DUPLICATE_EMAIL_MSG = "An account for this email already exists";
        String ERROR_INVALID_EMAIL_TITLE = "Invalid Email";
        String ERROR_INVALID_EMAIL_MSG = "Please enter a valid email address";

        String TOAST_BUTTON_MSG = "Please fill all required fields.";
        if (!signUpButtonEnabled) {
            view.showToast(TOAST_BUTTON_MSG);
            return;
        }

        email = email.toLowerCase();

        // Invalid email
        if (!Email.isValid(email)) {
            view.showError(ERROR_INVALID_EMAIL_TITLE, ERROR_INVALID_EMAIL_MSG);
            return;
        }

        // Duplicate email used
        if (users.findUserByEmail(email) != null) {
            view.showError(ERROR_DUPLICATE_EMAIL_TITLE, ERROR_DUPLICATE_EMAIL_MSG);
            return;
        }

        // Create instances with given input
        Address userAddress = new Address(postalCode, country, state, city, street, buildingNumber);
        Email userEmail = new Email(email);
        UserAccount newUserAccount = new UserAccount(users.nextId(), legalName, TIN, userAddress, userEmail, password);

        // Save new account instance in memory
        users.save(newUserAccount);

        view.onSuccessfulSignUp(newUserAccount.getEmail().toString());
    }
}
