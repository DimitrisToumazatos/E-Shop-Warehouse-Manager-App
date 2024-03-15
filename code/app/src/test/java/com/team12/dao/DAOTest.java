package com.team12.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.team12.memorydao.ManagerAccountDAOMemory;
import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.OrderDAOMemory;
import com.team12.memorydao.ProductTypeDAOMemory;
import com.team12.memorydao.UserAccountDAOMemory;
import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.Address;
import com.team12.warehousemanager.Email;
import com.team12.warehousemanager.ManagerAccount;
import com.team12.warehousemanager.Order;
import com.team12.warehousemanager.ProductType;
import com.team12.warehousemanager.UserAccount;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Test class for the basic functions of the database objects.
 */
public class DAOTest {
    private int INITIAL_USER_COUNT;
    private int INITIAL_MANAGER_COUNT ;
    private int INITIAL_PRODUCT_TYPE_COUNT;
    private int INITIAL_ORDER_COUNT;
    private ProductTypeDAO productTypeDao;
    private UserAccountDAO userAccountDao;
    private ManagerAccountDAO managerAccountDao;
    private OrderDAO orderDao;

    /**
     * Creates the database with the MemoryInitializer
     * and sets up the DAOs
     */
    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        productTypeDao = new ProductTypeDAOMemory();
        INITIAL_PRODUCT_TYPE_COUNT = productTypeDao.getAllProducts().size();

        userAccountDao = new UserAccountDAOMemory();
        INITIAL_USER_COUNT = userAccountDao.findAll().size();

        managerAccountDao = new ManagerAccountDAOMemory();
        INITIAL_MANAGER_COUNT = managerAccountDao.findAll().size();

        orderDao = new OrderDAOMemory();
        INITIAL_ORDER_COUNT = orderDao.getAllOrders().size();
    }

    /**
     * Find an Order that exists.
     */
    @Test
    public void findExistingOrderTest() {
        AbstractOrder order = orderDao.findByOrderId("2");
        assertEquals("2", order.getOrderID());
    }

    /**
     * Find a user that exists.
     */
    @Test
    public void findExistingUserTest() {
        UserAccount user = userAccountDao.find(1);
        assertEquals("Dimitris Toumazatos", user.getName());
    }

    /**
     * Find the manager that exists.
     */
    @Test
    public void findExistingManagerTest() {
        ManagerAccount manager = managerAccountDao.find(1);
        assertEquals("admin", manager.getName());
    }

    /**
     * Find a product type that exists.
     */
    @Test
    public void findExistingProductTypeTest() {
        ProductType product = productTypeDao.findByEOF("3");
        assertEquals("Otrivin", product.getName());
    }

    /**
     * Try to find a user that does not exist.
     */
    @Test
    public void findNonExistingUserTest() {
        assertNull(userAccountDao.find(97));
    }

    /**
     * Try to find a manager that does not exist.
     */
    @Test
    public void findNonExistingManagerTest() {
        assertNull(managerAccountDao.find(2));
    }

    /**
     * Try to find a product that does not exist.
     */
    @Test
    public void findNonExistingProductTest() {
        assertNull(productTypeDao.findByEOF("45674567"));
    }


    /**
     * Find an Order that does not exists.
     */
    @Test
    public void findNonExistingOrderTest() {
        assertNull(orderDao.findByOrderId("7"));
    }

    /**
     * Get the list of users.
     */
    @Test
    public void listAllUsersTest() {
        List<UserAccount> allUsers = userAccountDao.findAll();
        assertEquals(INITIAL_USER_COUNT, allUsers.size());
    }

    /**
     * Get the list of managers (only 1 item in the list since
     * since we only have 1 manager).
     */
    @Test
    public void listAllManagersTest() {
        List<ManagerAccount> allManagers = managerAccountDao.findAll();
        assertEquals(INITIAL_MANAGER_COUNT, allManagers.size());
    }

    /**
     * Get the list of product types.
     */
    @Test
    public void listAllProductsTest() {
        List<ProductType> allProducts = productTypeDao.getAllProducts();
        assertEquals(INITIAL_PRODUCT_TYPE_COUNT, allProducts.size());
    }

    /**
     * Get the list of Orders.
     */
    @Test
    public void listAllOrdersTest() {
        List<AbstractOrder> allOrders = orderDao.getAllOrders();
        assertEquals(INITIAL_ORDER_COUNT, allOrders.size());
    }


    /**
     * Save a new user
     */
    @Test
    public void saveUserTest() {
        UserAccount user = new UserAccount(userAccountDao.nextId(), "Test User", "545",
                new Address("845674", "Greece", "Attica", "Piraeus", "Poseidonos", "234"),
                new Email("test@mail.com"), "543");

        userAccountDao.save(user);
        assertEquals(INITIAL_USER_COUNT + 1, userAccountDao.findAll().size());
        assertNotNull(userAccountDao.find(user.getId()));
        assertTrue(userAccountDao.findAll().contains(user));
    }

    /**
     * Delete a user from the database
     */
    @Test
    public void deleteUserTest() {
        List<UserAccount> allUsers = userAccountDao.findAll();
        UserAccount user = allUsers.get(0);
        userAccountDao.delete(user);
        allUsers = userAccountDao.findAll();
        assertEquals(INITIAL_USER_COUNT - 1, allUsers.size());
        assertNull(userAccountDao.find(user.getId()));
    }

    /**
     * Save a new product in the database
     */
    @Test
    public void saveProductTest() {
        ProductType product = new ProductType("Eziksin", "Anti-Pain", "E7567", 11.99f);
        productTypeDao.save(product);
        assertEquals(INITIAL_PRODUCT_TYPE_COUNT + 1, productTypeDao.getAllProducts().size());
        assertNotNull(productTypeDao.findByEOF(product.getEOF()));
        assertTrue(productTypeDao.getAllProducts().contains(product));
    }

    /**
     * Delete a product from the database
     */
    @Test
    public void deleteProductTest() {
        List<ProductType> allProducts = productTypeDao.getAllProducts();
        ProductType product = allProducts.get(0);
        productTypeDao.delete(product);
        allProducts = productTypeDao.getAllProducts();
        assertEquals(INITIAL_PRODUCT_TYPE_COUNT - 1, allProducts.size());
        assertNull(productTypeDao.findByEOF(product.getEOF()));
    }

    /**
     * Save a new Order in the database.
     */
    @Test
    public void saveOrderTest() {
        AbstractOrder order = new Order(LocalDate.now(), "4", userAccountDao.find(1));
        orderDao.save(order);
        assertEquals(INITIAL_ORDER_COUNT + 1, orderDao.getAllOrders().size());
        assertNotNull(orderDao.findByOrderId(order.getOrderID()));
        assertTrue(orderDao.getAllOrders().contains(order));
    }

    /**
     * Delete an Order from the database.
     */
    @Test
    public void deleteOrderTest() {
        List<AbstractOrder> allOrders = orderDao.getAllOrders();
        AbstractOrder order = allOrders.get(0);
        orderDao.delete(order);
        allOrders = orderDao.getAllOrders();
        assertEquals(INITIAL_ORDER_COUNT - 1, allOrders.size());
        assertNull(orderDao.findByOrderId(order.getOrderID()));
    }

    /**
     * Test getOrderByStatus method in OrderDao.
     */
    @Test
    public void getOrderByStatusTest() {
        assertEquals(3, orderDao.getOrdersByStatus("New").size());
        assertEquals(3, orderDao.getOrdersByStatus("Draft").size());
        orderDao.findByOrderId("1").setStatus("Executed");
        assertEquals(1, orderDao.getOrdersByStatus("Executed").size());
    }

    /**
     * Test getOrdersInProgress and getOrderInProgressByUser method in OrderDao.
     */
    @Test
    public void getOrdersInProgressTest() {
        assertEquals(0, orderDao.getOrdersInProgress().size());
        assertNull(orderDao.getOrderInProgressByUser(userAccountDao.findUserByEmail("papadopoulos@mail.com")));
        orderDao.saveOrderInProgress(new Order("2", userAccountDao.findUserByEmail("papadopoulos@mail.com")));
        assertEquals(1, orderDao.getOrdersInProgress().size());
    }
}
