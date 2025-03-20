package org.example.presenter;

import org.example.presenter.dto.StoreProductDTO;
import org.example.presenter.dto.CosmeticProductDTO;
import org.example.presenter.dto.Mapeer;
import org.example.model.StoreProduct;
import org.example.model.repository.CosmeticProductRepository;
import org.example.model.repository.CosmeticProductRepositoryImpl;
import org.example.model.repository.StoreProductRepository;
import org.example.model.repository.StoreProductRepositoryImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilterPresenter {

    private final ProductFilterView view;
    private final StoreProductRepository storeProductRepository;
    private final CosmeticProductRepository cosmeticProductRepository;

    public ProductFilterPresenter(ProductFilterView view) {
        this.view = view;
        this.storeProductRepository = new StoreProductRepositoryImpl();
        this.cosmeticProductRepository = new CosmeticProductRepositoryImpl();
    }

    public void filterProducts() {
        String storeIdStr = view.getStoreIdForFilter();
        int storeId;
        try {
            storeId = Integer.parseInt(storeIdStr);
        } catch (NumberFormatException e) {
            view.showError("Invalid Store ID for filtering");
            return;
        }
        Boolean onlyAvailable = view.isOnlyAvailableSelected();
        String brand = view.getBrand();

        List<StoreProduct> all = storeProductRepository.getAllStoreProductsByStoreId(storeId);

        List<StoreProduct> filtered = all.stream()
                .filter(sp -> {
                    if (onlyAvailable != null) {
                        if (onlyAvailable) {
                            return sp.getQuantity() > 0;
                        } else {
                            return sp.getQuantity() == 0;
                        }
                    }
                    return true;
                })
                .filter(sp -> {
                    if (brand != null && !brand.isEmpty()) {
                        if (sp.getProduct() == null) return false;
                        return sp.getProduct().getBrand().equalsIgnoreCase(brand);
                    }
                    return true;
                })
                .collect(Collectors.toList());

        List<StoreProductDTO> dtos = filtered.stream()
                .map(Mapeer::mapToDTO)
                .collect(Collectors.toList());

        view.displayFilteredProducts(dtos);
    }

    public void searchCosmeticsByName() {
        String name = view.getSearchName();
        if (name == null || name.trim().isEmpty()) {
            view.showError("Please enter a search term.");
            return;
        }
        var all = cosmeticProductRepository.getAllCosmeticProducts();
        String lower = name.toLowerCase();

        var found = all.stream()
                .filter(cp -> cp.getName().toLowerCase().contains(lower))
                .collect(Collectors.toList());

        var dtos = found.stream()
                .map(Mapeer::mapToDTO)
                .collect(Collectors.toList());

        view.displaySearchedCosmetics(dtos);
    }

    public void exportOutOfStockToCSV() {
        String storeIdStr = view.getExportStoreId();
        int storeId;
        try {
            storeId = Integer.parseInt(storeIdStr);
        } catch (NumberFormatException e) {
            view.showError("Invalid Store ID for export CSV");
            return;
        }
        String filePath = view.getExportFilePath();
        if (!filePath.toLowerCase().endsWith(".csv")) {
            filePath += ".csv";
        }
        var all = storeProductRepository.getAllStoreProductsByStoreId(storeId);
        var outOfStock = all.stream()
                .filter(sp -> sp.getQuantity() == 0)
                .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("ID,StoreName,ProductName,Quantity");
            for (StoreProduct sp : outOfStock) {
                String storeName = (sp.getStore() != null) ? sp.getStore().getName() : "N/A";
                String productName = (sp.getProduct() != null) ? sp.getProduct().getName() : "N/A";
                writer.println(sp.getId() + "," + storeName + "," + productName + "," + sp.getQuantity());
            }
            view.showMessage("CSV exported to " + filePath);
        } catch (FileNotFoundException e) {
            view.showError("Error writing CSV file: " + e.getMessage());
        }
    }

    public void exportOutOfStockToDoc() {
        String storeIdStr = view.getExportStoreId();
        int storeId;
        try {
            storeId = Integer.parseInt(storeIdStr);
        } catch (NumberFormatException e) {
            view.showError("Invalid Store ID for export DOC");
            return;
        }
        String filePath = view.getExportFilePath();
        if (!filePath.toLowerCase().endsWith(".doc")) {
            filePath += ".doc";
        }
        var all = storeProductRepository.getAllStoreProductsByStoreId(storeId);
        var outOfStock = all.stream()
                .filter(sp -> sp.getQuantity() == 0)
                .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("Out of stock products for storeId = " + storeId);
            writer.println("-------------------------------------");
            for (StoreProduct sp : outOfStock) {
                String storeName = (sp.getStore() != null) ? sp.getStore().getName() : "N/A";
                String productName = (sp.getProduct() != null) ? sp.getProduct().getName() : "N/A";
                writer.println("ID: " + sp.getId());
                writer.println("Store: " + storeName);
                writer.println("Product: " + productName);
                writer.println("Quantity: " + sp.getQuantity());
                writer.println();
            }
            view.showMessage("DOC exported to " + filePath);
        } catch (FileNotFoundException e) {
            view.showError("Error writing DOC file: " + e.getMessage());
        }
    }
}
