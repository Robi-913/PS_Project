package com.Robert.project.presenter;

import com.Robert.project.model.*;
import com.Robert.project.model.repository.*;
import com.Robert.project.view.ICatalogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatalogPresenter {

    private final ProductRepository productRepo;
    private final StoreRepository storeRepo;
    private final InventoryRepository inventoryRepo;
    private final ICatalogView view;

    @Autowired
    public CatalogPresenter(ProductRepository productRepo, StoreRepository storeRepo,
                            InventoryRepository inventoryRepo, ICatalogView view) {
        this.productRepo = productRepo;
        this.storeRepo = storeRepo;
        this.inventoryRepo = inventoryRepo;
        this.view = view;
    }

    // Metode pentru gestionarea produselor
    public void addProduct(Product product) {
        productRepo.save(product);
        view.displayProductList(productRepo.findAll());
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
        view.displayProductList(productRepo.findAll());
    }

    public void deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
        view.displayProductList(productRepo.findAll());
    }

    public List<Product> displayAllProducts() {
        return productRepo.findAll();
    }

    // Metode pentru gestionarea magazinelor
    public void addStore(Store store) {
        storeRepo.save(store);
        view.displayStores(storeRepo.findAll());
    }

    public void updateStore(Store store) {
        storeRepo.save(store);
        view.displayStores(storeRepo.findAll());
    }

    public void deleteStore(Integer storeId) {
        storeRepo.deleteById(storeId);
        view.displayStores(storeRepo.findAll());
    }

    public List<Store> displayAllStores() {
        return storeRepo.findAll();
    }

    // Alte metode existente
    public List<Product> filterProductsByAvailability(Integer storeId, Boolean available) {
        List<Inventory> stock = inventoryRepo.findByStoreStoreIdAndAvailable(storeId, available);
        return stock.stream()
                .map(inventory -> productRepo.findById(inventory.getProductId()).orElse(null))
                .collect(Collectors.toList());
    }

    public List<Product> searchProductByName(String name) {
        return productRepo.findByNameContaining(name);
    }

    public void exportOutOfStockProducts(Integer storeId) {
        List<Inventory> outOfStock = inventoryRepo.findByStoreStoreIdAndQuantity(storeId, 0);
        List<Product> outOfStockProducts = outOfStock.stream()
                .map(inventory -> productRepo.findById(inventory.getProductId()).orElse(null))
                .collect(Collectors.toList());

        // Salvăm lista de produse epuizate într-un fișier CSV
        try (FileWriter writer = new FileWriter(new File("out_of_stock_products_store_" + storeId + ".csv"))) {
            writer.append("Product ID,Name,Manufacturer,Expiration Date\n");
            for (Product product : outOfStockProducts) {
                writer.append(product.getProductId() + "," + product.getName() + ","
                        + product.getManufacturer() + "," + product.getExpirationDate() + "\n");
            }
            writer.flush();
            view.displayOutOfStockProducts(outOfStockProducts);
        } catch (IOException e) {
            view.displayErrorMessage("Error exporting out-of-stock products: " + e.getMessage());
        }
    }

    public void exportOutOfStockProductsToDoc(Integer storeId) {
        List<Inventory> outOfStock = inventoryRepo.findByStoreStoreIdAndQuantity(storeId, 0);
        List<Product> outOfStockProducts = outOfStock.stream()
                .map(inventory -> productRepo.findById(inventory.getProductId()).orElse(null))
                .collect(Collectors.toList());

        // Aici ai putea folosi o librărie de generat fișiere DOC, precum Apache POI.
        try {
            // Logica de generare DOC
            view.displayOutOfStockProducts(outOfStockProducts);
        } catch (Exception e) {
            view.displayErrorMessage("Error exporting out-of-stock products to DOC: " + e.getMessage());
        }
    }
}
