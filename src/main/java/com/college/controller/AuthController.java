package com.college.controller;

import com.college.core.DBConnection;
import com.college.core.SessionManager;
import com.college.dao.academic.StudentDAO;
import com.college.dao.user.UserDAO;
import com.college.model.academic.Student;
import com.college.model.user.User;
import com.college.util.Constants;
import com.college.util.DemoData;

/**
 * Handles login and signup logic.
 */
public class AuthController {

    private final UserDAO    userDAO    = new UserDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    /**
     * Authenticates user.
     * @return true on success; populates SessionManager.
     */
    public boolean login(String username, String password) {
        // Demo mode fallback
        if (DBConnection.isDemoMode()) {
            if (Constants.DEMO_USER.equals(username) && Constants.DEMO_PASS.equals(password)) {
                User u = DemoData.getUser();
                SessionManager.setUser(u.getUserId(), u.getRole(), u.getUsername(), "Swapnil Akhade");
                Student s = DemoData.getStudent();
                SessionManager.setStudentId(s.getStudentId());
                SessionManager.setCourseId(s.getCourseId());
                return true;
            }
            return false;
        }

        User user = userDAO.findByUsername(username);
        if (user == null) return false;
        if (!user.getPassword().equals(password)) return false;
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) return false;

        // Determine display name
        String displayName = username;
        if ("STUDENT".equalsIgnoreCase(user.getRole())) {
            Student s = studentDAO.findByUserId(user.getUserId());
            if (s != null) {
                displayName = s.getFullName();
                SessionManager.setStudentId(s.getStudentId());
                SessionManager.setCourseId(s.getCourseId());
            }
        }

        SessionManager.setUser(user.getUserId(), user.getRole(), username, displayName);
        return true;
    }

    /**
     * Submits a signup request (stores User with INACTIVE status).
     * @return true on success.
     */
    public boolean requestAccount(String username, String password, String role) {
        if (DBConnection.isDemoMode()) return false; // No-op in demo
        User u = new User();
        u.setUsername(username); u.setPassword(password);
        u.setRole(role.toUpperCase()); u.setStatus("INACTIVE");
        return userDAO.insert(u);
    }

    /**
     * Changes password for current user.
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (DBConnection.isDemoMode()) {
            return Constants.DEMO_PASS.equals(oldPassword);
        }
        User user = userDAO.findById(SessionManager.getUserId());
        if (user == null || !user.getPassword().equals(oldPassword)) return false;
        user.setPassword(newPassword);
        return userDAO.update(user);
    }

    public void logout() {
        SessionManager.clear();
    }
}
