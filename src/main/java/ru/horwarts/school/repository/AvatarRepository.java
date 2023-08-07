package ru.horwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.horwarts.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {
    Optional<Avatar> findByStudent_StudentId (Long studentId);
}
