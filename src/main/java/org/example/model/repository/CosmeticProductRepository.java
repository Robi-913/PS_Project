package org.example.model.repository;

import org.example.model.CosmeticProduct;

import java.util.List;

public interface CosmeticProductRepository {
    boolean addCosmeticProduct(CosmeticProduct product);

    boolean updateCosmeticProduct(CosmeticProduct product);

    boolean deleteCosmeticProduct(int id);

    List<CosmeticProduct> getAllCosmeticProducts();

    CosmeticProduct getCosmeticProductById(int id);

    List<CosmeticProduct> getCosmeticProductsByStoreId(int storeId);

}
