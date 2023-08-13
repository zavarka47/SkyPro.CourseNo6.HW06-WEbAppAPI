-- liquibase formatted sql

-- changeset zavarka:1

CREATE TABLE student (
    student_id    bigserial not null primary key ,
    student_age   integer not null,
    student_name  varchar(255),
    faculty_id    bigint references faculty
);

-- changeset zavarka:2
ALTER TABLE student ADD CONSTRAINT age_constraint CHECK ( student_age >= 16 )
