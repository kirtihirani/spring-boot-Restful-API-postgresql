package com.example.springbootRestfulAPIpostgresql.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Teacher")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    private Long id;
    private String name;
    private String department;
    private int experience;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(name = "TEACHER_COURSE_TABLE",
    joinColumns = {
            @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id")
    }
    )
//    @JsonManagedReference

    private Set<Course> coursesTaught;

    public void addCourse(Course course){
        this.coursesTaught.add(course);
        course.getTeachers().add(this);
    }

    public void removeCourse(Long courseId){
        Course course = this.coursesTaught.stream().filter(t -> t.getId() == courseId).findFirst().orElse(null);
        if(course!=null){
            this.coursesTaught.remove(course);
            course.getTeachers().remove(this);
        }
    }
}
