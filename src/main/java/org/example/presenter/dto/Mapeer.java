package org.example.presenter.dto;

import org.example.model.CosmeticProduct;
import org.example.model.Store;
import org.example.model.StoreProduct;

public class Mapeer {

    public static CosmeticProductDTO mapToDTO(CosmeticProduct product) {
        if (product == null) {
            return null;
        }
        return new CosmeticProductDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getSize(),
                product.getCategory()
        );
    }

    public static CosmeticProduct mapToModel(CosmeticProductDTO dto) {
        if (dto == null) {
            return null;
        }
        CosmeticProduct product = new CosmeticProduct();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setSize(dto.getSize());
        product.setCategory(dto.getCategory());
        return product;
    }

    public static StoreProductDTO mapToDTO(StoreProduct sp) {
        if (sp == null) {
            return null;
        }
        int id = sp.getId();
        int storeId = (sp.getStore() != null) ? sp.getStore().getId() : 0;
        String storeName = (sp.getStore() != null) ? sp.getStore().getName() : "N/A";
        int productId = (sp.getProduct() != null) ? sp.getProduct().getId() : 0;
        String productName = (sp.getProduct() != null) ? sp.getProduct().getName() : "N/A";
        int quantity = sp.getQuantity();
        return new StoreProductDTO(id, storeId, storeName, productId, productName, quantity);
    }

    public static StoreProduct mapToModel(StoreProductDTO dto) {
        if (dto == null) {
            return null;
        }
        StoreProduct sp = new StoreProduct();
        sp.setId(dto.getId());

        Store store = new Store();
        store.setId(dto.getStoreId());
        store.setName(dto.getStoreName());
        sp.setStore(store);

        CosmeticProduct product = new CosmeticProduct();
        product.setId(dto.getProductId());
        product.setName(dto.getProductName());
        sp.setProduct(product);

        sp.setQuantity(dto.getQuantity());
        return sp;
    }

    public static StoreDTO mapToDTO(Store store) {
        if (store == null) return null;
        return new StoreDTO(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getPhone()
        );
    }

    public static Store mapToModel(StoreDTO dto) {
        if (dto == null) return null;
        Store store = new Store();
        store.setId(dto.getId());
        store.setName(dto.getName());
        store.setAddress(dto.getAddress());
        store.setPhone(dto.getPhone());
        return store;
    }
}
