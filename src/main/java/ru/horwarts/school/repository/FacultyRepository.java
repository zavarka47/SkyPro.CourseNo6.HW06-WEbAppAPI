package ru.horwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.horwarts.school.Model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository <Faculty, Long> {
List<Faculty> getFacultiesByColor (String color);
}
