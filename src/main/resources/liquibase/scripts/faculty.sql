-- liquibase formatted sql

-- changeset zavarka:1
CREATE TABLE faculty (
    faculty_id    bigserial not null primary key,
    faculty_color varchar(255),
    faculty_name  varchar(255)
);

-- changeset zavarka:2
ALTER TABLE faculty ADD CONSTRAINT name_unique UNIQUE (faculty_name)
