package com.college.dao.academic;

import java.util.List;
import com.college.model.academic.StudentSubject;

/**
 * ============================================
 * CLASS: StudentSubjectDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for StudentSubject table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to StudentSubject
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
public class StudentSubjectDAO {

    /**
     * Insert new record
     * @param data (StudentSubject object)
     * @return boolean (true if success)
     */
    public boolean insert(StudentSubject data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (StudentSubject object)
     * @return boolean
     */
    public boolean update(StudentSubject data) {
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
     * @return StudentSubject
     */
    public StudentSubject findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<StudentSubject>
     */
    public List<StudentSubject> findAll() {
        return null;
    }
}
