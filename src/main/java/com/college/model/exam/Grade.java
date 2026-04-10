package com.college.model.exam;

public class Grade {
    private int    gradeId;
    private String gradeName;
    private int    minMarks;
    private int    maxMarks;

    public Grade() {}
    public Grade(int id, String name, int min, int max) {
        this.gradeId = id; this.gradeName = name; this.minMarks = min; this.maxMarks = max;
    }

    public int    getGradeId()   { return gradeId; }
    public String getGradeName() { return gradeName; }
    public int    getMinMarks()  { return minMarks; }
    public int    getMaxMarks()  { return maxMarks; }

    public void setGradeId(int v)    { gradeId   = v; }
    public void setGradeName(String v){ gradeName = v; }
    public void setMinMarks(int v)   { minMarks  = v; }
    public void setMaxMarks(int v)   { maxMarks  = v; }
}
