-- Bảng users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

-- Bảng classes
CREATE TABLE classes (
                         id SERIAL PRIMARY KEY,
                         class_name VARCHAR(255) NOT NULL,
                         teacher_id INT,
                         CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES users(id)
);

-- Bảng enrollments
CREATE TABLE enrollments (
                             id SERIAL PRIMARY KEY,
                             student_id INT,
                             class_id INT,
                             enrollment_date DATE,
                             grade NUMERIC,
                             CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES users(id),
                             CONSTRAINT fk_class FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- Dữ liệu mẫu
INSERT INTO users (password, email, name, role) VALUES
                                                    ('123456', 'teacher1@example.com', 'Teacher One', 'TEACHER'),
                                                    ('abcdef', 'student1@example.com', 'Student One', 'STUDENT'),
                                                    ('qwerty', 'student2@example.com', 'Student Two', 'STUDENT');

INSERT INTO classes (class_name, teacher_id) VALUES
                                                 ('Math 101', 1),
                                                 ('History 201', 1);

INSERT INTO enrollments (student_id, class_id, enrollment_date, grade) VALUES
                                                                           (2, 1, CURRENT_DATE, 8.5),
                                                                           (3, 2, CURRENT_DATE, 9.0);
