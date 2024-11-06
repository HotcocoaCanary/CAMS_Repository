package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.back.common.CourseStats;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "student_course")
public class StudentCourse {
    @EmbeddedId
    private StudentCourseId id;

    @MapsId("studentID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StudentID", nullable = false)
    private User studentID;

    @Column(name = "Score", precision = 5, scale = 2)
    private BigDecimal score;

    @NotNull
    @Column(name = "Credit", nullable = false)
    private Integer credit;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'REQUIRED'")
    @Lob
    @Column(name = "Stats", nullable = false)
    private CourseStats stats;

}