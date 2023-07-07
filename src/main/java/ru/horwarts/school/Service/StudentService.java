package ru.horwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.horwarts.school.Model.Student;
import ru.horwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentsRepository;

    public StudentService(StudentRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student create(Student student){
        return studentsRepository.save(student);
    }

    public Student getById (Long id){
        return studentsRepository.findById(id).get();
    }

    public List<Student> getAll(){
        return studentsRepository.findAll();
    }

    public Student edit(Student student){
        return studentsRepository.save(student);
    }
    public void removeById(Long id){
        studentsRepository.deleteById(id);
    }
    public List<Student> filterByAge (int age){
        return studentsRepository.getByAge(age);
    }
}
