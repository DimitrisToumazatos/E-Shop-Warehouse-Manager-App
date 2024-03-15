package com.team12.memorydao;

import com.team12.dao.UserAccountDAO;
import com.team12.warehousemanager.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by the app as the database for the UserAccounts.
 * It provides necessary methods that help with the management of the the data.
 */
public class UserAccountDAOMemory implements UserAccountDAO {
    public static ArrayList<UserAccount> users = new ArrayList<>();

    /**
     * Deletes the given User/Customer from the database.
     * @param entity The User/Customer
     */
    public void delete(UserAccount entity) {
        users.remove(entity);
    }

    /**
     * Returns a list containing all the Users in the database.
     * @return A list containing all the Users in the database
     */
    public List<UserAccount> findAll() {
        return new ArrayList<>(users);
    }

    /**
     * Saves a User in the database.
     * @param entity The User
     */
    public void save(UserAccount entity) {
        users.add(entity);
    }

    /**
     * Finds the The User with the given ID and returns it.
     * Otherwise, if they do not exist in the database, it returns NULL.
     * @param userId The ID of a User
     * @return The User with the given ID or
     * NULL if they do not exist in the database
     */
    public UserAccount find(int userId) {
        for (UserAccount user : users)
            if (user.getId() == userId)
                return user;

        return null;
    }

    /**
     * Returns the next available ID for item to get put in the database.
     * @return The next available ID for item to get put in the database
     */
    @Override
    public UserAccount findUserByEmail(String email) {
        for (UserAccount user : users) {
            if (user.getEmail().toString().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return (users.size() > 0 ? users.get(users.size() - 1).getId() + 1 : 1);
    }
}
