package org.example.presenter;

import org.example.presenter.dto.CosmeticProductDTO;
import org.example.model.CosmeticProduct;
import org.example.model.repository.CosmeticProductRepository;
import org.example.model.repository.CosmeticProductRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CosmeticProductPresenter {
    private CosmeticProductView view;
    private CosmeticProductRepository repository;

    public CosmeticProductPresenter(CosmeticProductView view) {
        this.view = view;
        this.repository = new CosmeticProductRepositoryImpl();
    }

    private CosmeticProductDTO convertToDTO(CosmeticProduct product) {
        return new CosmeticProductDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getSize(),
                product.getCategory()
        );
    }

    public void loadAllCosmeticProducts() {
        List<CosmeticProduct> products = repository.getAllCosmeticProducts();
        List<CosmeticProductDTO> dtos = products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        view.displayCosmeticProducts(dtos);
    }

    public void addCosmeticProduct() {
        String name = view.getProductName();
        String brand = view.getProductBrand();
        String priceText = view.getProductPrice();
        String size = view.getProductSize();
        String category = view.getProductCategory();

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            view.showError("Invalid numeric input for Price.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            view.showError("Product name is required.");
            return;
        }

        CosmeticProduct product = new CosmeticProduct();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setSize(size);
        product.setCategory(category);

        boolean success = repository.addCosmeticProduct(product);
        if (success) {
            view.showMessage("Product added successfully.");
            loadAllCosmeticProducts();
        } else {
            view.showError("Error adding product.");
        }
    }

    public void updateCosmeticProduct() {
        CosmeticProductDTO selected = view.getSelectedProduct();
        if (selected == null) {
            view.showError("Please select a product to update.");
            return;
        }

        String name = view.getProductName();
        String brand = view.getProductBrand();
        String priceText = view.getProductPrice();
        String size = view.getProductSize();
        String category = view.getProductCategory();

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            view.showError("Invalid numeric input for Price.");
            return;
        }

        CosmeticProduct product = new CosmeticProduct();
        product.setId(selected.getId());
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setSize(size);
        product.setCategory(category);

        boolean success = repository.updateCosmeticProduct(product);
        if (success) {
            view.showMessage("Product updated successfully.");
            loadAllCosmeticProducts();
        } else {
            view.showError("Error updating product.");
        }
    }

    public void deleteCosmeticProduct() {
        CosmeticProductDTO selected = view.getSelectedProduct();
        if (selected == null) {
            view.showError("Please select a product to delete.");
            return;
        }

        boolean success = repository.deleteCosmeticProduct(selected.getId());
        if (success) {
            view.showMessage("Product deleted successfully.");
            loadAllCosmeticProducts();
        } else {
            view.showError("Error deleting product.");
        }
    }
}
