package com.college.model.academic;

import java.util.Date;

public class Faculty {
    private int    facultyId;
    private int    userId;
    private String firstName;
    private String lastName;
    private String designation;
    private int    deptId;
    private Date   dateOfJoining;
    private String status;

    public Faculty() {}

    public int    getFacultyId()    { return facultyId; }
    public int    getUserId()       { return userId; }
    public String getFirstName()    { return firstName; }
    public String getLastName()     { return lastName; }
    public String getFullName()     { return firstName + " " + lastName; }
    public String getDesignation()  { return designation; }
    public int    getDeptId()       { return deptId; }
    public Date   getDateOfJoining(){ return dateOfJoining; }
    public String getStatus()       { return status; }

    public void setFacultyId(int v)       { facultyId     = v; }
    public void setUserId(int v)          { userId        = v; }
    public void setFirstName(String v)    { firstName     = v; }
    public void setLastName(String v)     { lastName      = v; }
    public void setDesignation(String v)  { designation   = v; }
    public void setDeptId(int v)          { deptId        = v; }
    public void setDateOfJoining(Date v)  { dateOfJoining = v; }
    public void setStatus(String v)       { status        = v; }
}
