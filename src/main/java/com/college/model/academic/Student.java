package com.college.model.academic;

import java.util.Date;

public class Student {
    private int    studentId;
    private int    userId;
    private String rollNo;
    private String firstName;
    private String lastName;
    private String gender;
    private Date   dob;
    private String email;
    private String phone;
    private String address;
    private int    courseId;
    private int    yearOfAdmission;
    private String status;

    public Student() {}

    public int    getStudentId()      { return studentId; }
    public int    getUserId()         { return userId; }
    public String getRollNo()         { return rollNo; }
    public String getFirstName()      { return firstName; }
    public String getLastName()       { return lastName; }
    public String getFullName()       { return firstName + " " + lastName; }
    public String getGender()         { return gender; }
    public Date   getDob()            { return dob; }
    public String getEmail()          { return email; }
    public String getPhone()          { return phone; }
    public String getAddress()        { return address; }
    public int    getCourseId()       { return courseId; }
    public int    getYearOfAdmission(){ return yearOfAdmission; }
    public String getStatus()         { return status; }

    public void setStudentId(int v)       { studentId       = v; }
    public void setUserId(int v)          { userId          = v; }
    public void setRollNo(String v)       { rollNo          = v; }
    public void setFirstName(String v)    { firstName       = v; }
    public void setLastName(String v)     { lastName        = v; }
    public void setGender(String v)       { gender          = v; }
    public void setDob(Date v)            { dob             = v; }
    public void setEmail(String v)        { email           = v; }
    public void setPhone(String v)        { phone           = v; }
    public void setAddress(String v)      { address         = v; }
    public void setCourseId(int v)        { courseId        = v; }
    public void setYearOfAdmission(int v) { yearOfAdmission = v; }
    public void setStatus(String v)       { status          = v; }
}
