package com.college.dao.academic;

import java.util.List;
import com.college.model.academic.Student;

/**
 * ============================================
 * CLASS: StudentDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for Student table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to Student
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
public class StudentDAO {

    /**
     * Insert new record
     * @param data (Student object)
     * @return boolean (true if success)
     */
    public boolean insert(Student data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (Student object)
     * @return boolean
     */
    public boolean update(Student data) {
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
     * @return Student
     */
    public Student findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<Student>
     */
    public List<Student> findAll() {
        return null;
    }
}
