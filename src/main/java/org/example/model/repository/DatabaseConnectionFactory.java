package org.example.model.repository;

public class DatabaseConnectionFactory {
    private static final String SCHEMA = "cosmetics";

    public static JDBCConnectionWrapper getConnectionWrapper() {
        return new JDBCConnectionWrapper(SCHEMA);
    }
}