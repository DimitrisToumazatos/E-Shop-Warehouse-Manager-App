package com.team12.warehousemanager;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

/**
 * The Email.
 * It save the email of a user in 2 parts the username and the domain.
 * ex. name@mail.com
 * username = name
 * domain = mail.com
 */
public class Email {
    private String username;
    private String domain;

    /**
     * Constructor.
     * Sets up the basic attributes of an Email.
     *
     * @param email The email as a String
     */
    public Email(String email) {
        String[] parts = email.split("@");
        username = parts[0];
        domain = parts[1];
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Returns the username of the email.
     *
     * @return The username of the email
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the email.
     *
     * @param username The username of the email
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the domain of the email.
     *
     * @return The domain of the email
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the domain of the email.
     *
     * @param domain The domain of the email
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Returns the email as a String.
     *
     * @return The email as a String
     */
    public String getEmailAsString() {
        return String.format("%s@%s", username, domain);
    }

    /**
     * Sets the email.
     *
     * @param email The email as a String
     */
    public void setEmail(String email) {
        String[] parts = email.split("@");
        username = parts[0];
        domain = parts[1];
    }

    /**
     * Returnsthe representation of the Email object in a String form.
     *
     * @return the representation of the Email object in a String form
     */
    @NonNull
    public String toString() {
        return getEmailAsString();
    }
}
