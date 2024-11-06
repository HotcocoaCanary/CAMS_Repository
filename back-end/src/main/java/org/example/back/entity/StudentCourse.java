package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.back.common.CourseStats;
import org.example.back.common.Term;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "student_course", schema = "cams_repository_db")
public class StudentCourse {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StudentID", nullable = false)
    private User studentID;

    @Size(max = 50)
    @NotNull
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Score", precision = 5, scale = 2)
    private BigDecimal score;

    @NotNull
    @Column(name = "Credit", nullable = false)
    private Integer credit;

    @NotNull
    @ColumnDefault("'REQUIRED'")
    @Enumerated(EnumType.STRING)
    @Lob
    @Column(name = "Stats", nullable = false)
    private CourseStats stats;

    @NotNull
    @ColumnDefault("'FRESHMAN_FALL'")
    @Enumerated(EnumType.STRING)
    @Lob
    @Column(name = "Term", nullable = false)
    private Term term;

}