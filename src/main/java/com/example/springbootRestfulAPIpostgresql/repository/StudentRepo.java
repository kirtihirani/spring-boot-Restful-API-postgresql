package com.example.springbootRestfulAPIpostgresql.repository;
import java.util.List;
import com.example.springbootRestfulAPIpostgresql.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
    List<Student>  findStudentsByCoursesId(Long courseId);
}
