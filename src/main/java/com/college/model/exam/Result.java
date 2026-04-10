package com.college.model.exam;

public class Result {
    private int resultId;
    private int examId;
    private int studentId;
    private int marksObtained;
    private int gradeId;

    public Result() {}

    public int getResultId()      { return resultId; }
    public int getExamId()        { return examId; }
    public int getStudentId()     { return studentId; }
    public int getMarksObtained() { return marksObtained; }
    public int getGradeId()       { return gradeId; }

    public void setResultId(int v)      { resultId      = v; }
    public void setExamId(int v)        { examId        = v; }
    public void setStudentId(int v)     { studentId     = v; }
    public void setMarksObtained(int v) { marksObtained = v; }
    public void setGradeId(int v)       { gradeId       = v; }
}
