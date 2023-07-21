package ru.horwarts.school.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;
    private String facultyName;
    private String facultyColor;
    @OneToMany (mappedBy = "faculty")
    private List<Student> students;

    public Faculty() {
    }

    public Faculty(String name, String color, List<Student> students) {
        this.facultyName = name;
        this.facultyColor = color;
        this.students = students;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String name) {
        this.facultyName = name;
    }

    public String getFacultyColor() {
        return facultyColor;
    }

    public void setFacultyColor(String color) {
        this.facultyColor = color;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(facultyId, faculty.facultyId) && Objects.equals(facultyName, faculty.facultyName) && Objects.equals(facultyColor, faculty.facultyColor) && Objects.equals(students, faculty.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, facultyName, facultyColor, students);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + facultyId +
                ", name='" + facultyName + '\'' +
                ", color='" + facultyColor + '\'' +
                ", students=" + students +
                '}';
    }
}

