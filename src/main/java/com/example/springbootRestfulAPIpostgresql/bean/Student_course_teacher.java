package com.example.springbootRestfulAPIpostgresql.bean;

import jakarta.persistence.*;

@Entity
@Table
public class Student_course_teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long studentId;
    private long teacherId;
    private long  courseId;

    public Student_course_teacher( long studentId, long teacherId, long courseId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public Student_course_teacher() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
