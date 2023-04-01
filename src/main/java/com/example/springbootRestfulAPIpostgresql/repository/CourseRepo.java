package com.example.springbootRestfulAPIpostgresql.repository;

import com.example.springbootRestfulAPIpostgresql.bean.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepo extends JpaRepository<Course,Long> {
    List<Course> findCoursesByTeachersId(Long teacherId);
}
