package com.college.core;

import java.sql.Connection;

/**
 * ============================================
 * CLASS: DBConnection
 * ============================================
 *
 * PURPOSE:
 * Manages database connection (MySQL).
 *
 * RESPONSIBILITIES:
 * - Establish DB connection
 * - Provide reusable connection instance
 *
 * USED BY:
 * - All DAO classes
 *
 * METHODS TO IMPLEMENT:
 *
 * 1. getConnection()
 *    @return Connection
 *
 * 2. closeConnection(Connection conn)
 *    @param conn
 *    @return void
 *
 * NOTES:
 * - Use Singleton pattern (single connection instance)
 * - Read DB config from application.properties
 *
 * ============================================
 */
public class DBConnection {

    public static Connection getConnection() {
        return null;
    }

    public static void closeConnection(Connection conn) {
    }
}
