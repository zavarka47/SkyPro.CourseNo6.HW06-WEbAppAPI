package ru.horwarts.school.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.horwarts.school.model.Avatar;
import ru.horwarts.school.DTO.StudentDTO;
import ru.horwarts.school.service.AvatarService;
import ru.horwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
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
    public ResponseEntity<Collection<StudentDTO>> getAll(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        Collection<StudentDTO> result = studentService.getAll(pageNumber, pageSize);
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

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadAvatar (@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        if (file.getSize()> 1024*300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(id, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping ("{id}/preview")
    public ResponseEntity getPreview (@PathVariable Long id){
        Avatar avatar = avatarService.findByStudentId(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getDate().length);

        return ResponseEntity.status(200).headers(headers).body(avatar.getDate());
    }

    @GetMapping ("{id}/avatar")
    public void getAvatar (@PathVariable Long id, HttpServletResponse response) throws IOException {

        Avatar avatar = avatarService.findByStudentId(id);
        Path path = Path.of(avatar.getFilePath());

         try (InputStream is = Files.newInputStream(path);
              OutputStream os = response.getOutputStream();
              BufferedInputStream bis = new BufferedInputStream(is, 1024);
              BufferedOutputStream bos = new BufferedOutputStream(os, 1024)){

             response.setStatus(200);
             response.setContentType(avatar.getMediaType());
             response.setContentLength(Math.toIntExact(avatar.getFileSize()));
             bis.transferTo(bos);
         }
    }

   @GetMapping("/count")
    public ResponseEntity getQuantityStudents(){
        return ResponseEntity.ok(studentService.getQuantityStudents());
    }

    @GetMapping("/avg")
    public ResponseEntity getAverageAgeStudents(){
        return ResponseEntity.ok(studentService.getAverageAgeStudents());
    }


    @GetMapping("/youngest")
    public ResponseEntity getYoungestStudents(){
        return ResponseEntity.ok(studentService.getFiveYoungStudents());
    }

}
