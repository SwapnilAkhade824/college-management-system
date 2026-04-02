package com.college.controller;

import java.util.List;

/**
 * ============================================
 * CLASS: CourseController
 * ============================================
 *
 * PURPOSE:
 * This controller handles all business logic related to Course.
 * It acts as a bridge between UI (Panels) and DAO layer.
 *
 * RESPONSIBILITIES:
 * - Validate user input
 * - Call DAO methods
 * - Process data before sending to UI
 *
 * USED BY:
 * - CoursePanel (UI)
 *
 * DEPENDS ON:
 * - CourseDAO
 * - Course model
 *
 * ============================================
 */
public class CourseController {

    /**
     * Create/Add Course
     * @param data (Course object)
     * @return boolean (true if success, false otherwise)
     */
    public boolean addCourse(Course data) {
        return false;
    }

    /**
     * Update Course
     * @param data (Course object with updated values)
     * @return boolean
     */
    public boolean updateCourse(Course data) {
        return false;
    }

    /**
     * Soft Delete Course
     * @param id (primary key)
     * @return boolean
     */
    public boolean deleteCourse(int id) {
        return false;
    }

    /**
     * Get Course by ID
     * @param id
     * @return Course object
     */
    public Course getCourseById(int id) {
        return null;
    }

    /**
     * Get all Course records
     * @return List<Course>
     */
    public List<Course> getAllCourses() {
        return null;
    }
}
