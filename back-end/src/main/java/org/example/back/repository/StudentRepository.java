package org.example.back.repository;

import jakarta.transaction.Transactional;
import org.example.back.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Canary
 * @version 1.0.0
 * @title TeacherRepository
 * @description <TODO description class purpose>
 * @creat 2024/11/7 上午11:53
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Students(studentId, class_name) VALUES(:studentId, :className)", nativeQuery = true)
    void addStudent(@Param("studentId") String studentId, @Param("className") String className);
}
