package com.team12.dao;

import com.team12.warehousemanager.UserAccount;

import java.util.List;

/**
 * This Interface is used by the app as the database for the UserAccounts.
 */
public interface UserAccountDAO {
    void delete(UserAccount entity);

    List<UserAccount> findAll();

    void save(UserAccount entity);

    UserAccount find(int userId);

    UserAccount findUserByEmail(String email);

    int nextId();
}
