package org.example.model;

public class StoreProduct {
    private int id;
    private Store store;
    private CosmeticProduct product;
    private int quantity;

    public StoreProduct() {
    }

    public StoreProduct(int id, Store store, CosmeticProduct product, int quantity) {
        this.id = id;
        this.store = store;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public CosmeticProduct getProduct() {
        return product;
    }

    public void setProduct(CosmeticProduct product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StoreProduct{" +
                "id=" + id +
                ", store=" + (store != null ? store.getName() : "N/A") +
                ", product=" + (product != null ? product.getName() : "N/A") +
                ", quantity=" + quantity +
                '}';
    }
}
