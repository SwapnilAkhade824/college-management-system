package com.college.model.finance;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private int        paymentId;
    private int        studentId;
    private int        studentFeeId;
    private Date       paymentDate;
    private BigDecimal amountPaid;
    private String     paymentMode;
    private String     status;

    public Payment() {}

    public int        getPaymentId()    { return paymentId; }
    public int        getStudentId()    { return studentId; }
    public int        getStudentFeeId() { return studentFeeId; }
    public Date       getPaymentDate()  { return paymentDate; }
    public BigDecimal getAmountPaid()   { return amountPaid; }
    public String     getPaymentMode()  { return paymentMode; }
    public String     getStatus()       { return status; }

    public void setPaymentId(int v)       { paymentId    = v; }
    public void setStudentId(int v)       { studentId    = v; }
    public void setStudentFeeId(int v)    { studentFeeId = v; }
    public void setPaymentDate(Date v)    { paymentDate  = v; }
    public void setAmountPaid(BigDecimal v){ amountPaid  = v; }
    public void setPaymentMode(String v)  { paymentMode  = v; }
    public void setStatus(String v)       { status       = v; }
}
