package com.college.dao.user;

import com.college.core.DBConnection;
import com.college.model.user.User;
import com.college.util.DemoData;

import java.sql.*;
import java.util.*;

public class UserDAO {

    public User findByUsername(String username) {
        if (DBConnection.isDemoMode()) {
            User d = DemoData.getUser();
            return d.getUsername().equals(username) ? d : null;
        }
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Users WHERE username=? AND status='ACTIVE'")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean insert(User u) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Users(username,password,role,status) VALUES(?,?,?,?)")) {
            ps.setString(1, u.getUsername()); ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());     ps.setString(4, "INACTIVE");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean update(User u) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE Users SET password=?,status=? WHERE user_id=?")) {
            ps.setString(1, u.getPassword()); ps.setString(2, u.getStatus());
            ps.setInt(3, u.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean delete(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE Users SET status='INACTIVE' WHERE user_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public User findById(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE user_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        if (conn == null) return list;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Users")) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id")); u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password")); u.setRole(rs.getString("role"));
        u.setStatus(rs.getString("status"));
        return u;
    }
}
