-- =========================================
-- COMPLETE CORRECTED SQL SCHEMA
-- College Management System
--
-- Tables: Total(19)
--      Users
--      Department
--      Course
--      Subject
--      Student
--      Faculty
--      Student_Subject
--      Faculty_Subject
--      Attendance
--      Exam
--      Grade
--      Result
--      Fees
--      Student_Fees
--      Payment
--      Classroom
--      Timetable
--      Notifications
--      Audit_Log
--
-- Views: Total(6)
--      Attendance_Defaulters
--      Student_Details
--      Faculty_Details
--      Student_Grades
--      Fee_Status
--      Daily_Timetable
-- =========================================

-- ==============================
-- TABLES
-- ==============================

-- ==============================
-- USERS (Authentication + RBAC)
-- ==============================
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'FACULTY', 'STUDENT', 'ACCOUNTS') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==============================
-- DEPARTMENT
-- ==============================
CREATE TABLE Department (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(100) NOT NULL,
    office_location VARCHAR(100),
    phone VARCHAR(20)
);

-- ==============================
-- COURSE
-- ==============================
CREATE TABLE Course (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    dept_id INT,
    duration_years INT,
    total_credits INT,
    FOREIGN KEY (dept_id) REFERENCES Department(dept_id) ON DELETE SET NULL
);

-- ==============================
-- SUBJECT
-- ==============================
CREATE TABLE Subject (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(100) NOT NULL,
    course_id INT,
    semester INT,
    credits INT,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE SET NULL
);

-- ==============================
-- STUDENT
-- ==============================
CREATE TABLE Student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    roll_no VARCHAR(20) UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    dob DATE,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20) UNIQUE,
    address TEXT,
    course_id INT,
    year_of_admission YEAR,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE SET NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE SET NULL
);

-- ==============================
-- FACULTY
-- ==============================
CREATE TABLE Faculty (
    faculty_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    designation VARCHAR(50),
    dept_id INT,
    date_of_joining DATE,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    FOREIGN KEY (dept_id) REFERENCES Department(dept_id) ON DELETE SET NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE SET NULL
);

-- ==============================
-- STUDENT-SUBJECT (ENROLLMENT)
-- ==============================
CREATE TABLE Student_Subject (
    student_id INT,
    subject_id INT,
    semester INT,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE
);

-- ==============================
-- FACULTY-SUBJECT
-- ==============================
CREATE TABLE Faculty_Subject (
    faculty_id INT,
    subject_id INT,
    semester INT,
    PRIMARY KEY (faculty_id, subject_id),
    FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE
);

-- ==============================
-- ATTENDANCE
-- ==============================
CREATE TABLE Attendance (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    subject_id INT,
    faculty_id INT,
    date DATE,
    status ENUM('PRESENT', 'ABSENT'),
    marked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE SET NULL,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE SET NULL,
    FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id) ON DELETE SET NULL
);

-- ==============================
-- EXAM
-- ==============================
CREATE TABLE Exam (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_id INT,
    exam_type ENUM('MIDTERM', 'FINAL', 'QUIZ'),
    exam_date DATE,
    max_marks INT,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE SET NULL
);

-- ==============================
-- GRADE
-- ==============================
CREATE TABLE Grade (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    grade_name VARCHAR(5) UNIQUE NOT NULL,
    min_marks INT,
    max_marks INT
);

-- ==============================
-- RESULT
-- ==============================
CREATE TABLE Result (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    exam_id INT,
    student_id INT,
    marks_obtained INT,
    grade_id INT,
    FOREIGN KEY (exam_id) REFERENCES Exam(exam_id) ON DELETE SET NULL,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE SET NULL,
    FOREIGN KEY (grade_id) REFERENCES Grade(grade_id) ON DELETE SET NULL
);

-- ==============================
-- FEES (Structure)
-- ==============================
CREATE TABLE Fees (
    fee_id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT,
    semester INT,
    amount DECIMAL(10,2),
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE SET NULL
);

-- ==============================
-- STUDENT FEES (Tracking)
-- ==============================
CREATE TABLE Student_Fees (
    student_fee_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    fee_id INT,
    paid_amount DECIMAL(10,2) DEFAULT 0,
    status ENUM('PAID', 'PENDING') DEFAULT 'PENDING',
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE SET NULL,
    FOREIGN KEY (fee_id) REFERENCES Fees(fee_id) ON DELETE SET NULL
);

-- ==============================
-- PAYMENT
-- ==============================
CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    student_fee_id INT,
    payment_date DATE,
    amount_paid DECIMAL(10,2),
    payment_mode VARCHAR(20),
    status VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE SET NULL,
    FOREIGN KEY (student_fee_id) REFERENCES Student_Fees(student_fee_id) ON DELETE SET NULL
);

-- ==============================
-- CLASSROOM
-- ==============================
CREATE TABLE Classroom (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    building VARCHAR(50),
    capacity INT
);

-- ==============================
-- TIMETABLE
-- ==============================
CREATE TABLE Timetable (
    timetable_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_id INT,
    faculty_id INT,
    room_id INT,
    day ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON DELETE SET NULL,
    FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id) ON DELETE SET NULL,
    FOREIGN KEY (room_id) REFERENCES Classroom(room_id) ON DELETE SET NULL
);

-- ==============================
-- NOTIFICATIONS
-- ==============================
CREATE TABLE Notifications (
    notification_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    message TEXT,
    type VARCHAR(50),
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE SET NULL
);

-- ==============================
-- AUDIT LOG
-- ==============================
CREATE TABLE Audit_Log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    action TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==============================
-- VIEWS
-- ==============================

-- ==============================
-- ATTENDANCE DEFAULTERS
-- ==============================
CREATE VIEW Attendance_Defaulters AS
SELECT student_id,
       (SUM(status = 'PRESENT') / COUNT(*)) * 100 AS attendance_percentage
FROM Attendance
GROUP BY student_id
HAVING attendance_percentage < 75;

-- ==============================
-- STUDENT DETAILS
-- ==============================
CREATE VIEW Student_Details AS
SELECT
    s.student_id,
    s.roll_no,
    s.first_name,
    s.last_name,
    s.gender,
    s.dob,
    s.email,
    s.phone,
    s.address,
    c.course_name,
    d.dept_name,
    s.year_of_admission,
    s.status
FROM Student s
LEFT JOIN Course c ON s.course_id = c.course_id
LEFT JOIN Department d ON c.dept_id = d.dept_id;

-- ==============================
-- FACUALTY DETAILS
-- ==============================
CREATE VIEW Faculty_Details AS
SELECT
    f.faculty_id,
    f.first_name,
    f.last_name,
    u.email,
    f.designation,
    d.dept_name,
    f.date_of_joining,
    f.status
FROM Faculty f
LEFT JOIN Department d ON f.dept_id = d.dept_id
LEFT JOIN Users u ON f.user_id = u.user_id;

-- ==============================
-- STUDENT GRADES
-- ==============================
CREATE VIEW Student_Grades AS
SELECT
    r.student_id,
    s.first_name,
    s.last_name,
    sub.subject_name,
    e.exam_type,
    r.marks_obtained,
    g.grade_name
FROM Result r
LEFT JOIN Student s ON r.student_id = s.student_id
LEFT JOIN Exam e ON r.exam_id = e.exam_id
LEFT JOIN Subject sub ON e.subject_id = sub.subject_id
LEFT JOIN Grade g ON r.grade_id = g.grade_id;

-- ==============================
-- FEE STATUS
-- ==============================
CREATE VIEW Fee_Status AS
SELECT
    sf.student_id,
    s.first_name,
    s.last_name,
    f.semester,
    f.amount AS total_amount,
    sf.paid_amount,
    (f.amount - sf.paid_amount) AS pending_amount,
    sf.status
FROM Student_Fees sf
LEFT JOIN Fees f ON sf.fee_id = f.fee_id
LEFT JOIN Student s ON sf.student_id = s.student_id;

-- ==============================
-- DAILY TIMETABLE
-- ==============================
CREATE VIEW Daily_Timetable AS
SELECT
    t.day,
    t.start_time,
    t.end_time,
    sub.subject_name,
    c.course_name,
    f.first_name AS faculty_first_name,
    f.last_name AS faculty_last_name,
    cr.building,
    cr.room_id
FROM Timetable t
LEFT JOIN Subject sub ON t.subject_id = sub.subject_id
LEFT JOIN Course c ON sub.course_id = c.course_id
LEFT JOIN Faculty f ON t.faculty_id = f.faculty_id
LEFT JOIN Classroom cr ON t.room_id = cr.room_id;
