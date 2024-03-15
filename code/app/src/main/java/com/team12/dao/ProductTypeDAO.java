package com.team12.dao;

import com.team12.warehousemanager.ProductType;

import java.util.List;

/**
 * This Interface is used by the app as the database for the Product Types.
 */
public interface ProductTypeDAO {
    void delete(ProductType product);

    List<ProductType> getAllProducts();

    void save(ProductType product);

    ProductType findByEOF(String EOF);
}
