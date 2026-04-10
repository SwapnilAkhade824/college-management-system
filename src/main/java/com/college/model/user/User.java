package com.college.model.user;

public class User {
    private int    userId;
    private String username;
    private String password;
    private String role;   // ADMIN, FACULTY, STUDENT, ACCOUNTS
    private String status; // ACTIVE, INACTIVE

    public User() {}
    public User(int userId, String username, String password, String role, String status) {
        this.userId   = userId;   this.username = username;
        this.password = password; this.role     = role;
        this.status   = status;
    }

    public int    getUserId()   { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole()     { return role; }
    public String getStatus()   { return status; }

    public void setUserId(int v)     { userId   = v; }
    public void setUsername(String v){ username  = v; }
    public void setPassword(String v){ password  = v; }
    public void setRole(String v)    { role      = v; }
    public void setStatus(String v)  { status    = v; }

    @Override public String toString() {
        return "User[" + userId + ", " + username + ", " + role + "]";
    }
}
