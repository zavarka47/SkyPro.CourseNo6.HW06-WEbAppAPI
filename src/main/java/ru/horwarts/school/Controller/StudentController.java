package ru.horwarts.school.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.horwarts.school.Model.Student;
import ru.horwarts.school.Service.StudentService;

import java.util.Collection;
import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create (@RequestBody Student student){
        try {
            studentService.create(student);
            return ResponseEntity.ok(student);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getById (@PathVariable Long id){
        Student result = studentService.getById(id);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> getAll(){
        Collection<Student> result = studentService.getAll();
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Student> edit (@RequestBody Student student){
        Student result = studentService.edit(student);
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> remove (@PathVariable Long id){
        studentService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("filter/{age}")
    public ResponseEntity <List<Student>> filterByAge (@PathVariable int age){
        List <Student> result = studentService.filterByAge(age);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
