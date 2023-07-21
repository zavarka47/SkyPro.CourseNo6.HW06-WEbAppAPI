package ru.horwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.horwarts.school.DTO.StudentDTO;
import ru.horwarts.school.model.Student;
import ru.horwarts.school.service.StudentService;

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
    public ResponseEntity<StudentDTO> create (@RequestBody StudentDTO studentDTO){
        try {
            studentService.create(studentDTO);
            return ResponseEntity.ok(studentDTO);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getById (@PathVariable Long id){
        StudentDTO result = studentService.getById(id);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getAll(){
        Collection<StudentDTO> result = studentService.getAll();
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<StudentDTO> edit (@RequestBody StudentDTO studentDTO){
        StudentDTO result = studentService.edit(studentDTO);
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<StudentDTO> remove (@PathVariable Long id){
        studentService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("filter/")
    public ResponseEntity <List<StudentDTO>> filterByAge (@RequestParam (required = false) Integer age,
                                                       @RequestParam (required = false) Integer min,
                                                       @RequestParam (required = false) Integer max){
        if (age!=null){
            return ResponseEntity.ok(studentService.filterByAge(age));
        }
        if (min!=null && max!=null){
            return ResponseEntity.ok(studentService.filterByAgeBetween(min, max));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("filter/{facultyId}")
    public ResponseEntity <List<StudentDTO>> filterByFacultyId (@PathVariable Long facultyId){
        List <StudentDTO> result = studentService.getStudentsByFacultyId(facultyId);
        if (!result.isEmpty()){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
