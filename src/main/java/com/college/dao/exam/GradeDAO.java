package com.college.dao.exam;

import java.util.List;
import com.college.model.exam.Grade;

/**
 * ============================================
 * CLASS: GradeDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for Grade table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to Grade
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
public class GradeDAO {

    /**
     * Insert new record
     * @param data (Grade object)
     * @return boolean (true if success)
     */
    public boolean insert(Grade data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (Grade object)
     * @return boolean
     */
    public boolean update(Grade data) {
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
     * @return Grade
     */
    public Grade findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<Grade>
     */
    public List<Grade> findAll() {
        return null;
    }
}
