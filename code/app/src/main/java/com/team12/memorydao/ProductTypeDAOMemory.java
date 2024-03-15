package com.team12.memorydao;

import com.team12.dao.ProductTypeDAO;
import com.team12.warehousemanager.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used by the app as the database for the Product Types.
 * It provides necessary methods that help with the management of the the data.
 */
public class ProductTypeDAOMemory implements ProductTypeDAO {
    protected static ArrayList<ProductType> products = new ArrayList<>();

    protected static HashMap<String, ProductType> eofToProduct = new HashMap<String, ProductType>();

    /**
     * Deletes the given Product Type from the database.
     * @param product The Product Type
     */
    public void delete(ProductType product) {
        products.remove(product);
        eofToProduct.remove(product.getEOF());
    }

    /**
     * Returns a list containing all the Product Types in the database.
     * @return A list containing all the Product Types in the database.
     */
    public List<ProductType> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Saves a Product Types in the database.
     * @param product The Product Type
     */
    public void save(ProductType product) {
        products.add(product);
        eofToProduct.put(product.getEOF(), product);
    }

    /**
     * Finds the The Product Type with the given ID and returns it.
     * Otherwise, if it does not exist in the database, it returns NULL.
     * @param EOF The ID of a Product Type
     * @return The Product Types with the given ID or
     * NULL if it does not exist in the database
     */
    public ProductType findByEOF(String EOF) {
        return eofToProduct.getOrDefault(EOF, null);
    }
}
