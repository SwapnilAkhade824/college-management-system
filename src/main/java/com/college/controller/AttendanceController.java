package com.college.controller;

import com.college.core.DBConnection;
import com.college.util.DemoData;

public class AttendanceController {

    public String[] getColumns() { return DemoData.attendanceCols(); }

    public Object[][] getAttendanceData(String subjectFilter, String statusFilter) {
        if (DBConnection.isDemoMode()) return filter(DemoData.attendanceRows(), subjectFilter, statusFilter);
        // TODO: DB query with filters
        return filter(DemoData.attendanceRows(), subjectFilter, statusFilter);
    }

    public String[] getSubjectNames() { return DemoData.getSubjectNames(); }

    private Object[][] filter(Object[][] rows, String subject, String status) {
        if ((subject == null || subject.equals("All Subjects")) &&
            (status  == null || status.equals("All"))) return rows;
        java.util.List<Object[]> out = new java.util.ArrayList<>();
        for (Object[] r : rows) {
            boolean subMatch = subject == null || subject.equals("All Subjects") ||
                               r[1].toString().equals(subject);
            boolean stMatch  = status  == null || status.equals("All") ||
                               r[2].toString().equalsIgnoreCase(status);
            if (subMatch && stMatch) out.add(r);
        }
        return out.toArray(new Object[0][]);
    }
}
