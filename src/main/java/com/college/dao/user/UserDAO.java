package com.college.dao.user;

import java.util.List;
import com.college.model.user.User;

/**
 * ============================================
 * CLASS: UserDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for User table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to User
 *
 * USED BY:
 * - Controller layer
 *
 * DEPENDS ON:
 * - DBConnection
 *
 * RULES:
 * - No business logic
 * - Only database interaction
 *
 * METHODS TO IMPLEMENT:
 *
 * ============================================
 */
public class UserDAO {

    /**
     * Insert new record
     * @param data (User object)
     * @return boolean (true if success)
     */
    public boolean insert(User data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (User object)
     * @return boolean
     */
    public boolean update(User data) {
        return false;
    }

    /**
     * Soft delete record (if applicable)
     * @param id (primary key)
     * @return boolean
     */
    public boolean delete(int id) {
        return false;
    }

    /**
     * Fetch record by ID
     * @param id
     * @return User
     */
    public User findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<User>
     */
    public List<User> findAll() {
        return null;
    }
}
