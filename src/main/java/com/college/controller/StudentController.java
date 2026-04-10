package com.college.controller;

import com.college.core.DBConnection;
import com.college.core.SessionManager;
import com.college.dao.academic.StudentDAO;
import com.college.model.academic.Student;
import com.college.util.DemoData;

/**
 * Provides student profile data and computed stats (CGPA, attendance).
 */
public class StudentController {

    private final StudentDAO dao = new StudentDAO();

    public Student getStudent() {
        if (DBConnection.isDemoMode()) return DemoData.getStudent();
        return dao.findById(SessionManager.getStudentId());
    }

    /** Returns CGPA computed from results, or demo value. */
    public double getCGPA() {
        if (DBConnection.isDemoMode()) return DemoData.getCGPA();
        // Real implementation: query Result join Exam, average marks/maxMarks * 10
        // TODO: replace stub once DB connected
        return DemoData.getCGPA();
    }

    /** Returns attendance percentage for the student. */
    public double getAttendancePct() {
        if (DBConnection.isDemoMode()) return DemoData.getAttendancePct();
        // TODO: real query
        return DemoData.getAttendancePct();
    }

    // --- Detail view helpers ---
    public String getCourseName()  { return DBConnection.isDemoMode() ? DemoData.getCourse()      : "--"; }
    public String getDeptName()    { return DBConnection.isDemoMode() ? DemoData.getDept()         : "--"; }
    public String getYearLabel()   { return DBConnection.isDemoMode() ? DemoData.getYear()         : "--"; }
    public String getSemLabel()    { return DBConnection.isDemoMode() ? DemoData.getSemester()     : "--"; }
    public String getBranch()      { return DBConnection.isDemoMode() ? DemoData.getBranch()       : "--"; }
    public int    getBacklog()     { return DBConnection.isDemoMode() ? DemoData.getBacklog()      : 0;   }
    public String getFacultyName() { return DBConnection.isDemoMode() ? DemoData.getFacultyName()  : "--"; }
    public String getSubjectList() { return DBConnection.isDemoMode() ? DemoData.getSubjectList()  : "--"; }
}
