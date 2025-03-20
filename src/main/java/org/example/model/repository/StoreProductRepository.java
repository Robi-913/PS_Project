package org.example.model.repository;

import org.example.model.StoreProduct;

import java.util.List;

public interface StoreProductRepository {
    boolean addStoreProduct(StoreProduct storeProduct);

    boolean updateStoreProduct(StoreProduct storeProduct);

    boolean deleteStoreProduct(int id);

    List<StoreProduct> getAllStoreProducts();

    StoreProduct getStoreProductById(int id);

    List<StoreProduct> getAllStoreProductsByStoreId(int storeId);
}
