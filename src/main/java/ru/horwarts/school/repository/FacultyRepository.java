package ru.horwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.horwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository <Faculty, Long> {
List<Faculty> getFacultiesByFacultyColor(String color);
List<Faculty> getFacultiesByFacultyColorIgnoreCase(String color);



}
