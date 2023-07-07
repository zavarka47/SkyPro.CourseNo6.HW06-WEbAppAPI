package ru.horwarts.school.repository;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.horwarts.school.Model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
List<Student> getByAge (int age);
}
