package com.college.model.exam;

import java.util.Date;

public class Exam {
    private int    examId;
    private int    subjectId;
    private String examType; // MIDTERM | FINAL | QUIZ
    private Date   examDate;
    private int    maxMarks;

    public Exam() {}

    public int    getExamId()    { return examId; }
    public int    getSubjectId() { return subjectId; }
    public String getExamType()  { return examType; }
    public Date   getExamDate()  { return examDate; }
    public int    getMaxMarks()  { return maxMarks; }

    public void setExamId(int v)     { examId    = v; }
    public void setSubjectId(int v)  { subjectId = v; }
    public void setExamType(String v){ examType  = v; }
    public void setExamDate(Date v)  { examDate  = v; }
    public void setMaxMarks(int v)   { maxMarks  = v; }
}
