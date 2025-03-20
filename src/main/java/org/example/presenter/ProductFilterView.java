package org.example.presenter;

import org.example.presenter.dto.CosmeticProductDTO;
import org.example.presenter.dto.StoreProductDTO;
import java.util.List;

public interface ProductFilterView {
    void displayFilteredProducts(List<StoreProductDTO> filtered);
    void displaySearchedCosmetics(List<CosmeticProductDTO> found);
    void showMessage(String message);
    void showError(String errorMessage);

    String getStoreIdForFilter();
    Boolean isOnlyAvailableSelected();
    String getBrand();

    String getSearchName();

    String getExportStoreId();
    String getExportFilePath();
}
