package ru.horwarts.school.DTO;

import ru.horwarts.school.model.Faculty;

import java.util.Objects;

public class FacultyDTO {
    private Long facultyId;
    private String facultyName;
    private String facultyColor;

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyColor() {
        return facultyColor;
    }

    public void setFacultyColor(String facultyColor) {
        this.facultyColor = facultyColor;
    }

    public FacultyDTO fromFaculty (Faculty faculty){
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setFacultyId(faculty.getFacultyId());
        facultyDTO.setFacultyName(faculty.getFacultyName());
        facultyDTO.setFacultyColor(faculty.getFacultyColor());
        return facultyDTO;
    }

    public Faculty toFaculty (FacultyDTO facultyDTO){
        Faculty faculty = new Faculty();
        faculty.setFacultyName(facultyDTO.getFacultyName());
        faculty.setFacultyColor(facultyDTO.getFacultyColor());
        return faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDTO that = (FacultyDTO) o;
        return Objects.equals(facultyName, that.facultyName) && Objects.equals(facultyColor, that.facultyColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyName, facultyColor);
    }

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "facultyName='" + facultyName + '\'' +
                ", facultyColor='" + facultyColor + '\'' +
                '}';
    }
}
