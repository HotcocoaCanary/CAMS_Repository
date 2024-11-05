package org.example.back.repository;

import org.example.back.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Canary
 * @version 1.0.0
 * @title asdf
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午4:45
 **/


@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    // 根据学生ID查找学生课程
    List<StudentCourse> findByStudentId(String studentId);
}