package com.team12.dao;

import com.team12.warehousemanager.ManagerAccount;

import java.util.List;

/**
 * This Interface is used by the app as the database for the ManagerAccount.
 */
public interface ManagerAccountDAO {
    void delete(ManagerAccount entity);

    List<ManagerAccount> findAll();

    void save(ManagerAccount entity);

    ManagerAccount find(int managerId);

    int nextId();
}
