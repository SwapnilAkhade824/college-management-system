package com.college.model.system;

import java.util.Date;

public class AuditLog {
    private int    logId;
    private int    userId;
    private String action;
    private Date   timestamp;

    public AuditLog() {}

    public int    getLogId()    { return logId; }
    public int    getUserId()   { return userId; }
    public String getAction()   { return action; }
    public Date   getTimestamp(){ return timestamp; }

    public void setLogId(int v)     { logId     = v; }
    public void setUserId(int v)    { userId    = v; }
    public void setAction(String v) { action    = v; }
    public void setTimestamp(Date v){ timestamp = v; }
}
