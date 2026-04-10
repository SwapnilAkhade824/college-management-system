package com.college.core;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Manages a single MySQL connection.
 * Falls back to demo mode if DB is unavailable.
 */
public final class DBConnection {

    private DBConnection() {}

    private static Connection conn;
    private static boolean    demo = false;
    private static boolean    tried = false;

    public static boolean isDemoMode() { return demo; }

    /**
     * Returns an open Connection, or null (demo mode).
     */
    public static Connection getConnection() {
        if (tried && demo) return null;
        if (conn != null) {
            try {
                if (!conn.isClosed()) return conn;
            } catch (Exception ignored) {}
        }
        tried = true;
        try {
            Properties cfg = new Properties();
            InputStream is = DBConnection.class.getResourceAsStream("/config/application.properties");
            if (is == null) { demo = true; return null; }
            cfg.load(is);
            String url  = cfg.getProperty("db.url",      "jdbc:mysql://localhost:3306/college_db");
            String user = cfg.getProperty("db.username", "root");
            String pass = cfg.getProperty("db.password", "");
            conn = DriverManager.getConnection(url, user, pass);
            demo = false;
            return conn;
        } catch (Exception e) {
            System.out.println("[DBConnection] Demo mode — DB unavailable: " + e.getMessage());
            demo = true;
            return null;
        }
    }

    public static void close() {
        try { if (conn != null && !conn.isClosed()) conn.close(); }
        catch (Exception ignored) {}
    }
}
