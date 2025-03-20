package org.example.presenter;

import org.example.presenter.dto.CosmeticProductDTO;
import java.util.List;

public interface CosmeticProductView {
    void displayCosmeticProducts(List<CosmeticProductDTO> products);
    void displayCosmeticProduct(CosmeticProductDTO product);
    void showMessage(String message);
    void showError(String errorMessage);

    String getProductName();
    String getProductBrand();
    String getProductPrice();
    String getProductSize();
    String getProductCategory();

    CosmeticProductDTO getSelectedProduct();
}
