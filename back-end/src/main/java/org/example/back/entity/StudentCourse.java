package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.back.common.CourseStats;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "student_course")
@AllArgsConstructor
@NoArgsConstructor
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
    private Double credit;

    @NotNull
    @ColumnDefault("'REQUIRED'")
    @Enumerated(EnumType.STRING)
    @Lob
    @Column(name = "Stats", nullable = false)
    private CourseStats stats;

}