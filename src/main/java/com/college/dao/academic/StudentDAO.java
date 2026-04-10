package com.college.dao.academic;

import com.college.core.DBConnection;
import com.college.model.academic.Student;
import com.college.util.DemoData;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    public Student findByUserId(int userId) {
        if (DBConnection.isDemoMode()) {
            Student d = DemoData.getStudent();
            return d.getUserId() == userId ? d : null;
        }
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Student WHERE user_id=?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Student findById(int id) {
        if (DBConnection.isDemoMode()) {
            Student d = DemoData.getStudent();
            return d.getStudentId() == id ? d : null;
        }
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Student WHERE student_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean insert(Student s) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Student(user_id,roll_no,first_name,last_name,gender,dob,email,phone,address,course_id,year_of_admission,status)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)")) {
            ps.setInt(1,s.getUserId()); ps.setString(2,s.getRollNo());
            ps.setString(3,s.getFirstName()); ps.setString(4,s.getLastName());
            ps.setString(5,s.getGender());
            ps.setDate(6, s.getDob()==null?null:new java.sql.Date(s.getDob().getTime()));
            ps.setString(7,s.getEmail()); ps.setString(8,s.getPhone());
            ps.setString(9,s.getAddress()); ps.setInt(10,s.getCourseId());
            ps.setInt(11,s.getYearOfAdmission()); ps.setString(12,"ACTIVE");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean update(Student s) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE Student SET first_name=?,last_name=?,email=?,phone=?,address=? WHERE student_id=?")) {
            ps.setString(1,s.getFirstName()); ps.setString(2,s.getLastName());
            ps.setString(3,s.getEmail()); ps.setString(4,s.getPhone());
            ps.setString(5,s.getAddress()); ps.setInt(6,s.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean delete(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE Student SET status='INACTIVE' WHERE student_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        if (conn == null) { list.add(DemoData.getStudent()); return list; }
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Student WHERE status='ACTIVE'")) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Student map(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id")); s.setUserId(rs.getInt("user_id"));
        s.setRollNo(rs.getString("roll_no")); s.setFirstName(rs.getString("first_name"));
        s.setLastName(rs.getString("last_name")); s.setGender(rs.getString("gender"));
        s.setDob(rs.getDate("dob")); s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone")); s.setAddress(rs.getString("address"));
        s.setCourseId(rs.getInt("course_id")); s.setYearOfAdmission(rs.getInt("year_of_admission"));
        s.setStatus(rs.getString("status"));
        return s;
    }
}
