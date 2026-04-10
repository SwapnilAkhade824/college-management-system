package com.college.model.academic;

public class Department {
    private int    deptId;
    private String deptName;
    private String officeLocation;
    private String phone;

    public Department() {}
    public Department(int id, String name, String loc, String phone) {
        this.deptId = id; this.deptName = name;
        this.officeLocation = loc; this.phone = phone;
    }

    public int    getDeptId()         { return deptId; }
    public String getDeptName()       { return deptName; }
    public String getOfficeLocation() { return officeLocation; }
    public String getPhone()          { return phone; }

    public void setDeptId(int v)          { deptId         = v; }
    public void setDeptName(String v)     { deptName       = v; }
    public void setOfficeLocation(String v){ officeLocation = v; }
    public void setPhone(String v)        { phone          = v; }
}
