package com.example.springbootRestfulAPIpostgresql.repository;

import com.example.springbootRestfulAPIpostgresql.bean.Student;
import com.example.springbootRestfulAPIpostgresql.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    List<Teacher> findByNameContaining(String name);

}
