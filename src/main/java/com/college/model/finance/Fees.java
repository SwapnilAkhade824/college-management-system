package com.college.model.finance;

import java.math.BigDecimal;

public class Fees {
    private int        feeId;
    private int        courseId;
    private int        semester;
    private BigDecimal amount;

    public Fees() {}

    public int        getFeeId()   { return feeId; }
    public int        getCourseId(){ return courseId; }
    public int        getSemester(){ return semester; }
    public BigDecimal getAmount()  { return amount; }

    public void setFeeId(int v)        { feeId    = v; }
    public void setCourseId(int v)     { courseId = v; }
    public void setSemester(int v)     { semester = v; }
    public void setAmount(BigDecimal v){ amount   = v; }
}
