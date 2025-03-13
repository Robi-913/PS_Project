package com.Robert.project.view;

import com.Robert.project.model.Product;
import com.Robert.project.model.Inventory;
import com.Robert.project.model.Store;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogWebView implements ICatalogView {

    @Override
    public void displayProductList(List<Product> products) {
        System.out.println("Product List:");
        products.forEach(product -> System.out.println(product.getName() + " - " + product.getManufacturer()));
    }

    @Override
    public void displayProductDetails(Product product) {
        System.out.println("Product Details:");
        System.out.println("ID: " + product.getProductId());
        System.out.println("Name: " + product.getName());
        System.out.println("Manufacturer: " + product.getManufacturer());
        System.out.println("Expiration Date: " + product.getExpirationDate());
    }

    @Override
    public void displayStock(List<Inventory> stock) {
        System.out.println("Stock Information:");
        stock.forEach(inventory -> System.out.println(
                "Store ID: " + inventory.getStoreId() +
                        ", Product ID: " + inventory.getProductId() +
                        ", Quantity: " + inventory.getQuantity() +
                        ", Available: " + inventory.getAvailable()
        ));
    }

    @Override
    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    @Override
    public void displayStores(List<Store> stores) {
        System.out.println("Store List:");
        stores.forEach(store -> System.out.println(store.getName() + " - " + store.getAddress()));
    }

    @Override
    public void displayOutOfStockProducts(List<Product> products) {
        System.out.println("Out-of-Stock Products:");
        products.forEach(product -> System.out.println(product.getName() + " - " + product.getManufacturer()));
    }
}
