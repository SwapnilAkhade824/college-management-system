package com.college.core;

/**
 * Stores information about the currently logged-in user.
 * Stateless singleton — all fields are static.
 */
public final class SessionManager {

    private SessionManager() {}

    private static int    userId;
    private static String userRole;
    private static String username;
    private static String displayName;
    private static int    studentId;
    private static int    courseId;

    public static void setUser(int id, String role, String uname, String name) {
        userId      = id;
        userRole    = role;
        username    = uname;
        displayName = name;
    }

    public static void setStudentId(int id) { studentId = id; }
    public static void setCourseId(int id)  { courseId  = id; }

    public static int    getUserId()      { return userId; }
    public static String getUserRole()    { return userRole; }
    public static String getUsername()    { return username; }
    public static String getDisplayName() { return displayName; }
    public static int    getStudentId()   { return studentId; }
    public static int    getCourseId()    { return courseId; }

    public static boolean isStudent() { return "STUDENT".equalsIgnoreCase(userRole); }
    public static boolean isFaculty() { return "FACULTY".equalsIgnoreCase(userRole); }
    public static boolean isAdmin()   { return "ADMIN".equalsIgnoreCase(userRole); }

    public static void clear() {
        userId = 0; userRole = null; username = null;
        displayName = null; studentId = 0; courseId = 0;
    }
}
