package com.example.springbootRestfulAPIpostgresql.controller;

import com.example.springbootRestfulAPIpostgresql.bean.Course;
import com.example.springbootRestfulAPIpostgresql.bean.Student;
import com.example.springbootRestfulAPIpostgresql.bean.Teacher;
import com.example.springbootRestfulAPIpostgresql.repository.CourseRepo;
import com.example.springbootRestfulAPIpostgresql.repository.StudentRepo;
import com.example.springbootRestfulAPIpostgresql.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class CoursesController {
    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StudentRepo stRepo;

    @GetMapping("/courses")
    public List<Course> findAllCourses(){
        return courseRepo.findAll();
    }

    @GetMapping("/teachers/{teacherId}/courses")
    public List<Course> getAllCoursesByTeacherId(@PathVariable Long teacherId) throws Exception {
        if(!teacherRepo.existsById(teacherId)){
            throw new Exception("Not found Courses with Teacher id = " + teacherId);
        }
        List courses = courseRepo.findCoursesByTeachersId(teacherId);
        return courses;
    }

//    @GetMapping("/courses/{courseId}/teachers")
//        public List<Teacher> getAllTeachersByCourseId(@PathVariable Long courseId) throws Exception {
//            if(!courseRepo.existsById(courseId)){
//                throw new Exception("Not found Teachers with course id ="+ courseId);
//            }
//            List<Teacher> teachers = teacherRepo.findTeachersByCourseId(courseId);
//            return teachers;
//        }

        @DeleteMapping("/teachers/{teacherId}/courses/{courseId}")
        public void deleteCourseFromTeacher(@PathVariable Long teacherId, @PathVariable Long courseId)throws Exception{
            Teacher teacher = teacherRepo.findById(teacherId).orElseThrow(()-> new Exception("not found teacher with this id"));
            teacher.removeCourse(courseId);
            teacherRepo.save(teacher);

        }
        @PostMapping("/courses")
        public Course createCourse(@RequestBody Course course){
            return courseRepo.save(course);
        }

        @PutMapping("/courses/{id}")
        public void updateCourse(@PathVariable Long id , @RequestBody Course course){
            courseRepo.save(course);
        }

        @DeleteMapping("/courses/{id}")
        public void deleteCourse(@PathVariable Long id){
        courseRepo.deleteById(id);
        }

        @PostMapping("/teachers/{id}/course")
        public Course addCourseToTeacher(@PathVariable Long id, @RequestBody Course course )throws Exception{
            Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new Exception());
            long courseid = course.getId();
            if(courseid!= 0L){
                Course  _course = courseRepo.findById(courseid).orElseThrow(() -> new Exception());
                teacher.addCourse(course);
                return _course;
            }

            teacher.addCourse((course));
            return courseRepo.save(course);

        }

        @PostMapping("/students/{studentid}/courses")
        public Course addCourseToStudent(@PathVariable Long studentid, @RequestBody Course courseRequest)throws Exception{
            Course course = stRepo.findById(studentid).map(student -> {
                long courseId = courseRequest.getId();

                // tag is existed
                Course _course;
                if (courseId != 0L) {
                    try {
                         _course = courseRepo.findById(courseId).orElseThrow(() -> new Exception());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    student.addCourse(_course);
                    stRepo.save(student);
                    return _course;
                }

                // add and create new Tag
                student.addCourse(courseRequest);
                return courseRepo.save(courseRequest);
            }).orElseThrow(() -> new Exception("Not found Student with id = " + studentid));

            return course;
        }

        @DeleteMapping("/students/{sid}/courses/{cid}")
        public void deleteCourseFromStudent(@PathVariable long sid, @PathVariable long cid)throws Exception{
            Student stu = stRepo.findById(sid).orElseThrow(() -> new Exception());
            stu.removeCourse(cid);
             stRepo.save(stu);
        }




}
