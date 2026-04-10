package com.college.controller;

import com.college.core.SessionManager;
import com.college.dao.system.NotificationDAO;
import com.college.model.system.Notification;
import com.college.util.DemoData;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationController {

    private final NotificationDAO dao = new NotificationDAO();

    public List<Notification> getAll() {
        return dao.findByUserId(SessionManager.getUserId());
    }

    public List<Notification> getFiltered(boolean notifOnly, boolean alertOnly) {
        List<Notification> all = getAll();
        if (!notifOnly && !alertOnly) return all;
        return all.stream().filter(n -> {
            if (notifOnly && "NOTIFICATION".equals(n.getType())) return true;
            if (alertOnly  && "ALERT".equals(n.getType()))        return true;
            return false;
        }).collect(Collectors.toList());
    }

    public int getUnreadCount() {
        return (int) getAll().stream().filter(n -> !n.isRead()).count();
    }

    public boolean markAllRead() {
        return dao.markAllRead(SessionManager.getUserId());
    }
}
