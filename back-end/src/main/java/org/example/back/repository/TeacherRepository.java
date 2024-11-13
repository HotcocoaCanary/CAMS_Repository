package org.example.back.repository;

import jakarta.transaction.Transactional;
import org.example.back.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Canary
 * @version 1.0.0
 * @title TeacherRepository
 * @description
 * @creat 2024/11/7 上午11:53
 **/
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teachers(TeacherID, Department) VALUES(:teacherId, :department)", nativeQuery = true)
    void addTeacher(@Param("teacherId") String teacherId, @Param("department") String department);

}
