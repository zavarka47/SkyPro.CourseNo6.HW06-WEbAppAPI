package ru.horwarts.school.model;


import javax.persistence.*;
import java.util.Objects;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long studentId;
    private String studentName;
    private int studentAge;
    @ManyToOne
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    public Student() {
    }

    public Student(String name, int age, Faculty faculty) {
        this.studentName = name;
        this.studentAge = age;
        this.faculty = faculty;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int age) {
        this.studentAge = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentAge == student.studentAge && Objects.equals(studentId, student.studentId) && Objects.equals(studentName, student.studentName) && Objects.equals(faculty, student.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, studentAge, faculty);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", name='" + studentName + '\'' +
                ", age=" + studentAge +
                ", faculty=" + faculty +
                '}';
    }
}
