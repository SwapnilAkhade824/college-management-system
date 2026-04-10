package com.college.model.academic;

public class StudentSubject {
    private int studentId;
    private int subjectId;
    private int semester;

    public StudentSubject() {}
    public StudentSubject(int studentId, int subjectId, int semester) {
        this.studentId = studentId; this.subjectId = subjectId; this.semester = semester;
    }

    public int getStudentId() { return studentId; }
    public int getSubjectId() { return subjectId; }
    public int getSemester()  { return semester; }

    public void setStudentId(int v) { studentId = v; }
    public void setSubjectId(int v) { subjectId = v; }
    public void setSemester(int v)  { semester  = v; }
}
