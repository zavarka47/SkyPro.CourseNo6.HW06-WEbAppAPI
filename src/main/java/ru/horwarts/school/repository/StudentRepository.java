package ru.horwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.horwarts.school.model.Faculty;
import ru.horwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
List<Student> findByStudentAge (Integer age);
List<Student> findByStudentAgeBetween (Integer min, Integer max);
List<Student> findByFaculty (Faculty faculty);
}
