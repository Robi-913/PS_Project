package org.example.model.repository;

import org.example.model.Store;

import java.util.List;

public interface StoreRepository {
    boolean addStore(Store store);

    Store getStoreById(int id);

    List<Store> getAllStores();

    boolean updateStore(Store store);

    boolean deleteStore(int id);

    List<Store> getAllStoresWithCosmetics();
}

