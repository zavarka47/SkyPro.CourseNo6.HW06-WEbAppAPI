-- liquibase formatter sql

-- changeset zavarka:1
CREATE TABLE avatar(
    id         bigserial not null primary key,
    date       oid,
    file_path  varchar(255) not null,
    file_size  bigint not null,
    media_type varchar(255) not null,
    student_id bigint references student
);



