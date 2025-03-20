package org.example.presenter;

import org.example.presenter.dto.StoreProductDTO;
import org.example.presenter.dto.Mapeer;
import org.example.model.CosmeticProduct;
import org.example.model.Store;
import org.example.model.StoreProduct;
import org.example.model.repository.StoreProductRepository;
import org.example.model.repository.StoreProductRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class StoreProductPresenter {
    private final StoreProductView view;
    private final StoreProductRepository repository;

    public StoreProductPresenter(StoreProductView view) {
        this.view = view;
        this.repository = new StoreProductRepositoryImpl();
    }

    public void addStoreProduct() {
        String storeIdStr = view.getStoreIdInput();
        String productIdStr = view.getProductIdInput();
        String quantityStr = view.getQuantityInput();

        int storeId, productId, quantity;
        try {
            storeId = Integer.parseInt(storeIdStr);
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            view.showError("Invalid numeric input.");
            return;
        }

        StoreProduct sp = new StoreProduct();
        sp.setStore(new Store() {{
            setId(storeId);
        }});
        sp.setProduct(new CosmeticProduct() {{
            setId(productId);
        }});
        sp.setQuantity(quantity);

        boolean success = repository.addStoreProduct(sp);
        if (success) {
            view.showMessage("StoreProduct added successfully.");
            loadAllStoreProducts();
        } else {
            view.showError("Error adding StoreProduct.");
        }
    }

    public void updateStoreProduct() {
        StoreProductDTO selected = view.getSelectedStoreProduct();
        if (selected == null) {
            view.showError("Please select a record to update.");
            return;
        }

        String storeIdStr = view.getStoreIdInput();
        String productIdStr = view.getProductIdInput();
        String quantityStr = view.getQuantityInput();

        int storeId, productId, quantity;
        try {
            storeId = Integer.parseInt(storeIdStr);
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            view.showError("Invalid numeric input.");
            return;
        }

        StoreProduct sp = new StoreProduct();
        sp.setId(selected.getId());
        sp.setStore(new Store() {{
            setId(storeId);
        }});
        sp.setProduct(new CosmeticProduct() {{
            setId(productId);
        }});
        sp.setQuantity(quantity);

        boolean success = repository.updateStoreProduct(sp);
        if (success) {
            view.showMessage("StoreProduct updated successfully.");
            loadAllStoreProducts();
        } else {
            view.showError("Error updating StoreProduct.");
        }
    }

    public void deleteStoreProduct() {
        StoreProductDTO selected = view.getSelectedStoreProduct();
        if (selected == null) {
            view.showError("Please select a record to delete.");
            return;
        }
        boolean success = repository.deleteStoreProduct(selected.getId());
        if (success) {
            view.showMessage("StoreProduct deleted successfully.");
            loadAllStoreProducts();
        } else {
            view.showError("Error deleting StoreProduct.");
        }
    }

    public void loadAllStoreProducts() {
        List<StoreProduct> storeProducts = repository.getAllStoreProducts();
        List<StoreProductDTO> dtos = storeProducts.stream()
                .map(Mapeer::mapToDTO)
                .collect(Collectors.toList());
        view.displayStoreProducts(dtos);
    }
}
