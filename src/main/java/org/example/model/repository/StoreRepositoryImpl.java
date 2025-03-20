package org.example.model.repository;

import org.example.model.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreRepositoryImpl implements StoreRepository {

    private Connection connection;

    public StoreRepositoryImpl() {
        connection = DatabaseConnectionFactory.getConnectionWrapper().getConnection();
    }

    @Override
    public boolean addStore(Store store) {
        String sql = "INSERT INTO store (name, address, phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, store.getName());
            stmt.setString(2, store.getAddress());
            stmt.setString(3, store.getPhone());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Store getStoreById(int id) {
        String sql = "SELECT * FROM store WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Store store = new Store();
                    store.setId(rs.getInt("id"));
                    store.setName(rs.getString("name"));
                    store.setAddress(rs.getString("address"));
                    store.setPhone(rs.getString("phone"));
                    return store;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Store> getAllStores() {
        List<Store> stores = new ArrayList<>();
        String sql = "SELECT * FROM store";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Store store = new Store();
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                stores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }

    @Override
    public boolean updateStore(Store store) {
        String sql = "UPDATE store SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, store.getName());
            stmt.setString(2, store.getAddress());
            stmt.setString(3, store.getPhone());
            stmt.setInt(4, store.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStore(int id) {
        String sql = "DELETE FROM store WHERE id = ?";
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
    public List<Store> getAllStoresWithCosmetics() {
        List<Store> stores = new ArrayList<>();
        String sql = "SELECT DISTINCT s.* " +
                "FROM store s " +
                "JOIN store_product sp ON s.id = sp.store_id " +
                "JOIN cosmetic_product cp ON sp.product_id = cp.id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Store store = new Store();
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                stores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
