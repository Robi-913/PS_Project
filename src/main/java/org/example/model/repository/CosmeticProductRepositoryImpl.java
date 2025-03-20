package org.example.model.repository;

import org.example.model.CosmeticProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CosmeticProductRepositoryImpl implements CosmeticProductRepository {

    private Connection connection;

    public CosmeticProductRepositoryImpl() {
        connection = DatabaseConnectionFactory.getConnectionWrapper().getConnection();
    }

    @Override
    public boolean addCosmeticProduct(CosmeticProduct product) {
        String sql = "INSERT INTO cosmetic_product (name, brand, price, size, category) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBrand());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getSize());
            stmt.setString(5, product.getCategory());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCosmeticProduct(CosmeticProduct product) {
        String sql = "UPDATE cosmetic_product SET name = ?, brand = ?, price = ?, size = ?, category = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBrand());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getSize());
            stmt.setString(5, product.getCategory());
            stmt.setInt(6, product.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCosmeticProduct(int id) {
        String sql = "DELETE FROM cosmetic_product WHERE id = ?";
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
    public List<CosmeticProduct> getAllCosmeticProducts() {
        List<CosmeticProduct> products = new ArrayList<>();
        String sql = "SELECT id, name, brand, price, size, category FROM cosmetic_product";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CosmeticProduct product = new CosmeticProduct();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setSize(rs.getString("size"));
                product.setCategory(rs.getString("category"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public CosmeticProduct getCosmeticProductById(int id) {
        String sql = "SELECT id, name, brand, price, size, category FROM cosmetic_product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CosmeticProduct product = new CosmeticProduct();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setBrand(rs.getString("brand"));
                    product.setPrice(rs.getDouble("price"));
                    product.setSize(rs.getString("size"));
                    product.setCategory(rs.getString("category"));
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CosmeticProduct> getCosmeticProductsByStoreId(int storeId) {
        List<CosmeticProduct> cosmetics = new ArrayList<>();
        String sql = "SELECT cp.id, cp.name, cp.brand, cp.price, cp.size, cp.category " +
                "FROM cosmetic_product cp " +
                "JOIN store_product sp ON cp.id = sp.product_id " +
                "WHERE sp.store_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, storeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CosmeticProduct product = new CosmeticProduct();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setBrand(rs.getString("brand"));
                    product.setPrice(rs.getDouble("price"));
                    product.setSize(rs.getString("size"));
                    product.setCategory(rs.getString("category"));
                    cosmetics.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cosmetics;
    }

}
