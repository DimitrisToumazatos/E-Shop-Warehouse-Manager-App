package com.team12.memorydao;

import com.team12.dao.ManagerAccountDAO;
import com.team12.warehousemanager.ManagerAccount;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is used by the app as the database for the ManagerAccount.
 * It provides necessary methods that help with the management of the the data.
 */
public class ManagerAccountDAOMemory implements ManagerAccountDAO {
    protected static ArrayList<ManagerAccount> entities = new ArrayList<>();

    /**
     * Deletes the given Manager from the database.
     * @param entity The Manager
     */
    public void delete(ManagerAccount entity) {
        entities.remove(entity);
    }

    /**
     * Returns a list containing all the Managers in the database.
     * Basically, a list that has only one item.
     * @return A list containing all the Managers in the database
     */
    public List<ManagerAccount> findAll() {
        return new ArrayList<>(entities);
    }

    /**
     * Saves a Manager in the database.
     * @param entity The Manager
     */
    public void save(ManagerAccount entity) {
        entities.add(entity);
    }

    /**
     * Finds the The Manager with the given ID and returns it.
     * Otherwise, if they do not exist in the database, it returns NULL.
     * @param managerId The ID of a Manager
     * @return The Manager with the given ID or
     * NULL if they do not exist in the database
     */
    public ManagerAccount find(int managerId) {
        for (ManagerAccount manager : entities) {
            if (manager.getId() == managerId)
                return manager;
        }
        return null;
    }

    /**
     * Returns the next available ID for item to get put in the database.
     * @return The next available ID for item to get put in the database
     */
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size() - 1).getId() + 1 : 1);
    }
}
