package org.example.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.back.common.CourseStats;
import org.example.back.common.Term;

import java.math.BigDecimal;

/**
 * @author Canary
 * @version 1.0.0
 * @creat 2024/11/5 下午4:39
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student_Course")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "ID")
    private Student student;

    @Column(name = "CourseID")
    private String courseID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Score")
    private BigDecimal score;

    @Column(name = "Credit")
    private byte credit;

    @Enumerated(EnumType.STRING)
    @Column(name = "Stats")
    private CourseStats stats;

    @Enumerated(EnumType.STRING)
    @Column(name = "Term")
    private Term term;
}
