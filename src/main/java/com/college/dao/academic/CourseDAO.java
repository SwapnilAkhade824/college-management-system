package com.college.dao.academic;

import java.util.List;
import com.college.model.academic.Course;

/**
 * ============================================
 * CLASS: CourseDAO
 * ============================================
 *
 * PURPOSE:
 * Handles database operations for Course table.
 *
 * RESPONSIBILITIES:
 * - Execute SQL queries
 * - Map ResultSet to Course
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
public class CourseDAO {

    /**
     * Insert new record
     * @param data (Course object)
     * @return boolean (true if success)
     */
    public boolean insert(Course data) {
        return false;
    }

    /**
     * Update existing record
     * @param data (Course object)
     * @return boolean
     */
    public boolean update(Course data) {
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
     * @return Course
     */
    public Course findById(int id) {
        return null;
    }

    /**
     * Fetch all records
     * @return List<Course>
     */
    public List<Course> findAll() {
        return null;
    }
}
