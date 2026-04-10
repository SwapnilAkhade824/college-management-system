package com.college.controller;

import com.college.core.SessionManager;
import com.college.model.system.Notification;

import java.util.List;

public class DashboardController {

    private final StudentController      sc = new StudentController();
    private final NotificationController nc = new NotificationController();

    public String getWelcomeName()   { return SessionManager.getDisplayName(); }
    public double getCGPA()          { return sc.getCGPA(); }
    public double getAttendancePct() { return sc.getAttendancePct(); }

    /** Returns up to `limit` recent notifications for the mini panel. */
    public List<Notification> getRecentNotifications(int limit) {
        List<Notification> all = nc.getAll();
        return all.size() <= limit ? all : all.subList(0, limit);
    }

    public int getUnreadCount() { return nc.getUnreadCount(); }
}
