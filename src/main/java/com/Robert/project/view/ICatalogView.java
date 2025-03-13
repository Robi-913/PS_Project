package com.Robert.project.view;

import com.Robert.project.model.Product;
import com.Robert.project.model.Inventory;
import com.Robert.project.model.Store;

import java.util.List;

public interface ICatalogView {

    void displayProductList(List<Product> products); // Display list of products
    void displayProductDetails(Product product); // Display details of a single product
    void displayStock(List<Inventory> stock); // Display stock information
    void displayErrorMessage(String message); // Display error messages
    void displayStores(List<Store> stores); // Display list of stores
    void displayOutOfStockProducts(List<Product> products); // Display out-of-stock products
}