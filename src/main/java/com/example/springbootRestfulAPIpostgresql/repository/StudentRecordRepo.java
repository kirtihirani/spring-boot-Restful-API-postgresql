package com.example.springbootRestfulAPIpostgresql.repository;

import com.example.springbootRestfulAPIpostgresql.bean.Student_course_teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRecordRepo extends JpaRepository<Student_course_teacher,Long> {
}
