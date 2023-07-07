package ru.horwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.horwarts.school.Model.Faculty;
import ru.horwarts.school.Model.Student;
import ru.horwarts.school.repository.FacultyRepository;
import ru.horwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void create (Faculty faculty){
        facultyRepository.save(faculty);
    }

    public Faculty getById (Long id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAll(){
        return facultyRepository.findAll();
    }

    public Faculty edit(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public void removeById(Long id){
        facultyRepository.deleteById(id);
    }
    public List<Faculty> filterByColor (String color){
        return facultyRepository.getFacultiesByColor(color);
    }
}
