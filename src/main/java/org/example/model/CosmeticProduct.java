package org.example.model;

public class CosmeticProduct {
    private int id;
    private String name;
    private String brand;
    private double price;
    private String size;
    private String category;

    public CosmeticProduct() {
    }

    public CosmeticProduct(int id, String name, String brand, double price, String size, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CosmeticProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", size='" + size + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
