package com.college.dao.infrastructure;

import java.util.List;
import com.college.model.infrastructure.Classroom;

/**
 * ============================================
 * CLASS: ClassroomDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for Classroom table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to Classroom
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
public class ClassroomDAO {

    /**
     * Insert new record
     * @param data (Classroom object)
     * @return boolean (true if success)
     */
    public boolean insert(Classroom data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (Classroom object)
     * @return boolean
     */
    public boolean update(Classroom data) {
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
     * @return Classroom
     */
    public Classroom findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<Classroom>
     */
    public List<Classroom> findAll() {
        return null;
    }
}
