package com.college.controller;

import java.util.List;

/**
 * ============================================
 * CLASS: ExamController
 * ============================================
 *
 * PURPOSE:
 * This controller handles all business logic related to Exam.
 * It acts as a bridge between UI (Panels) and DAO layer.
 *
 * RESPONSIBILITIES:
 * - Validate user input
 * - Call DAO methods
 * - Process data before sending to UI
 *
 * USED BY:
 * - ExamPanel (UI)
 *
 * DEPENDS ON:
 * - ExamDAO
 * - Exam model
 *
 * ============================================
 */
public class ExamController {

    /**
     * Create/Add Exam
     * @param data (Exam object)
     * @return boolean (true if success, false otherwise)
     */
    public boolean addExam(Exam data) {
        return false;
    }

    /**
     * Update Exam
     * @param data (Exam object with updated values)
     * @return boolean
     */
    public boolean updateExam(Exam data) {
        return false;
    }

    /**
     * Soft Delete Exam
     * @param id (primary key)
     * @return boolean
     */
    public boolean deleteExam(int id) {
        return false;
    }

    /**
     * Get Exam by ID
     * @param id
     * @return Exam object
     */
    public Exam getExamById(int id) {
        return null;
    }

    /**
     * Get all Exam records
     * @return List<Exam>
     */
    public List<Exam> getAllExams() {
        return null;
    }
}
