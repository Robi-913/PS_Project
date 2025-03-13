package com.Robert.project.model.repository;

import com.Robert.project.model.Inventory;
import com.Robert.project.model.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {

    List<Inventory> findByStoreStoreId(Integer storeId);

    // Find inventory by store ID and availability
    List<Inventory> findByStoreStoreIdAndAvailable(Integer storeId, Boolean available);

    // Find inventory by store ID and quantity
    List<Inventory> findByStoreStoreIdAndQuantity(Integer storeId, Integer quantity);
}