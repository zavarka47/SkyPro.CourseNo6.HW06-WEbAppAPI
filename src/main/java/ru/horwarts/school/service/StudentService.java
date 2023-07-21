package ru.horwarts.school.service;

import org.springframework.stereotype.Service;
import ru.horwarts.school.DTO.StudentDTO;
import ru.horwarts.school.model.Student;
import ru.horwarts.school.repository.FacultyRepository;
import ru.horwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentsRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentsRepository, FacultyRepository facultyRepository) {
        this.studentsRepository = studentsRepository;
        this.facultyRepository = facultyRepository;
    }

    public void create(StudentDTO studentDTO){studentsRepository.save(toStudent(studentDTO));}


    public StudentDTO getById (Long id){
        return new StudentDTO().fromStudent(studentsRepository.findById(id).get());
    }

    public List<StudentDTO> getAll(){
        List <Student> students = studentsRepository.findAll();
        List <StudentDTO> dtos = new ArrayList<>();
        for (Student student :students) {
            dtos.add(new StudentDTO().fromStudent(student));
        }
        return dtos;
    }

    public StudentDTO edit(StudentDTO studentDTO){
        return new StudentDTO().fromStudent(studentsRepository.save(toStudent(studentDTO)));
    }
    public void removeById(Long id){
        studentsRepository.deleteById(id);
    }
    public List<StudentDTO> filterByAge (Integer age){
        List<Student> students = studentsRepository.findByStudentAge(age);
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student:students) {
            dtos.add(new StudentDTO().fromStudent(student));
        }
        return dtos;
    }
    public List<StudentDTO> filterByAgeBetween (Integer min, Integer max){
        List<Student> students = studentsRepository.findByStudentAgeBetween(min, max);
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student:students) {
            dtos.add(new StudentDTO().fromStudent(student));
        }
        return dtos;
    }

    public List<StudentDTO> getStudentsByFacultyId (Long facultyId){
        List<Student> students = studentsRepository.getStudentsByFacultyId(facultyId);
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student:students) {
            dtos.add(new StudentDTO().fromStudent(student));
        }
        return dtos;
    }

    public Student toStudent (StudentDTO studentDTO){
        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());
        student.setStudentAge(studentDTO.getStudentAge());
        student.setFaculty(facultyRepository.getById(studentDTO.getFacultyId()));
        return student;
    }


}
