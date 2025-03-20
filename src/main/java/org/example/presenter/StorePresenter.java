package org.example.presenter;

import org.example.presenter.dto.StoreDTO;
import org.example.presenter.dto.CosmeticProductDTO;
import org.example.model.Store;
import org.example.model.repository.StoreRepository;
import org.example.model.repository.StoreRepositoryImpl;
import org.example.model.repository.CosmeticProductRepository;
import org.example.model.repository.CosmeticProductRepositoryImpl;
import org.example.model.CosmeticProduct;

import java.util.List;
import java.util.stream.Collectors;

public class StorePresenter {
    private final StoreView view;
    private final StoreRepository repository;
    private final CosmeticProductRepository cosmeticRepository;

    public StorePresenter(StoreView view) {
        this.view = view;
        this.repository = new StoreRepositoryImpl();
        this.cosmeticRepository = new CosmeticProductRepositoryImpl();
    }

    public void loadAllStores() {
        List<Store> stores = repository.getAllStores();
        List<StoreDTO> dtos = stores.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        view.displayStores(dtos);
    }

    private StoreDTO mapToDTO(Store store) {
        return new StoreDTO(store.getId(), store.getName(), store.getAddress(), store.getPhone());
    }

    public void addStore() {
        String name = view.getStoreName();
        String address = view.getStoreAddress();
        String phone = view.getStorePhone();

        if (name == null || name.trim().isEmpty()) {
            view.showError("Store name is required.");
            return;
        }

        Store store = new Store();
        store.setName(name);
        store.setAddress(address);
        store.setPhone(phone);

        boolean success = repository.addStore(store);
        if (success) {
            view.showMessage("Store added successfully.");
            loadAllStores();
        } else {
            view.showError("Error adding store.");
        }
    }

    public void updateStore() {
        StoreDTO selected = view.getSelectedStore();
        if (selected == null) {
            view.showError("Please select a store to update.");
            return;
        }

        String name = view.getStoreName();
        String address = view.getStoreAddress();
        String phone = view.getStorePhone();

        if (name == null || name.trim().isEmpty()) {
            view.showError("Store name is required.");
            return;
        }

        Store store = new Store();
        store.setId(selected.getId());
        store.setName(name);
        store.setAddress(address);
        store.setPhone(phone);

        boolean success = repository.updateStore(store);
        if (success) {
            view.showMessage("Store updated successfully.");
            loadAllStores();
        } else {
            view.showError("Error updating store.");
        }
    }

    public void deleteStore() {
        StoreDTO selected = view.getSelectedStore();
        if (selected == null) {
            view.showError("Please select a store to delete.");
            return;
        }

        boolean success = repository.deleteStore(selected.getId());
        if (success) {
            view.showMessage("Store deleted successfully.");
            loadAllStores();
        } else {
            view.showError("Error deleting store.");
        }
    }

    // Metoda pentru afișarea cosmeticelor pentru magazinul selectat
    public void loadStoresWithCosmetics() {
        StoreDTO selected = view.getSelectedStore();
        if (selected == null) {
            view.showError("Please select a store to display its cosmetics.");
            return;
        }
        int storeId = selected.getId();
        String storeName = selected.getName();

        // Obținem lista de cosmetice pentru magazinul respectiv
        List<CosmeticProduct> cosmetics = cosmeticRepository.getCosmeticProductsByStoreId(storeId);
        List<CosmeticProductDTO> cosmeticDTOs = cosmetics.stream()
                .map(cp -> new CosmeticProductDTO(cp.getId(), cp.getName(), cp.getBrand(), cp.getPrice(), cp.getSize(), cp.getCategory()))
                .collect(Collectors.toList());

        // Trimitem datele către view pentru afișare
        view.displayStoreCosmetics(cosmeticDTOs, storeName);
    }
}
