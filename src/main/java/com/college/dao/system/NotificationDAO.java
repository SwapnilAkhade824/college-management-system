package com.college.dao.system;

import com.college.core.DBConnection;
import com.college.model.system.Notification;
import com.college.util.DemoData;

import java.sql.*;
import java.util.*;

public class NotificationDAO {

    public List<Notification> findByUserId(int userId) {
        if (DBConnection.isDemoMode()) return DemoData.getNotifications();
        List<Notification> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        if (conn == null) return DemoData.getNotifications();
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Notifications WHERE user_id=? ORDER BY created_at DESC")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list.isEmpty() ? DemoData.getNotifications() : list;
    }

    public boolean markAllRead(int userId) {
        if (DBConnection.isDemoMode()) return true;
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE Notifications SET is_read=TRUE WHERE user_id=?")) {
            ps.setInt(1, userId);
            return ps.executeUpdate() >= 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean insert(Notification n) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Notifications(user_id,message,type,is_read) VALUES(?,?,?,?)")) {
            ps.setInt(1,n.getUserId()); ps.setString(2,n.getMessage());
            ps.setString(3,n.getType()); ps.setBoolean(4,n.isRead());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean update(Notification n) { return false; }
    public boolean delete(int id) { return false; }
    public Notification findById(int id) { return null; }
    public List<Notification> findAll() { return new ArrayList<>(); }

    private Notification map(ResultSet rs) throws SQLException {
        Notification n = new Notification();
        n.setNotificationId(rs.getInt("notification_id")); n.setUserId(rs.getInt("user_id"));
        n.setMessage(rs.getString("message")); n.setType(rs.getString("type"));
        n.setRead(rs.getBoolean("is_read")); n.setCreatedAt(rs.getTimestamp("created_at"));
        return n;
    }
}
