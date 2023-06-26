package ru.horwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.horwarts.school.Model.Faculty;
import ru.horwarts.school.Model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private Long id= 0L;

    public void create (Faculty faculty){
        if (!faculties.containsKey(faculty.getId())){
            faculty.setId(++id);
            faculties.put(faculty.getId(), faculty);
        } else {
         new RuntimeException();
        }

    }

    public Faculty getById (Long id){
        if (faculties.containsKey(id)){
            return faculties.get(id);
        }
        return null;
    }

    public Collection<Faculty> getAll(){
        return faculties.values();
    }

    public Faculty edit(Faculty faculty){
        if (faculties.containsKey(faculty.getId())){
            return faculties.put(faculty.getId(), faculty);
        }
        return null;
    }
    public Faculty removeById(Long id){
        if (faculties.containsKey(id)){
            return faculties.remove(id);
        }
        return null;
    }
    public List<Faculty> filterByColor (String color){
        return faculties.entrySet().stream()
                .filter(f -> f.getValue().getColor().contains(color))
                .map(f -> f.getValue())
                .collect(Collectors.toList());
    }
}
