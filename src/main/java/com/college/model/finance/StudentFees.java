package com.college.model.finance;

import java.math.BigDecimal;

public class StudentFees {
    private int        studentFeeId;
    private int        studentId;
    private int        feeId;
    private BigDecimal paidAmount;
    private String     status; // PAID | PENDING

    public StudentFees() {}

    public int        getStudentFeeId(){ return studentFeeId; }
    public int        getStudentId()   { return studentId; }
    public int        getFeeId()       { return feeId; }
    public BigDecimal getPaidAmount()  { return paidAmount; }
    public String     getStatus()      { return status; }

    public void setStudentFeeId(int v)       { studentFeeId = v; }
    public void setStudentId(int v)          { studentId    = v; }
    public void setFeeId(int v)              { feeId        = v; }
    public void setPaidAmount(BigDecimal v)  { paidAmount   = v; }
    public void setStatus(String v)          { status       = v; }
}
