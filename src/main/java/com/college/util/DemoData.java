package com.college.util;

import com.college.model.academic.Student;
import com.college.model.system.Notification;
import com.college.model.user.User;

import java.util.*;

/**
 * Hardcoded demo data returned when DB is unavailable.
 * Login: student1 / password
 */
public final class DemoData {

    private DemoData() {}

    public static User getUser() {
        User u = new User();
        u.setUserId(1); u.setUsername("student1");
        u.setPassword("password"); u.setRole("STUDENT"); u.setStatus("ACTIVE");
        return u;
    }

    public static Student getStudent() {
        Student s = new Student();
        s.setStudentId(1); s.setUserId(1);
        s.setRollNo("STU2024001");
        s.setFirstName("Swapnil"); s.setLastName("Akhade");
        s.setGender("MALE");
        try { s.setDob(new java.text.SimpleDateFormat("yyyy-MM-dd").parse("2003-06-15")); }
        catch (Exception ignored) {}
        s.setEmail("swapnil@college.edu"); s.setPhone("9876543210");
        s.setAddress("123 College Road, Pune, Maharashtra - 411001");
        s.setCourseId(1); s.setYearOfAdmission(2022); s.setStatus("ACTIVE");
        return s;
    }

    // --- Student detail view data (joined) ---
    public static String getCourse()      { return "B.Tech Computer Science"; }
    public static String getDept()        { return "Computer Engineering"; }
    public static String getYear()        { return "3rd Year"; }
    public static String getSemester()    { return "Semester 5"; }
    public static String getBranch()      { return "Computer Science & Engineering"; }
    public static int    getBacklog()     { return 0; }
    public static String getFacultyName() { return "Prof. Rajesh Kumar"; }
    public static String getSubjectList() { return "Data Structures, DBMS, OS, CN, SE"; }

    // --- Stats ---
    public static double getCGPA()               { return 8.45; }
    public static double getAttendancePct()      { return 82.0; }
    public static int    getUnreadNotifCount()   { return 3; }

    // --- Attendance table ---
    public static String[] attendanceCols() {
        return new String[]{"Date", "Subject", "Status", "Marked By"};
    }
    public static Object[][] attendanceRows() {
        return new Object[][]{
            {"09/04/2026", "Data Structures",     "PRESENT", "Prof. Rajesh Kumar"},
            {"09/04/2026", "Operating Systems",   "PRESENT", "Prof. Meena Shah"},
            {"09/04/2026", "DBMS",               "ABSENT",  "Prof. Sunil Patil"},
            {"08/04/2026", "Computer Networks",   "PRESENT", "Prof. Anil Desai"},
            {"08/04/2026", "Software Engineering","PRESENT", "Prof. Kavita More"},
            {"08/04/2026", "Data Structures",     "PRESENT", "Prof. Rajesh Kumar"},
            {"07/04/2026", "Operating Systems",   "ABSENT",  "Prof. Meena Shah"},
            {"07/04/2026", "DBMS",               "PRESENT", "Prof. Sunil Patil"},
            {"07/04/2026", "Computer Networks",   "PRESENT", "Prof. Anil Desai"},
            {"04/04/2026", "Software Engineering","PRESENT", "Prof. Kavita More"},
            {"04/04/2026", "Data Structures",     "PRESENT", "Prof. Rajesh Kumar"},
            {"04/04/2026", "Operating Systems",   "PRESENT", "Prof. Meena Shah"},
        };
    }

    // --- Payment table ---
    public static String[] paymentCols() {
        return new String[]{"Semester", "Total Fees", "Paid", "Pending", "Status", "Mode"};
    }
    public static Object[][] paymentRows() {
        return new Object[][]{
            {"Sem 1", "₹50,000", "₹50,000", "₹0",      "PAID",    "Online"},
            {"Sem 2", "₹50,000", "₹50,000", "₹0",      "PAID",    "DD"},
            {"Sem 3", "₹50,000", "₹50,000", "₹0",      "PAID",    "Online"},
            {"Sem 4", "₹50,000", "₹50,000", "₹0",      "PAID",    "Cash"},
            {"Sem 5", "₹55,000", "₹30,000", "₹25,000", "PENDING", "--"},
        };
    }

    // --- Timetable ---
    public static String[] timetableCols() {
        return new String[]{"Day", "Time", "Subject", "Faculty", "Room"};
    }
    public static Object[][] timetableAllRows() {
        return new Object[][]{
            {"Monday",    "09:00–10:00", "Data Structures",     "Prof. Rajesh Kumar", "CS-101"},
            {"Monday",    "10:00–11:00", "Operating Systems",   "Prof. Meena Shah",   "CS-102"},
            {"Monday",    "11:00–12:00", "DBMS",               "Prof. Sunil Patil",  "CS-103"},
            {"Tuesday",   "09:00–10:00", "Computer Networks",   "Prof. Anil Desai",   "CS-104"},
            {"Tuesday",   "10:00–11:00", "Software Engineering","Prof. Kavita More",  "CS-105"},
            {"Tuesday",   "11:00–12:00", "Data Structures Lab", "Prof. Rajesh Kumar", "CS-Lab"},
            {"Wednesday", "09:00–10:00", "Data Structures",     "Prof. Rajesh Kumar", "CS-101"},
            {"Wednesday", "10:00–11:00", "DBMS",               "Prof. Sunil Patil",  "CS-103"},
            {"Wednesday", "11:00–12:00", "Computer Networks",   "Prof. Anil Desai",   "CS-104"},
            {"Thursday",  "09:00–10:00", "Operating Systems",   "Prof. Meena Shah",   "CS-102"},
            {"Thursday",  "10:00–11:00", "Software Engineering","Prof. Kavita More",  "CS-105"},
            {"Thursday",  "11:00–12:00", "DBMS Lab",           "Prof. Sunil Patil",  "CS-Lab"},
            {"Friday",    "09:00–10:00", "Data Structures",     "Prof. Rajesh Kumar", "CS-101"},
            {"Friday",    "10:00–11:00", "Computer Networks",   "Prof. Anil Desai",   "CS-104"},
            {"Friday",    "02:00–04:00", "Network Lab",         "Prof. Anil Desai",   "NW-Lab"},
        };
    }
    public static Object[][] timetableTodayRows() {
        String today = DateUtil.todayDayName(); // MONDAY, TUESDAY, etc.
        String cap   = today.substring(0, 1) + today.substring(1).toLowerCase();
        List<Object[]> filtered = new ArrayList<>();
        for (Object[] row : timetableAllRows()) {
            if (cap.equalsIgnoreCase(row[0].toString())) filtered.add(row);
        }
        if (filtered.isEmpty()) {
            // Weekend fallback
            return new Object[][]{{"No classes today (" + cap + ")", "", "", "", ""}};
        }
        return filtered.toArray(new Object[0][]);
    }

    // --- Notifications ---
    public static List<Notification> getNotifications() {
        List<Notification> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        list.add(new Notification(1, 1,
            "Semester 5 fee payment due before 30th April 2026", "ALERT", false, c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, -1);
        list.add(new Notification(2, 1,
            "Result declared for Mid-Semester Examination — Sem 5", "NOTIFICATION", false, c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, -1);
        list.add(new Notification(3, 1,
            "Your attendance in DBMS is 68% — below 75% threshold", "ALERT", false, c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, -2);
        list.add(new Notification(4, 1,
            "Assignment submitted successfully for Software Engineering", "NOTIFICATION", true, c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, -1);
        list.add(new Notification(5, 1,
            "College annual sports day scheduled for 25th April 2026", "NOTIFICATION", true, c.getTime()));
        return list;
    }

    // --- Subjects (for filter dropdown) ---
    public static String[] getSubjectNames() {
        return new String[]{
            "All Subjects", "Data Structures", "Operating Systems",
            "DBMS", "Computer Networks", "Software Engineering"
        };
    }
}
