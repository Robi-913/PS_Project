package com.Robert.project.controller;

import com.Robert.project.model.Product;
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
    public void displayAllProducts() {
        presenter.displayAllProducts();
    }

    @GetMapping("/stores")
    public void displayAllStores() {
        presenter.displayAllStores();
    }

    @GetMapping("/products/filter")
    public void filterProductsByAvailability(
            @RequestParam Integer storeId,
            @RequestParam Boolean available) {
        presenter.filterProductsByAvailability(storeId, available);
    }

    @GetMapping("/products/search")
    public void searchProductByName(@RequestParam String name) {
        presenter.searchProductByName(name);
    }

    @GetMapping("/export-csv/{storeId}")
    public void exportOutOfStockProducts(@PathVariable Integer storeId) {
        presenter.exportOutOfStockProducts(storeId);
    }
}