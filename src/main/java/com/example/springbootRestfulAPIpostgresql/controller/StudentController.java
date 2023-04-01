package com.example.springbootRestfulAPIpostgresql.controller;

import com.example.springbootRestfulAPIpostgresql.bean.Student;
import com.example.springbootRestfulAPIpostgresql.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepo stuRepo;

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return stuRepo.findAll();
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable long id){
        return stuRepo.findById(id);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        Student stu = stuRepo.save(new Student(student.getId(), student.getName(), student.getAge()));
        return stu;
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student)throws Exception{
        Student stu = stuRepo.findById(id).orElseThrow(() -> new Exception("Student with this id is not found"));
        stu.setName(student.getName());
        stu.setAge(student.getAge());
        return stu;
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        stuRepo.deleteById(id);
        return new ResponseEntity<>("Student Deleted", HttpStatus.NO_CONTENT);
    }

}
