package com.team12.warehousemanager;

/**
 * The Abstract Account.
 * This class has features essential for both the Customers and the Manager.
 */
public abstract class AbstractAccount {

    private final int id;
    private final Email email;
    private String name;
    private String TIN;
    private Address address;

    private String password;

    /**
     * Constructor.
     * Sets up the basic attributes of an Account.
     *
     * @param id       The ID of the account
     * @param name     The name of the user
     * @param TIN      The TIN of the user
     * @param address  The address of the user
     * @param email    The email of the user
     * @param password The password of the account
     */
    public AbstractAccount(int id, String name, String TIN, Address address, Email email, String password) {
        this.id = id;
        this.name = name;
        this.TIN = TIN;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the ID of the account.
     *
     * @return The ID of the account
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the TIN of the user.
     *
     * @return The TIN of the user
     */
    public String getTIN() {
        return TIN;
    }

    /**
     * Sets the TIN of the user.
     *
     * @param TIN The TIN of the user
     */
    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    /**
     * Returns the address of the user.
     *
     * @return The address of the user
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     *
     * @param address The address of the user
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns teh email of the user.
     *
     * @return The email of the user
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user
     */
    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
