package com.team12.dao;

import com.team12.warehousemanager.Address;
import com.team12.warehousemanager.Batch;
import com.team12.warehousemanager.Email;
import com.team12.warehousemanager.ManagerAccount;
import com.team12.warehousemanager.Order;
import com.team12.warehousemanager.ProductType;
import com.team12.warehousemanager.UserAccount;

import java.time.LocalDate;

/**
 * The Initializer.
 * This class sets up the database of the app.
 */
public abstract class Initializer {
    /**
     * Deletes everything currently in the database.
     */
    protected abstract void eraseData();

    /**
     * Initializes the database with test data.
     * ex. ProductTypes, Orders, UserAccounts, the ManagerAccount etc.
     */
    public void prepareData() {
        // delete previous data
        eraseData();

        // create a manager
        ManagerAccountDAO managerAccountDAO = getManagerAccountDAO();

        managerAccountDAO.save(new ManagerAccount(managerAccountDAO.nextId(), "admin", "999",
                new Address("18999", "Greece", "Attica", "Piraeus", "Piraeus", "499"),
                new Email("admin@mail.com"), "admin"));

        // create some users
        UserAccountDAO userAccountDAO = getUserAccountDAO();

        userAccountDAO.save(new UserAccount(userAccountDAO.nextId(), "Dimitris Toumazatos", "123",
                new Address("18344", "Greece", "Attica", "Piraeus", "Makrigianni", "12"),
                new Email("toumazatos@mail.com"), "12"));

        userAccountDAO.save(new UserAccount(userAccountDAO.nextId(), "Dimitris Fotogiannopoulos", "124",
                new Address("18566", "Greece", "Attica", "Athens", "Patision", "245"),
                new Email("fotogiannopoulos@mail.com"), "123"));

        userAccountDAO.save(new UserAccount(userAccountDAO.nextId(), "Alexios Papadopoulos-Siountris", "125",
                new Address("18198", "Greece", "Attica", "Athens", "Vouliagmenis", "134"),
                new Email("papadopoulos@mail.com"), "1234"));

        ProductTypeDAO productTypeDAO = getProductTypeDAO();

        productTypeDAO.save(new ProductType("Depon", "Aspirin", "1", 5));
        productTypeDAO.save(new ProductType("Panadol", "Aspirin", "2", 7));
        productTypeDAO.save(new ProductType("Otrivin", "Antiviral", "3", 8));
        productTypeDAO.save(new ProductType("Ronal", "Antiviral", "4", 9));
        productTypeDAO.save(new ProductType("Rifacol", "Antibiotic ", "5", 14));

        // Add Batches to the ProductType Objects
        LocalDate date = LocalDate.of(2023, 12, 2);
        productTypeDAO.findByEOF("1").addBatch(new Batch(date, "1", 100, "1"));
        productTypeDAO.findByEOF("1").addBatch(new Batch(date, "1", 190, "2"));
        productTypeDAO.findByEOF("2").addBatch(new Batch(date, "2", 40, "3"));
        productTypeDAO.findByEOF("3").addBatch(new Batch(date, "3", 140, "4"));
        productTypeDAO.findByEOF("3").addBatch(new Batch(date, "3", 90, "5"));
        productTypeDAO.findByEOF("3").addBatch(new Batch(date, "3", 200, "6"));
        date = LocalDate.of(2023, 6, 24);
        productTypeDAO.findByEOF("4").addBatch(new Batch(date, "4", 300, "7"));
        productTypeDAO.findByEOF("4").addBatch(new Batch(date, "4", 40, "8"));
        productTypeDAO.findByEOF("5").addBatch(new Batch(date, "5", 150, "9"));
        productTypeDAO.findByEOF("5").addBatch(new Batch(date, "5", 300, "10"));

        // Initialize Orders for testing
        OrderDAO orderDAO = getOrderDAO();
        Order order = new Order(LocalDate.now(), "1", userAccountDAO.find(1), true);
        order.addOrderLine(productTypeDAO.findByEOF("1"), 150);
        order.addOrderLine(productTypeDAO.findByEOF("2"), 120);
        order.addOrderLine(productTypeDAO.findByEOF("4"), 300);
        orderDAO.save(order);
        userAccountDAO.findUserByEmail("toumazatos@mail.com").addToOrderList(order);

        order = new Order(LocalDate.now(), "2", userAccountDAO.find(2));
        order.addOrderLine(productTypeDAO.findByEOF("3"), 200);
        orderDAO.save(order);
        userAccountDAO.findUserByEmail("papadopoulos@mail.com").addToOrderList(order);


        order = new Order(LocalDate.now(), "3", userAccountDAO.find(3));
        order.addOrderLine(productTypeDAO.findByEOF("5"), 150);
        orderDAO.save(order);
        userAccountDAO.findUserByEmail("fotogiannopoulos@mail.com").addToOrderList(order);

        // Add Draft Orders
        order = new Order(LocalDate.now(), "4", userAccountDAO.findUserByEmail("papadopoulos@mail.com"));
        order.setStatus("Draft");
        order.addOrderLine(productTypeDAO.findByEOF("5"), 150);
        orderDAO.save(order);
        userAccountDAO.findUserByEmail("papadopoulos@mail.com").addToOrderList(order);

        order = new Order(LocalDate.now(), "5", userAccountDAO.findUserByEmail("fotogiannopoulos@mail.com"));
        order.setStatus("Draft");
        order.addOrderLine(productTypeDAO.findByEOF("5"), 40);
        orderDAO.save(order);
        userAccountDAO.findUserByEmail("fotogiannopoulos@mail.com").addToOrderList(order);

        order = new Order(LocalDate.now(), "6", userAccountDAO.findUserByEmail("fotogiannopoulos@mail.com"));
        order.setStatus("Draft");
        order.addOrderLine(productTypeDAO.findByEOF("5"), 30);
        orderDAO.save(order);
        userAccountDAO.findUserByEmail("fotogiannopoulos@mail.com").addToOrderList(order);

    }

    public abstract UserAccountDAO getUserAccountDAO();

    public abstract ManagerAccountDAO getManagerAccountDAO();

    public abstract ProductTypeDAO getProductTypeDAO();

    public abstract OrderDAO getOrderDAO();
}
