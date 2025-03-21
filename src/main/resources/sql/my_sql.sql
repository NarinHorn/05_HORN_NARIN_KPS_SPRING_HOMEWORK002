CREATE DATABASE my_batis_course_enrollment;

CREATE TABLE instructors
(
    instructor_id   serial PRIMARY KEY,
    instructor_name varchar(100) NOT NULL,
    email           varchar(100) NOT NULL
);

CREATE TABLE courses
(
    course_id     serial PRIMARY KEY,
    course_name   varchar(100)                                                 NOT NULL,
    description   varchar(100)                                                 NOT NULL,
    instructor_id int REFERENCES instructors (instructor_id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE students
(
    student_id   serial PRIMARY KEY,
    student_name varchar(100) NOT NULL,
    email        varchar(100) NOT NULL,
    phone_number varchar(100) NOT NULL
);

CREATE TABLE student_course
(
    student_id int REFERENCES students (student_id) ON DELETE CASCADE NOT NULL,
    course_id  int REFERENCES courses (course_id) ON DELETE CASCADE   NOT NULL
);