package com.example.springbootRestfulAPIpostgresql.controller;

import com.example.springbootRestfulAPIpostgresql.bean.Teacher;
import com.example.springbootRestfulAPIpostgresql.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    TeacherRepo teacherRepo;
    @GetMapping
    public ResponseEntity<List<Teacher>> findAllTeachers(@RequestParam(required = false) String title) {
        List<Teacher> teachers = new ArrayList<Teacher>();

        if (title == null)
            teacherRepo.findAll().forEach(teachers::add);


        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public Optional<Teacher> findTeacherById(@PathVariable Long teacherId){
        return teacherRepo.findById(teacherId);
    }
    @GetMapping("/find/{name}")
    public List<Teacher> findTeacherByName(@PathVariable String name){
        return teacherRepo.findByNameContaining(name);
    }

    @PostMapping
    public Teacher teacherSaveWithCourse(@RequestBody Teacher teacher){
        return teacherRepo.save((teacher));
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teacherRepo.deleteById(id);
        return "Teacher deleted";
    }


}
