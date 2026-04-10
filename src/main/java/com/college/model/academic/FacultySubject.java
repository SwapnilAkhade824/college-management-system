package com.college.model.academic;

public class FacultySubject {
    private int facultyId;
    private int subjectId;
    private int semester;

    public FacultySubject() {}
    public FacultySubject(int facultyId, int subjectId, int semester) {
        this.facultyId = facultyId; this.subjectId = subjectId; this.semester = semester;
    }

    public int getFacultyId() { return facultyId; }
    public int getSubjectId() { return subjectId; }
    public int getSemester()  { return semester; }

    public void setFacultyId(int v) { facultyId = v; }
    public void setSubjectId(int v) { subjectId = v; }
    public void setSemester(int v)  { semester  = v; }
}
