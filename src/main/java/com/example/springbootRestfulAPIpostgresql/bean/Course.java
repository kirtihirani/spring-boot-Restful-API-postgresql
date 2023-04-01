package com.example.springbootRestfulAPIpostgresql.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "Course")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "coursesTaught")
//    @JsonBackReference
    @JsonIgnore
    private Set<Teacher> teachers;
    public Set<Teacher> getTeachers() {return teachers;}


    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
            mappedBy = "courses"
    )
    @JsonIgnore
    private Set<Student> students = new HashSet<>();


}
