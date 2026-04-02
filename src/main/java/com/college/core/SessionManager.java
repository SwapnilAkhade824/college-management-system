package com.college.core;

/**
 * ============================================
 * CLASS: SessionManager
 * ============================================
 *
 * PURPOSE:
 * Stores information about currently logged-in user.
 *
 * RESPONSIBILITIES:
 * - Maintain session state
 * - Provide user role access
 *
 * USED BY:
 * - Controllers
 * - NavigationManager
 *
 * FIELDS:
 * - currentUserId
 * - currentUserRole
 *
 * METHODS TO IMPLEMENT:
 *
 * 1. setUser(int userId, String role)
 *    @return void
 *
 * 2. getUserId()
 *    @return int
 *
 * 3. getUserRole()
 *    @return String
 *
 * 4. clearSession()
 *    @return void
 *
 * ============================================
 */
public class SessionManager {

    private static int currentUserId;
    private static String currentUserRole;

    public static void setUser(int userId, String role) {}

    public static int getUserId() { return 0; }

    public static String getUserRole() { return null; }

    public static void clearSession() {}
}
