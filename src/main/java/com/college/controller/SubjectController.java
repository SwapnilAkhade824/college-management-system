package com.college.controller;

import java.util.List;

/**
 * ============================================
 * CLASS: SubjectController
 * ============================================
 *
 * PURPOSE:
 * This controller handles all business logic related to Subject.
 * It acts as a bridge between UI (Panels) and DAO layer.
 *
 * RESPONSIBILITIES:
 * - Validate user input
 * - Call DAO methods
 * - Process data before sending to UI
 *
 * USED BY:
 * - SubjectPanel (UI)
 *
 * DEPENDS ON:
 * - SubjectDAO
 * - Subject model
 *
 * ============================================
 */
public class SubjectController {

    /**
     * Create/Add Subject
     * @param data (Subject object)
     * @return boolean (true if success, false otherwise)
     */
    public boolean addSubject(Subject data) {
        return false;
    }

    /**
     * Update Subject
     * @param data (Subject object with updated values)
     * @return boolean
     */
    public boolean updateSubject(Subject data) {
        return false;
    }

    /**
     * Soft Delete Subject
     * @param id (primary key)
     * @return boolean
     */
    public boolean deleteSubject(int id) {
        return false;
    }

    /**
     * Get Subject by ID
     * @param id
     * @return Subject object
     */
    public Subject getSubjectById(int id) {
        return null;
    }

    /**
     * Get all Subject records
     * @return List<Subject>
     */
    public List<Subject> getAllSubjects() {
        return null;
    }
}
