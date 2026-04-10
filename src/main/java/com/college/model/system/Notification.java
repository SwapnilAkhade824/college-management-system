package com.college.model.system;

import java.util.Date;

public class Notification {
    private int     notificationId;
    private int     userId;
    private String  message;
    private String  type;    // "NOTIFICATION" | "ALERT"
    private boolean isRead;
    private Date    createdAt;

    public Notification() {}
    public Notification(int id, int userId, String message, String type, boolean read, Date at){
        this.notificationId = id; this.userId = userId; this.message = message;
        this.type = type; this.isRead = read; this.createdAt = at;
    }

    public int     getNotificationId() { return notificationId; }
    public int     getUserId()         { return userId; }
    public String  getMessage()        { return message; }
    public String  getType()           { return type; }
    public boolean isRead()            { return isRead; }
    public Date    getCreatedAt()      { return createdAt; }

    public void setNotificationId(int v) { notificationId = v; }
    public void setUserId(int v)         { userId         = v; }
    public void setMessage(String v)     { message        = v; }
    public void setType(String v)        { type           = v; }
    public void setRead(boolean v)       { isRead         = v; }
    public void setCreatedAt(Date v)     { createdAt      = v; }
}
