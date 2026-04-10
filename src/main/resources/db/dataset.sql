-- =========================================
-- DUMMY DATASET
-- College Management System
-- =========================================

SET FOREIGN_KEY_CHECKS = 0;

-- ==============================
-- USERS
-- ==============================
INSERT INTO Users (user_id, username, password, role, status) VALUES
(1,  'admin01',      '$2b$10$hashedpassword001', 'ADMIN',    'ACTIVE'),
(2,  'admin02',      '$2b$10$hashedpassword002', 'ADMIN',    'ACTIVE'),
(3,  'faculty_raj',  '$2b$10$hashedpassword003', 'FACULTY',  'ACTIVE'),
(4,  'faculty_priya','$2b$10$hashedpassword004', 'FACULTY',  'ACTIVE'),
(5,  'faculty_suresh','$2b$10$hashedpassword005','FACULTY',  'ACTIVE'),
(6,  'faculty_meena','$2b$10$hashedpassword006', 'FACULTY',  'ACTIVE'),
(7,  'faculty_arjun','$2b$10$hashedpassword007', 'FACULTY',  'INACTIVE'),
(8,  'student_001',  '$2b$10$hashedpassword008', 'STUDENT',  'ACTIVE'),
(9,  'student_002',  '$2b$10$hashedpassword009', 'STUDENT',  'ACTIVE'),
(10, 'student_003',  '$2b$10$hashedpassword010', 'STUDENT',  'ACTIVE'),
(11, 'student_004',  '$2b$10$hashedpassword011', 'STUDENT',  'ACTIVE'),
(12, 'student_005',  '$2b$10$hashedpassword012', 'STUDENT',  'ACTIVE'),
(13, 'student_006',  '$2b$10$hashedpassword013', 'STUDENT',  'ACTIVE'),
(14, 'student_007',  '$2b$10$hashedpassword014', 'STUDENT',  'ACTIVE'),
(15, 'student_008',  '$2b$10$hashedpassword015', 'STUDENT',  'ACTIVE'),
(16, 'student_009',  '$2b$10$hashedpassword016', 'STUDENT',  'ACTIVE'),
(17, 'student_010',  '$2b$10$hashedpassword017', 'STUDENT',  'INACTIVE'),
(18, 'accounts_01',  '$2b$10$hashedpassword018', 'ACCOUNTS', 'ACTIVE'),
(19, 'accounts_02',  '$2b$10$hashedpassword019', 'ACCOUNTS', 'ACTIVE');

-- ==============================
-- DEPARTMENT
-- ==============================
INSERT INTO Department (dept_id, dept_name, office_location, phone) VALUES
(1, 'Computer Science',       'Block A, Room 101', '020-1234-5678'),
(2, 'Electronics Engineering','Block B, Room 201', '020-1234-5679'),
(3, 'Mechanical Engineering', 'Block C, Room 301', '020-1234-5680'),
(4, 'Business Administration','Block D, Room 401', '020-1234-5681'),
(5, 'Mathematics',            'Block A, Room 105', '020-1234-5682');

-- ==============================
-- COURSE
-- ==============================
INSERT INTO Course (course_id, course_name, dept_id, duration_years, total_credits) VALUES
(1, 'B.Tech Computer Science',       1, 4, 160),
(2, 'B.Tech Electronics Engineering',2, 4, 160),
(3, 'B.Tech Mechanical Engineering', 3, 4, 160),
(4, 'BBA',                           4, 3, 120),
(5, 'M.Tech Computer Science',       1, 2, 80),
(6, 'B.Sc Mathematics',              5, 3, 100);

-- ==============================
-- SUBJECT
-- ==============================
INSERT INTO Subject (subject_id, subject_name, course_id, semester, credits) VALUES
-- B.Tech CS Sem 1 & 2
(1,  'Engineering Mathematics I',      1, 1, 4),
(2,  'Programming in C',               1, 1, 4),
(3,  'Digital Logic Design',           1, 1, 3),
(4,  'Engineering Physics',            1, 1, 3),
(5,  'Engineering Mathematics II',     1, 2, 4),
(6,  'Data Structures',                1, 2, 4),
(7,  'Computer Organization',          1, 2, 3),
(8,  'Discrete Mathematics',           1, 2, 3),
-- B.Tech CS Sem 3 & 4
(9,  'Algorithms',                     1, 3, 4),
(10, 'Operating Systems',              1, 3, 4),
(11, 'Database Management Systems',    1, 4, 4),
(12, 'Computer Networks',              1, 4, 4),
-- B.Tech EE Sem 1 & 2
(13, 'Circuit Theory',                 2, 1, 4),
(14, 'Electronics Devices',            2, 1, 4),
(15, 'Signals and Systems',            2, 2, 4),
(16, 'Analog Electronics',             2, 2, 4),
-- BBA Sem 1 & 2
(17, 'Principles of Management',       4, 1, 4),
(18, 'Business Communication',         4, 1, 4),
(19, 'Accounting Fundamentals',        4, 2, 4),
(20, 'Marketing Management',           4, 2, 4),
-- M.Tech CS Sem 1
(21, 'Advanced Algorithms',            5, 1, 4),
(22, 'Machine Learning',               5, 1, 4);

-- ==============================
-- FACULTY
-- ==============================
INSERT INTO Faculty (faculty_id, user_id, first_name, last_name, designation, dept_id, date_of_joining, status) VALUES
(1, 3, 'Rajesh',  'Kumar',   'Professor',        1, '2015-07-01', 'ACTIVE'),
(2, 4, 'Priya',   'Sharma',  'Associate Professor',1,'2018-01-15', 'ACTIVE'),
(3, 5, 'Suresh',  'Patel',   'Assistant Professor',2,'2019-06-01', 'ACTIVE'),
(4, 6, 'Meena',   'Iyer',    'Professor',        4, '2012-08-01', 'ACTIVE'),
(5, 7, 'Arjun',   'Nair',    'Lecturer',         1, '2021-07-15', 'INACTIVE');

-- ==============================
-- STUDENT
-- ==============================
INSERT INTO Student (student_id, user_id, roll_no, first_name, last_name, gender, dob, email, phone, address, course_id, year_of_admission, status) VALUES
(1,  8,  'CS2022001', 'Aarav',    'Mehta',    'MALE',   '2004-03-12', 'aarav.mehta@student.edu',    '9876500001', '12 MG Road, Pune',         1, 2022, 'ACTIVE'),
(2,  9,  'CS2022002', 'Sneha',    'Joshi',    'FEMALE', '2004-07-25', 'sneha.joshi@student.edu',    '9876500002', '34 FC Road, Pune',          1, 2022, 'ACTIVE'),
(3,  10, 'CS2022003', 'Rohit',    'Desai',    'MALE',   '2003-11-05', 'rohit.desai@student.edu',    '9876500003', '56 Baner Road, Pune',       1, 2022, 'ACTIVE'),
(4,  11, 'CS2022004', 'Ananya',   'Singh',    'FEMALE', '2004-01-18', 'ananya.singh@student.edu',   '9876500004', '78 Koregaon Park, Pune',    1, 2022, 'ACTIVE'),
(5,  12, 'CS2022005', 'Karthik',  'Rao',      'MALE',   '2003-09-30', 'karthik.rao@student.edu',    '9876500005', '90 Viman Nagar, Pune',      1, 2022, 'ACTIVE'),
(6,  13, 'EE2022001', 'Divya',    'Nair',     'FEMALE', '2004-04-14', 'divya.nair@student.edu',     '9876500006', '11 Aundh Road, Pune',       2, 2022, 'ACTIVE'),
(7,  14, 'EE2022002', 'Aditya',   'Kulkarni', 'MALE',   '2004-06-20', 'aditya.kulkarni@student.edu','9876500007', '22 Deccan Gymkhana, Pune',  2, 2022, 'ACTIVE'),
(8,  15, 'BBA2023001','Ishaan',   'Verma',    'MALE',   '2005-02-08', 'ishaan.verma@student.edu',   '9876500008', '33 Camp Area, Pune',        4, 2023, 'ACTIVE'),
(9,  16, 'BBA2023002','Pooja',    'Gupta',    'FEMALE', '2005-05-22', 'pooja.gupta@student.edu',    '9876500009', '44 Hadapsar, Pune',         4, 2023, 'ACTIVE'),
(10, 17, 'CS2021001', 'Vikram',   'Bose',     'MALE',   '2003-08-15', 'vikram.bose@student.edu',    '9876500010', '55 Hinjewadi, Pune',        1, 2021, 'INACTIVE');

-- ==============================
-- STUDENT_SUBJECT (ENROLLMENT)
-- ==============================
INSERT INTO Student_Subject (student_id, subject_id, semester) VALUES
-- CS 2022 batch Sem 1
(1, 1, 1),(1, 2, 1),(1, 3, 1),(1, 4, 1),
(2, 1, 1),(2, 2, 1),(2, 3, 1),(2, 4, 1),
(3, 1, 1),(3, 2, 1),(3, 3, 1),(3, 4, 1),
(4, 1, 1),(4, 2, 1),(4, 3, 1),(4, 4, 1),
(5, 1, 1),(5, 2, 1),(5, 3, 1),(5, 4, 1),
-- CS 2022 batch Sem 2
(1, 5, 2),(1, 6, 2),(1, 7, 2),(1, 8, 2),
(2, 5, 2),(2, 6, 2),(2, 7, 2),(2, 8, 2),
(3, 5, 2),(3, 6, 2),(3, 7, 2),(3, 8, 2),
-- EE 2022 batch Sem 1
(6, 13, 1),(6, 14, 1),
(7, 13, 1),(7, 14, 1),
-- BBA 2023 batch Sem 1
(8, 17, 1),(8, 18, 1),
(9, 17, 1),(9, 18, 1);

-- ==============================
-- FACULTY_SUBJECT
-- ==============================
INSERT INTO Faculty_Subject (faculty_id, subject_id, semester) VALUES
(1, 1, 1),(1, 5, 2),(1, 9, 3),
(2, 2, 1),(2, 6, 2),(2, 11, 4),
(2, 21, 1),(2, 22, 1),
(3, 13, 1),(3, 14, 1),(3, 15, 2),(3, 16, 2),
(4, 17, 1),(4, 18, 1),(4, 19, 2),(4, 20, 2),
(1, 3, 1),(1, 7, 2),(1, 8, 2),(1, 10, 3);

-- ==============================
-- CLASSROOM
-- ==============================
INSERT INTO Classroom (room_id, building, capacity) VALUES
(1, 'Block A - Room 101', 60),
(2, 'Block A - Room 102', 60),
(3, 'Block B - Room 201', 50),
(4, 'Block B - Lab 202',  30),
(5, 'Block C - Room 301', 70),
(6, 'Block D - Room 401', 80),
(7, 'Block A - Seminar Hall', 120);

-- ==============================
-- TIMETABLE
-- ==============================
INSERT INTO Timetable (timetable_id, subject_id, faculty_id, room_id, day, start_time, end_time) VALUES
(1,  1,  1, 1, 'MONDAY',    '09:00:00', '10:00:00'),
(2,  2,  2, 2, 'MONDAY',    '10:00:00', '11:00:00'),
(3,  3,  1, 1, 'TUESDAY',   '09:00:00', '10:00:00'),
(4,  4,  1, 1, 'WEDNESDAY', '09:00:00', '10:00:00'),
(5,  6,  2, 2, 'THURSDAY',  '10:00:00', '11:00:00'),
(6,  7,  1, 1, 'FRIDAY',    '09:00:00', '10:00:00'),
(7,  13, 3, 3, 'MONDAY',    '11:00:00', '12:00:00'),
(8,  14, 3, 4, 'WEDNESDAY', '11:00:00', '12:00:00'),
(9,  17, 4, 6, 'TUESDAY',   '10:00:00', '11:00:00'),
(10, 18, 4, 6, 'THURSDAY',  '09:00:00', '10:00:00'),
(11, 9,  1, 7, 'FRIDAY',    '11:00:00', '12:00:00'),
(12, 10, 1, 1, 'SATURDAY',  '09:00:00', '10:00:00');

-- ==============================
-- ATTENDANCE
-- Approx 75-90% for most students, 1 student below 75% to test defaulters view
-- ==============================
INSERT INTO Attendance (student_id, subject_id, faculty_id, date, status) VALUES
-- Student 1 (Aarav) - Subject 2 (Programming in C) - ~90%
(1, 2, 2, '2024-08-05', 'PRESENT'),
(1, 2, 2, '2024-08-12', 'PRESENT'),
(1, 2, 2, '2024-08-19', 'PRESENT'),
(1, 2, 2, '2024-08-26', 'ABSENT'),
(1, 2, 2, '2024-09-02', 'PRESENT'),
(1, 2, 2, '2024-09-09', 'PRESENT'),
(1, 2, 2, '2024-09-16', 'PRESENT'),
(1, 2, 2, '2024-09-23', 'PRESENT'),
(1, 2, 2, '2024-09-30', 'PRESENT'),
(1, 2, 2, '2024-10-07', 'PRESENT'),
-- Student 2 (Sneha) - Subject 2 - ~80%
(2, 2, 2, '2024-08-05', 'PRESENT'),
(2, 2, 2, '2024-08-12', 'ABSENT'),
(2, 2, 2, '2024-08-19', 'PRESENT'),
(2, 2, 2, '2024-08-26', 'PRESENT'),
(2, 2, 2, '2024-09-02', 'ABSENT'),
(2, 2, 2, '2024-09-09', 'PRESENT'),
(2, 2, 2, '2024-09-16', 'PRESENT'),
(2, 2, 2, '2024-09-23', 'PRESENT'),
(2, 2, 2, '2024-09-30', 'ABSENT'),
(2, 2, 2, '2024-10-07', 'PRESENT'),
-- Student 3 (Rohit) - Subject 2 - DEFAULTER (50%) to test Attendance_Defaulters view
(3, 2, 2, '2024-08-05', 'ABSENT'),
(3, 2, 2, '2024-08-12', 'ABSENT'),
(3, 2, 2, '2024-08-19', 'PRESENT'),
(3, 2, 2, '2024-08-26', 'ABSENT'),
(3, 2, 2, '2024-09-02', 'PRESENT'),
(3, 2, 2, '2024-09-09', 'ABSENT'),
(3, 2, 2, '2024-09-16', 'ABSENT'),
(3, 2, 2, '2024-09-23', 'PRESENT'),
(3, 2, 2, '2024-09-30', 'ABSENT'),
(3, 2, 2, '2024-10-07', 'ABSENT'),
-- Student 4 & 5 - Subject 1 (Eng Math I)
(4, 1, 1, '2024-08-05', 'PRESENT'),
(4, 1, 1, '2024-08-12', 'PRESENT'),
(4, 1, 1, '2024-08-19', 'ABSENT'),
(4, 1, 1, '2024-08-26', 'PRESENT'),
(4, 1, 1, '2024-09-02', 'PRESENT'),
(4, 1, 1, '2024-09-09', 'PRESENT'),
(4, 1, 1, '2024-09-16', 'PRESENT'),
(4, 1, 1, '2024-09-23', 'PRESENT'),
(5, 1, 1, '2024-08-05', 'PRESENT'),
(5, 1, 1, '2024-08-12', 'PRESENT'),
(5, 1, 1, '2024-08-19', 'PRESENT'),
(5, 1, 1, '2024-08-26', 'PRESENT'),
(5, 1, 1, '2024-09-02', 'ABSENT'),
(5, 1, 1, '2024-09-09', 'PRESENT'),
(5, 1, 1, '2024-09-16', 'PRESENT'),
(5, 1, 1, '2024-09-23', 'PRESENT'),
-- EE Students
(6, 13, 3, '2024-08-06', 'PRESENT'),
(6, 13, 3, '2024-08-13', 'PRESENT'),
(6, 13, 3, '2024-08-20', 'ABSENT'),
(6, 13, 3, '2024-08-27', 'PRESENT'),
(6, 13, 3, '2024-09-03', 'PRESENT'),
(7, 13, 3, '2024-08-06', 'PRESENT'),
(7, 13, 3, '2024-08-13', 'ABSENT'),
(7, 13, 3, '2024-08-20', 'PRESENT'),
(7, 13, 3, '2024-08-27', 'PRESENT'),
(7, 13, 3, '2024-09-03', 'PRESENT'),
-- BBA Students
(8, 17, 4, '2024-08-07', 'PRESENT'),
(8, 17, 4, '2024-08-14', 'PRESENT'),
(8, 17, 4, '2024-08-21', 'PRESENT'),
(8, 17, 4, '2024-08-28', 'ABSENT'),
(8, 17, 4, '2024-09-04', 'PRESENT'),
(9, 17, 4, '2024-08-07', 'ABSENT'),
(9, 17, 4, '2024-08-14', 'PRESENT'),
(9, 17, 4, '2024-08-21', 'PRESENT'),
(9, 17, 4, '2024-08-28', 'PRESENT'),
(9, 17, 4, '2024-09-04', 'PRESENT');

-- ==============================
-- EXAM
-- ==============================
INSERT INTO Exam (exam_id, subject_id, exam_type, exam_date, max_marks) VALUES
(1,  2,  'QUIZ',    '2024-09-10', 20),
(2,  2,  'MIDTERM', '2024-10-15', 50),
(3,  2,  'FINAL',   '2024-12-10', 100),
(4,  1,  'MIDTERM', '2024-10-14', 50),
(5,  1,  'FINAL',   '2024-12-09', 100),
(6,  6,  'MIDTERM', '2024-10-16', 50),
(7,  6,  'FINAL',   '2024-12-11', 100),
(8,  13, 'MIDTERM', '2024-10-15', 50),
(9,  13, 'FINAL',   '2024-12-10', 100),
(10, 17, 'MIDTERM', '2024-10-17', 50),
(11, 17, 'FINAL',   '2024-12-12', 100),
(12, 9,  'MIDTERM', '2024-10-18', 50),
(13, 9,  'FINAL',   '2024-12-13', 100);

-- ==============================
-- GRADE
-- ==============================
INSERT INTO Grade (grade_id, grade_name, min_marks, max_marks) VALUES
(1, 'O',   90, 100),
(2, 'A+',  80, 89),
(3, 'A',   70, 79),
(4, 'B+',  60, 69),
(5, 'B',   50, 59),
(6, 'C',   40, 49),
(7, 'F',   0,  39);

-- ==============================
-- RESULT
-- ==============================
INSERT INTO Result (result_id, exam_id, student_id, marks_obtained, grade_id) VALUES
-- Quiz: Subject 2 (Programming in C)
(1, 1, 1, 18, 1),
(2, 1, 2, 15, 3),
(3, 1, 3, 10, 6),
(4, 1, 4, 17, 2),
(5, 1, 5, 16, 2),
-- Midterm: Subject 2
(6,  2, 1, 45, 2),
(7,  2, 2, 38, 5),
(8,  2, 3, 22, 7),
(9,  2, 4, 43, 2),
(10, 2, 5, 40, 3),
-- Final: Subject 2
(11, 3, 1, 88, 2),
(12, 3, 2, 72, 3),
(13, 3, 3, 45, 6),
(14, 3, 4, 91, 1),
(15, 3, 5, 83, 2),
-- Midterm: Subject 1 (Eng Math I)
(16, 4, 1, 40, 3),
(17, 4, 2, 44, 2),
(18, 4, 3, 30, 7),
(19, 4, 4, 48, 2),
(20, 4, 5, 35, 5),
-- Final: Subject 1
(21, 5, 1, 75, 3),
(22, 5, 2, 82, 2),
(23, 5, 3, 50, 5),
(24, 5, 4, 90, 1),
(25, 5, 5, 68, 4),
-- Midterm: Subject 6 (Data Structures)
(26, 6, 1, 42, 3),
(27, 6, 2, 46, 2),
(28, 6, 3, 28, 7),
-- Midterm: Subject 13 (Circuit Theory)
(29, 8, 6, 41, 3),
(30, 8, 7, 38, 5),
-- Midterm: Subject 17 (Principles of Management)
(31, 10, 8, 44, 2),
(32, 10, 9, 39, 4);

-- ==============================
-- FEES (Structure)
-- ==============================
INSERT INTO Fees (fee_id, course_id, semester, amount) VALUES
(1, 1, 1, 50000.00),
(2, 1, 2, 50000.00),
(3, 1, 3, 55000.00),
(4, 1, 4, 55000.00),
(5, 2, 1, 52000.00),
(6, 2, 2, 52000.00),
(7, 4, 1, 40000.00),
(8, 4, 2, 40000.00),
(9, 5, 1, 75000.00),
(10,5, 2, 75000.00);

-- ==============================
-- STUDENT_FEES (Tracking)
-- ==============================
INSERT INTO Student_Fees (student_fee_id, student_id, fee_id, paid_amount, status) VALUES
(1,  1, 1, 50000.00, 'PAID'),
(2,  2, 1, 50000.00, 'PAID'),
(3,  3, 1, 30000.00, 'PENDING'),
(4,  4, 1, 50000.00, 'PAID'),
(5,  5, 1, 50000.00, 'PAID'),
(6,  1, 2, 50000.00, 'PAID'),
(7,  2, 2, 25000.00, 'PENDING'),
(8,  3, 2, 0.00,     'PENDING'),
(9,  6, 5, 52000.00, 'PAID'),
(10, 7, 5, 52000.00, 'PAID'),
(11, 8, 7, 40000.00, 'PAID'),
(12, 9, 7, 20000.00, 'PENDING'),
-- Sem 3 fees for CS 2021 batch (student 10 is inactive but fees remain)
(13, 1, 3, 55000.00, 'PAID'),
(14, 2, 3, 10000.00, 'PENDING');

-- ==============================
-- PAYMENT
-- ==============================
INSERT INTO Payment (payment_id, student_id, student_fee_id, payment_date, amount_paid, payment_mode, status) VALUES
(1,  1, 1,  '2022-08-10', 50000.00, 'ONLINE',  'SUCCESS'),
(2,  2, 2,  '2022-08-11', 50000.00, 'CHEQUE',  'SUCCESS'),
(3,  3, 3,  '2022-08-15', 30000.00, 'CASH',    'SUCCESS'),
(4,  4, 4,  '2022-08-10', 50000.00, 'ONLINE',  'SUCCESS'),
(5,  5, 5,  '2022-08-12', 50000.00, 'ONLINE',  'SUCCESS'),
(6,  1, 6,  '2023-01-08', 50000.00, 'ONLINE',  'SUCCESS'),
(7,  2, 7,  '2023-01-10', 25000.00, 'CASH',    'SUCCESS'),
(8,  6, 9,  '2022-08-10', 52000.00, 'ONLINE',  'SUCCESS'),
(9,  7, 10, '2022-08-11', 52000.00, 'CHEQUE',  'SUCCESS'),
(10, 8, 11, '2023-08-05', 40000.00, 'ONLINE',  'SUCCESS'),
(11, 9, 12, '2023-08-07', 20000.00, 'CASH',    'SUCCESS'),
(12, 1, 13, '2023-07-15', 55000.00, 'ONLINE',  'SUCCESS'),
(13, 2, 14, '2023-07-20', 10000.00, 'ONLINE',  'SUCCESS');

-- ==============================
-- NOTIFICATIONS
-- ==============================
INSERT INTO Notifications (notification_id, user_id, message, type, is_read) VALUES
(1,  8,  'Your attendance in Programming in C is above 90%. Keep it up!',           'ATTENDANCE',  TRUE),
(2,  10, 'Warning: Your attendance in Programming in C has dropped below 75%.',     'ATTENDANCE',  FALSE),
(3,  10, 'Fee payment of Rs.20000 is still pending for Semester 2.',                'FEES',        FALSE),
(4,  11, 'Your Result for Programming in C Final Exam has been published.',         'RESULT',      FALSE),
(5,  9,  'Fee payment of Rs.25000 is pending for Semester 2.',                      'FEES',        FALSE),
(6,  9,  'Your Result for Programming in C Final Exam has been published.',         'RESULT',      TRUE),
(7,  1,  'Timetable has been updated for Monday.',                                   'TIMETABLE',   TRUE),
(8,  3,  'New subject "Advanced Algorithms" has been assigned to you.',              'GENERAL',     FALSE),
(9,  16, 'Reminder: BBA Semester 1 exams start on 17th October.',                   'EXAM',        FALSE),
(10, 18, 'Payment of Rs.30000 received from student Rohit Desai (CS2022003).',      'PAYMENT',     TRUE);

-- ==============================
-- AUDIT_LOG
-- ==============================
INSERT INTO Audit_Log (log_id, user_id, action, timestamp) VALUES
(1,  1,  'Created new student: CS2022001 - Aarav Mehta',                            '2022-07-20 10:05:00'),
(2,  1,  'Created new student: CS2022002 - Sneha Joshi',                            '2022-07-20 10:10:00'),
(3,  1,  'Added subject enrollment for student CS2022001, Subject: Programming in C','2022-07-21 09:00:00'),
(4,  3,  'Marked attendance for Subject: Programming in C on 2024-08-05',           '2024-08-05 11:00:00'),
(5,  2,  'Published result for Exam ID 1 (Quiz - Programming in C)',                '2024-09-11 14:00:00'),
(6,  18, 'Recorded payment of Rs.50000 from student CS2022001 for Semester 1',      '2022-08-10 16:30:00'),
(7,  1,  'Created timetable entry: Programming in C - Monday 10:00',                '2022-07-25 09:00:00'),
(8,  4,  'Marked attendance for Subject: Principles of Management on 2024-08-07',   '2024-08-07 11:30:00'),
(9,  1,  'Deactivated student: CS2021001 - Vikram Bose',                            '2024-01-15 10:00:00'),
(10, 2,  'Updated grade for student CS2022004 in Final Exam - Programming in C',    '2024-12-11 15:00:00');

SET FOREIGN_KEY_CHECKS = 1;

-- =========================================
-- QUICK VERIFICATION QUERIES
-- =========================================
-- SELECT * FROM Student_Details;
-- SELECT * FROM Faculty_Details;
-- SELECT * FROM Attendance_Defaulters;
-- SELECT * FROM Student_Grades;
-- SELECT * FROM Fee_Status;
-- SELECT * FROM Daily_Timetable;