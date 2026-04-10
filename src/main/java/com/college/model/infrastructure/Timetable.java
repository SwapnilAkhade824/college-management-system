package com.college.model.infrastructure;

import java.sql.Time;

public class Timetable {
    private int    timetableId;
    private int    subjectId;
    private int    facultyId;
    private int    roomId;
    private String day; // MONDAY ... SUNDAY
    private Time   startTime;
    private Time   endTime;

    public Timetable() {}

    public int    getTimetableId() { return timetableId; }
    public int    getSubjectId()   { return subjectId; }
    public int    getFacultyId()   { return facultyId; }
    public int    getRoomId()      { return roomId; }
    public String getDay()         { return day; }
    public Time   getStartTime()   { return startTime; }
    public Time   getEndTime()     { return endTime; }

    public void setTimetableId(int v)  { timetableId = v; }
    public void setSubjectId(int v)    { subjectId   = v; }
    public void setFacultyId(int v)    { facultyId   = v; }
    public void setRoomId(int v)       { roomId      = v; }
    public void setDay(String v)       { day         = v; }
    public void setStartTime(Time v)   { startTime   = v; }
    public void setEndTime(Time v)     { endTime     = v; }
}
