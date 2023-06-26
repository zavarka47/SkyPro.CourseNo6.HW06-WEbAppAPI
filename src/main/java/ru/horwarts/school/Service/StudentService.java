package ru.horwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.horwarts.school.Model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap <Long, Student> students = new HashMap<>();
    private Long id= 0L;

    public void create(Student student){
        if (!students.containsKey(student)){
            student.setId(++id);
            students.put(student.getId(), student);
        } else {
            new RuntimeException();
        }

    }

    public Student getById (Long id){
        if (students.containsKey(id)){
            return students.get(id);
        }
        return null;
    }

    public Collection<Student> getAll(){
        return students.values();
    }

    public Student edit(Student student){
        if (students.containsKey(student.getId())){
            return students.put(student.getId(), student);
        }
        return null;
    }
    public Student removeById(Long id){
        if (students.containsKey(id)){
            return students.remove(id);
        }
        return null;
    }
    public List<Student> filterByAge (int age){
        return students.
                entrySet().stream()
                .filter(s -> s.getValue().getAge()>=age)
                .map(s -> s.getValue())
                .collect(Collectors.toList());
    }
}
