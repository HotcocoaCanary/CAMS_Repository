package org.example.back.repository;

import jakarta.transaction.Transactional;
import org.example.back.common.CourseStats;
import org.example.back.entity.StudentCourse;
import org.example.back.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE StudentCourse sc " +
            "SET sc.score = :score, sc.stats = :stats, sc.credit = :credit " +
            "WHERE sc.studentID = :studentId")
    void updateStudentCourse(@Param("studentId") StudentCourseId studentID,
                             @Param("score") BigDecimal score,
                             @Param("stats") CourseStats stats,
                             @Param("credit") Double credit);
}
