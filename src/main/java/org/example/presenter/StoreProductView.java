package org.example.presenter;

import org.example.presenter.dto.StoreProductDTO;
import java.util.List;

public interface StoreProductView {
    void displayStoreProducts(List<StoreProductDTO> storeProducts);
    void displayStoreProduct(StoreProductDTO storeProduct);
    void showMessage(String message);
    void showError(String errorMessage);

    String getStoreIdInput();
    String getProductIdInput();
    String getQuantityInput();

    StoreProductDTO getSelectedStoreProduct();
}
