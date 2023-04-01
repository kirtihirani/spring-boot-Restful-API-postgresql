package com.example.springbootRestfulAPIpostgresql.controller;

import com.example.springbootRestfulAPIpostgresql.bean.Course;
import com.example.springbootRestfulAPIpostgresql.bean.Student;
import com.example.springbootRestfulAPIpostgresql.bean.Student_course_teacher;
import com.example.springbootRestfulAPIpostgresql.bean.Teacher;
import com.example.springbootRestfulAPIpostgresql.repository.CourseRepo;
import com.example.springbootRestfulAPIpostgresql.repository.StudentRepo;
import com.example.springbootRestfulAPIpostgresql.repository.StudentRecordRepo;
import com.example.springbootRestfulAPIpostgresql.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRecordController {

    @Autowired
    StudentRecordRepo repo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    CourseRepo courseRepo;

    @PostMapping("records/{sid}/{cid}/{tid}")
    public String createRecord(@PathVariable long sid,@PathVariable long cid,@PathVariable long tid){
        Course course = courseRepo.findById(cid).orElseThrow();
        Student student = studentRepo.findById(sid).orElseThrow();
        Teacher teacher = teacherRepo.findById(tid).orElseThrow();
        Student_course_teacher record = new Student_course_teacher();
        if(student.getCourses().contains(course) && teacher.getCoursesTaught().contains(course)){
            record.setCourseId(cid);
            record.setStudentId(sid);
            record.setTeacherId(tid);
             repo.save(record);
             return "record created";
        }else{
            return "teacher or student may not be registered to that couurse!";
        }

    }
    @GetMapping("/records")
    public List<Student_course_teacher> getAllRecords(){
        return repo.findAll();
    }
}
