package com.team12.view.logInAsManager;

import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.warehousemanager.ManagerAccount;

/**
 * This is the Manager's Log in page Presenter.
 */
public class LogInAsManagerPresenter {
    LogInAsManagerView view;
    ManagerAccountDAOMemory managers;

    /**
     * Constructor.
     * Sets up the presenter.
     * @param view The Instance of the view
     */
    public LogInAsManagerPresenter(LogInAsManagerView view, ManagerAccountDAOMemory managerMemory) {
        this.view = view;
        this.managers = managerMemory;
    }

    /**
     * When the Manager clicks on the "Log In" button,
     * the log in operation starts.
     * If successful, the Manager gets logged in,
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
        for (ManagerAccount manager : managers.findAll()) {
            String managerEmail = manager.getEmail().toString();
            String managerPassword = manager.getPassword();

            if (managerEmail.equals(email) && managerPassword.equals(password)) {
                view.onSuccessfulLogIn(manager);
                return;
            }
        }

        view.showError(ERROR_INVALID_CREDENTIALS_TITLE, ERROR_INVALID_CREDENTIALS_MSG);
    }
}
