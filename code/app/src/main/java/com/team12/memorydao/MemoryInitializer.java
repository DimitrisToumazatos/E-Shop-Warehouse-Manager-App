package com.team12.memorydao;

import com.team12.dao.Initializer;
import com.team12.dao.ManagerAccountDAO;
import com.team12.dao.OrderDAO;
import com.team12.dao.ProductTypeDAO;
import com.team12.dao.UserAccountDAO;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.ManagerAccount;
import com.team12.warehousemanager.ProductType;
import com.team12.warehousemanager.UserAccount;

/**
 * This class provides necessary methods for the database,
 * such as eraseData and get methods.
 */
public class MemoryInitializer extends Initializer {

    /**
     * Deletes everything in the database.
     * Useful for the initialization of the app.
     */
    @Override
    protected void eraseData() {

        for (UserAccount user : getUserAccountDAO().findAll()) {
            getUserAccountDAO().delete(user);
        }

        for (ManagerAccount manager : getManagerAccountDAO().findAll()) {
            getManagerAccountDAO().delete(manager);
        }

        for (ProductType product : getProductTypeDAO().getAllProducts()) {
            getProductTypeDAO().delete(product);
        }

        for (AbstractOrder order : getOrderDAO().getAllOrders()) {
            getOrderDAO().delete(order);
        }
    }

    /**
     * Returns the DAO of the User's Accounts
     *
     * @return The DAO of the User's Accounts
     */
    @Override
    public UserAccountDAO getUserAccountDAO() {
        return new UserAccountDAOMemory();
    }

    /**
     * Returns the DAO of the Managers' account, basically a list with only 1 account.
     *
     * @return The DAO of the Managers' account
     */
    @Override
    public ManagerAccountDAO getManagerAccountDAO() {
        return new ManagerAccountDAOMemory();
    }

    /**
     * Returns the DAO of the Product Types.
     *
     * @return the DAO of the Product Types
     */
    @Override
    public ProductTypeDAO getProductTypeDAO() {
        return new ProductTypeDAOMemory();
    }

    /**
     * Returns the DAO of the Orders.
     *
     * @return the DAO of the Orders
     */
    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOMemory();
    }
}
