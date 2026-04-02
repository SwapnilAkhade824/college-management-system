package com.college.dao.academic;

import java.util.List;
import com.college.model.academic.Subject;

/**
 * ============================================
 * CLASS: SubjectDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for Subject table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to Subject
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
public class SubjectDAO {

    /**
     * Insert new record
     * @param data (Subject object)
     * @return boolean (true if success)
     */
    public boolean insert(Subject data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (Subject object)
     * @return boolean
     */
    public boolean update(Subject data) {
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
     * @return Subject
     */
    public Subject findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<Subject>
     */
    public List<Subject> findAll() {
        return null;
    }
}
