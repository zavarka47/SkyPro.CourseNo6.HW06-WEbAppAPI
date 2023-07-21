package ru.horwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.horwarts.school.DTO.FacultyDTO;
import ru.horwarts.school.service.HouseService;

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
    public ResponseEntity<FacultyDTO> create (@RequestBody FacultyDTO facultyDTO){
        try {
            houseService.create(facultyDTO);
            return ResponseEntity.ok(facultyDTO);
        } catch (RuntimeException r){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<FacultyDTO> getById (@PathVariable Long id){
        FacultyDTO result = houseService.getById(id);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> getAll(){
        Collection<FacultyDTO> result = houseService.getAll();
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<FacultyDTO> edit (@RequestBody FacultyDTO facultyDTO){
        FacultyDTO result = houseService.edit(facultyDTO);
        if (result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<FacultyDTO> remove (@PathVariable Long id){
        houseService.removeById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("filter/{color}")
    public ResponseEntity<List<FacultyDTO>> filterByColor (@PathVariable String color){

        List<FacultyDTO> result = houseService.filterByColor(color);
        List<FacultyDTO> resultIgnoreCase = houseService.filterByColorIgnoreCase(color);
        if (!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        if (result.isEmpty() && !resultIgnoreCase.isEmpty()){
            return ResponseEntity.ok(resultIgnoreCase);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("byStudentId/{id}")
    public ResponseEntity<FacultyDTO> getFacultyByStudentId (@PathVariable Long id){
        FacultyDTO result = houseService.getFacultyByStudentId(id);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
