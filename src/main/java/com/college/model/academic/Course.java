package com.college.model.academic;

public class Course {
    private int    courseId;
    private String courseName;
    private int    deptId;
    private int    durationYears;
    private int    totalCredits;

    public Course() {}
    public Course(int id, String name, int deptId, int dur, int credits) {
        this.courseId = id; this.courseName = name;
        this.deptId = deptId; this.durationYears = dur; this.totalCredits = credits;
    }

    public int    getCourseId()      { return courseId; }
    public String getCourseName()    { return courseName; }
    public int    getDeptId()        { return deptId; }
    public int    getDurationYears() { return durationYears; }
    public int    getTotalCredits()  { return totalCredits; }

    public void setCourseId(int v)       { courseId      = v; }
    public void setCourseName(String v)  { courseName    = v; }
    public void setDeptId(int v)         { deptId        = v; }
    public void setDurationYears(int v)  { durationYears = v; }
    public void setTotalCredits(int v)   { totalCredits  = v; }

    @Override public String toString() { return courseName; }
}
