package ru.horwarts.school.service;

import org.springframework.stereotype.Service;
import ru.horwarts.school.DTO.FacultyDTO;
import ru.horwarts.school.model.Faculty;
import ru.horwarts.school.repository.FacultyRepository;
import ru.horwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public HouseService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public void create (FacultyDTO facultyDTO){
        facultyRepository.save(facultyDTO.toFaculty(facultyDTO));
    }

    public FacultyDTO getById (Long id) {
        return new FacultyDTO().fromFaculty(facultyRepository.findById(id).get());
    }

    public List<FacultyDTO> getAll(){
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> dtos = new ArrayList<>();
        for (Faculty faculty:faculties) {
            dtos.add(new FacultyDTO().fromFaculty(faculty));
        }
        return dtos;
    }

    public FacultyDTO edit(FacultyDTO facultyDTO){
        return new FacultyDTO().fromFaculty(facultyRepository.save(facultyDTO.toFaculty(facultyDTO)));
    }
    public void removeById(Long id){
        facultyRepository.deleteById(id);
    }
    public List<FacultyDTO> filterByColor (String color){
        List <Faculty> faculties = facultyRepository.getFacultiesByFacultyColor(color);
        List <FacultyDTO>  dtos = new ArrayList<>();
        for (Faculty faculty:faculties) {
            dtos.add(new FacultyDTO().fromFaculty(faculty));
        }
        return dtos;
    }
    public List<FacultyDTO> filterByColorIgnoreCase (String color){
        List <Faculty> faculties = facultyRepository.getFacultiesByFacultyColor(color);
        List <FacultyDTO> dtos = new ArrayList<>();
        for (Faculty faculty:faculties) {
            dtos.add(new FacultyDTO().fromFaculty(faculty));
        }
        return dtos;
    }
    public FacultyDTO getFacultyByStudentId (Long studentId){
        return new FacultyDTO().fromFaculty(studentRepository.getById(studentId).getFaculty());
    }

}
