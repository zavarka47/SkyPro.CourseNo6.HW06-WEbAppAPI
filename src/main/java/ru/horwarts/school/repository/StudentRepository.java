package ru.horwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.horwarts.school.model.Faculty;
import ru.horwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
List<Student> findByStudentAge (Integer age);
List<Student> findByStudentAgeBetween (Integer min, Integer max);
//List<Student> findByFaculty (Faculty faculty);
@Query (value = "SELECT * FROM student WHERE faculty_id = faculty_id", nativeQuery = true)
List<Student> getStudentsByFacultyId (@Param("faculty_id") Long faculty_id);
}
