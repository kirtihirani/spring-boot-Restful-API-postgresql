package com.example.springbootRestfulAPIpostgresql.bean;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Student {
    @Id
    private long id;
    private String name;
    private String age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "student_courses",
        joinColumns = { @JoinColumn(name="student_id")},
        inverseJoinColumns = {@JoinColumn(name="course_id")}
    )
    private Set<Course> courses = new HashSet<>();

    public Student() {
    }

    public Student(long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        this.courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(long courseId){
        Course course = this.courses.stream().filter(c-> c.getId() == courseId).findFirst().orElse(null);
        if(course != null){
            this.courses.remove(course);
            course.getStudents().remove(this);
        }
    }
}
