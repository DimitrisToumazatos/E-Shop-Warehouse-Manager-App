package com.team12.warehousemanager;

import androidx.annotation.NonNull;

/**
 * The Address.
 * It is used as the Address of the Accounts.
 * It contains information such as the street of the address, the number of
 * the building, postal code etc.
 */
public class Address {
    private String postalCode;
    private String country;
    private String state;
    private String city;
    private String street;
    private String buildingNumber;

    /**
     * Constructor.
     * Sets up the basic attributes of an Address.
     *
     * @param postalCode     The postal code
     * @param country        The country
     * @param state          The state
     * @param city           The city
     * @param street         The street
     * @param buildingNumber The number of the building
     */
    public Address(String postalCode, String country, String state, String city, String street, String buildingNumber) {
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    /**
     * Returns the postal code of the address.
     *
     * @return The postal code of the address
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the address.
     *
     * @param postalCode The postal code of the address
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the country of the address.
     *
     * @return The country of the address
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the address.
     *
     * @param country The country of the address
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the state of the address.
     *
     * @return The state of the address
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the address.
     *
     * @param state The state of the address
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the street of the address.
     *
     * @return The street of the address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     *
     * @param street The street of the address
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the number of the building of the address.
     *
     * @return The number of the building of the address
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Returns the number of the building of the address.
     *
     * @param buildingNumber The number of the building of the address
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * Returns the city of the address.
     *
     * @return The city of the address
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     *
     * @param city The city of the address
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the representation of the Address object in a String form.
     *
     * @return the representation of the Address object in a String form
     */
    @NonNull
    public String toString() {
        return String.format("%s %s, %s %s, %s, %s", street, buildingNumber, city, postalCode, state, country);
    }
}
