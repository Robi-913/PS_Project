package org.example.presenter;

import org.example.presenter.dto.StoreDTO;
import org.example.presenter.dto.CosmeticProductDTO;
import java.util.List;

public interface StoreView {
    void displayStores(List<StoreDTO> stores);
    void displayStore(StoreDTO store);
    void showMessage(String message);
    void showError(String errorMessage);

    String getStoreName();
    String getStoreAddress();
    String getStorePhone();
    StoreDTO getSelectedStore();

    void displayStoreCosmetics(List<CosmeticProductDTO> cosmetics, String storeName);
}
