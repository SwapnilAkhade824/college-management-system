package com.college.controller;

import com.college.core.DBConnection;
import com.college.util.DemoData;

public class TimetableController {

    public String[] getColumns() { return DemoData.timetableCols(); }

    public Object[][] getFullTimetable() {
        return DBConnection.isDemoMode() ? DemoData.timetableAllRows() : DemoData.timetableAllRows();
    }

    public Object[][] getTodayTimetable() {
        return DBConnection.isDemoMode() ? DemoData.timetableTodayRows() : DemoData.timetableTodayRows();
    }
}
