package ru.horwarts.school.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.horwarts.school.Model.Faculty;
import ru.horwarts.school.Model.Student;
import ru.horwarts.school.Service.HouseService;
import ru.horwarts.school.Service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private HouseService houseService;

    public FacultyController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<Faculty> create (@RequestBody Faculty faculty){
        try {
            houseService.create(faculty);
            return ResponseEntity.ok(faculty);
        } catch (RuntimeException r){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getById (@PathVariable Long id){
        Faculty result = houseService.getById(id);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAll(){
        Collection<Faculty> result = houseService.getAll();
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Faculty> edit (@RequestBody Faculty faculty){
        Faculty result = houseService.edit(faculty);
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> remove (@PathVariable Long id){
        houseService.removeById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("filter/{color}")
    public ResponseEntity<List<Faculty>> filterByColor (@PathVariable String color){
        List<Faculty> result = houseService.filterByColor(color);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
