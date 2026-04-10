package com.college.model.attendance;

import java.util.Date;

public class Attendance {
    private int    attendanceId;
    private int    studentId;
    private int    subjectId;
    private int    facultyId;
    private Date   date;
    private String status; // PRESENT | ABSENT

    public Attendance() {}

    public int    getAttendanceId() { return attendanceId; }
    public int    getStudentId()    { return studentId; }
    public int    getSubjectId()    { return subjectId; }
    public int    getFacultyId()    { return facultyId; }
    public Date   getDate()         { return date; }
    public String getStatus()       { return status; }

    public void setAttendanceId(int v) { attendanceId = v; }
    public void setStudentId(int v)    { studentId    = v; }
    public void setSubjectId(int v)    { subjectId    = v; }
    public void setFacultyId(int v)    { facultyId    = v; }
    public void setDate(Date v)        { date         = v; }
    public void setStatus(String v)    { status       = v; }
}
