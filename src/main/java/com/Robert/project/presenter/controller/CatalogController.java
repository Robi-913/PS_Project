package com.Robert.project.presenter.controller;

import com.Robert.project.model.Product;
import com.Robert.project.model.Store;
import com.Robert.project.presenter.CatalogPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final CatalogPresenter presenter;

    @Autowired
    public CatalogController(CatalogPresenter presenter) {
        this.presenter = presenter;
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        presenter.addProduct(product);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product) {
        presenter.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        presenter.deleteProduct(productId);
    }

    @GetMapping("/products")
    public List<Product> displayAllProducts() {
        return presenter.displayAllProducts();
    }

    // Endpointuri pentru magazine
    @PostMapping("/stores")
    public void addStore(@RequestBody Store store) {
        presenter.addStore(store);
    }

    @PutMapping("/stores/{storeId}")
    public void updateStore(@PathVariable Integer storeId, @RequestBody Store store) {
        store.setStoreId(storeId);  // Setează ID-ul magazinului pentru actualizare
        presenter.updateStore(store);
    }

    @DeleteMapping("/stores/{storeId}")
    public void deleteStore(@PathVariable Integer storeId) {
        presenter.deleteStore(storeId);
    }

    @GetMapping("/stores")
    public List<Store> displayAllStores() {
        return presenter.displayAllStores();
    }

    @GetMapping("/products/filter")
    public List<Product> filterProductsByAvailability(
            @RequestParam Integer storeId,
            @RequestParam Boolean available) {
        return presenter.filterProductsByAvailability(storeId, available);
    }

    @GetMapping("/products/search")
    public List<Product> searchProductByName(@RequestParam String name) {
        return presenter.searchProductByName(name);
    }

    @GetMapping("/export-csv/{storeId}")
    public void exportOutOfStockProducts(@PathVariable Integer storeId) {
        presenter.exportOutOfStockProducts(storeId);
    }

    @GetMapping("/export-doc/{storeId}")
    public void exportOutOfStockProductsToDoc(@PathVariable Integer storeId) {
        presenter.exportOutOfStockProductsToDoc(storeId);
    }
}
