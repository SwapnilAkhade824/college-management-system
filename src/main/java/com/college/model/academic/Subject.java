package com.college.model.academic;

public class Subject {
    private int    subjectId;
    private String subjectName;
    private int    courseId;
    private int    semester;
    private int    credits;

    public Subject() {}
    public Subject(int id, String name, int courseId, int sem, int credits) {
        this.subjectId = id; this.subjectName = name;
        this.courseId = courseId; this.semester = sem; this.credits = credits;
    }

    public int    getSubjectId()   { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public int    getCourseId()    { return courseId; }
    public int    getSemester()    { return semester; }
    public int    getCredits()     { return credits; }

    public void setSubjectId(int v)    { subjectId   = v; }
    public void setSubjectName(String v){ subjectName = v; }
    public void setCourseId(int v)     { courseId    = v; }
    public void setSemester(int v)     { semester    = v; }
    public void setCredits(int v)      { credits     = v; }

    @Override public String toString() { return subjectName; }
}
