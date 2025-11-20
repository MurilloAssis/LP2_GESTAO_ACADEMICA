CREATE DATABASE gerencia_notas;
USE gerencia_notas;

CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);



CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(120) NOT NULL,
    full_name VARCHAR(120) NOT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);



CREATE TABLE students (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    registration_code VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);



CREATE TABLE teachers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    employee_code VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);



CREATE TABLE terms (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);



CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(120) NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    teacher_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);


CREATE TABLE assignments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    course_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    weight DECIMAL(6,3) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES cursos (id)
);


CREATE TABLE enrollments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    term_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES students (id),
    FOREIGN KEY (course_id) REFERENCES cursos (id),
    FOREIGN KEY (term_id) REFERENCES terms (id)
);



CREATE TABLE grades (
    id BIGINT NOT NULL AUTO_INCREMENT,
    enrollment_id BIGINT NOT NULL,
    assignment_id BIGINT NOT NULL,
    value DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (enrollment_id) REFERENCES enrollments (id),
    FOREIGN KEY (assignment_id) REFERENCES assignments (id)
);