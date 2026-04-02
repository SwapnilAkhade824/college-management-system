package com.college.dao.exam;

import java.util.List;
import com.college.model.exam.Exam;

/**
 * ============================================
 * CLASS: ExamDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for Exam table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to Exam
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
public class ExamDAO {

    /**
     * Insert new record
     * @param data (Exam object)
     * @return boolean (true if success)
     */
    public boolean insert(Exam data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (Exam object)
     * @return boolean
     */
    public boolean update(Exam data) {
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
     * @return Exam
     */
    public Exam findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<Exam>
     */
    public List<Exam> findAll() {
        return null;
    }
}
