package org.example.model.repository;

import org.example.model.StoreProduct;
import org.example.model.Store;
import org.example.model.CosmeticProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreProductRepositoryImpl implements StoreProductRepository {

    private Connection connection;

    public StoreProductRepositoryImpl() {
        connection = DatabaseConnectionFactory.getConnectionWrapper().getConnection();
    }

    @Override
    public boolean addStoreProduct(StoreProduct storeProduct) {
        String sql = "INSERT INTO store_product (store_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, storeProduct.getStore().getId());
            stmt.setInt(2, storeProduct.getProduct().getId());
            stmt.setInt(3, storeProduct.getQuantity());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStoreProduct(StoreProduct storeProduct) {
        String sql = "UPDATE store_product SET store_id = ?, product_id = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, storeProduct.getStore().getId());
            stmt.setInt(2, storeProduct.getProduct().getId());
            stmt.setInt(3, storeProduct.getQuantity());
            stmt.setInt(4, storeProduct.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStoreProduct(int id) {
        String sql = "DELETE FROM store_product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<StoreProduct> getAllStoreProducts() {
        List<StoreProduct> list = new ArrayList<>();
        String sql = "SELECT sp.id AS id, sp.quantity, " +
                "s.id AS storeId, s.name AS storeName, s.address, s.phone, " +
                "p.id AS productId, p.name AS productName, p.brand, p.price, p.size, p.category " +
                "FROM store_product sp " +
                "JOIN store s ON sp.store_id = s.id " +
                "JOIN cosmetic_product p ON sp.product_id = p.id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StoreProduct sp = new StoreProduct();
                sp.setId(rs.getInt("id"));
                sp.setQuantity(rs.getInt("quantity"));

                Store store = new Store();
                store.setId(rs.getInt("storeId"));
                store.setName(rs.getString("storeName"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                sp.setStore(store);

                CosmeticProduct product = new CosmeticProduct();
                product.setId(rs.getInt("productId"));
                product.setName(rs.getString("productName"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setSize(rs.getString("size"));
                product.setCategory(rs.getString("category"));
                sp.setProduct(product);

                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public StoreProduct getStoreProductById(int id) {
        String sql = "SELECT sp.id AS id, sp.quantity, " +
                "s.id AS storeId, s.name AS storeName, s.address, s.phone, " +
                "p.id AS productId, p.name AS productName, p.brand, p.price, p.size, p.category " +
                "FROM store_product sp " +
                "JOIN store s ON sp.store_id = s.id " +
                "JOIN cosmetic_product p ON sp.product_id = p.id " +
                "WHERE sp.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    StoreProduct sp = new StoreProduct();
                    sp.setId(rs.getInt("id"));
                    sp.setQuantity(rs.getInt("quantity"));

                    Store store = new Store();
                    store.setId(rs.getInt("storeId"));
                    store.setName(rs.getString("storeName"));
                    store.setAddress(rs.getString("address"));
                    store.setPhone(rs.getString("phone"));
                    sp.setStore(store);

                    CosmeticProduct product = new CosmeticProduct();
                    product.setId(rs.getInt("productId"));
                    product.setName(rs.getString("productName"));
                    product.setBrand(rs.getString("brand"));
                    product.setPrice(rs.getDouble("price"));
                    product.setSize(rs.getString("size"));
                    product.setCategory(rs.getString("category"));
                    sp.setProduct(product);
                    return sp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StoreProduct> getAllStoreProductsByStoreId(int storeId) {
        List<StoreProduct> list = new ArrayList<>();
        String sql = "SELECT sp.id AS id, sp.quantity, " +
                "s.id AS storeId, s.name AS storeName, s.address, s.phone, " +
                "p.id AS productId, p.name AS productName, p.brand, p.price, p.size, p.category " +
                "FROM store_product sp " +
                "JOIN store s ON sp.store_id = s.id " +
                "JOIN cosmetic_product p ON sp.product_id = p.id " +
                "WHERE sp.store_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, storeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StoreProduct sp = new StoreProduct();
                    sp.setId(rs.getInt("id"));
                    sp.setQuantity(rs.getInt("quantity"));

                    Store store = new Store();
                    store.setId(rs.getInt("storeId"));
                    store.setName(rs.getString("storeName"));
                    store.setAddress(rs.getString("address"));
                    store.setPhone(rs.getString("phone"));
                    sp.setStore(store);

                    CosmeticProduct product = new CosmeticProduct();
                    product.setId(rs.getInt("productId"));
                    product.setName(rs.getString("productName"));
                    product.setBrand(rs.getString("brand"));
                    product.setPrice(rs.getDouble("price"));
                    product.setSize(rs.getString("size"));
                    product.setCategory(rs.getString("category"));
                    sp.setProduct(product);

                    list.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
