package org.example.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.back.common.Term;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "comprehensive_evaluation", schema = "cams_repository_db")
public class ComprehensiveEvaluation {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("'FRESHMAN_FALL'")
    @Enumerated(EnumType.STRING)
    @Lob
    @Column(name = "Term", nullable = false)
    private Term term;

    @Column(name = "Academic_Performance", precision = 5, scale = 2)
    private BigDecimal academicPerformance;

    @Column(name = "Morality", precision = 5, scale = 2)
    private BigDecimal morality;

    @Column(name = "Sports_Achievement", precision = 5, scale = 2)
    private BigDecimal sportsAchievement;

    @Column(name = "Other_Score1", precision = 5, scale = 2)
    private BigDecimal otherScore1;

    @Column(name = "Other_Score2", precision = 5, scale = 2)
    private BigDecimal otherScore2;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StudentID", nullable = false)
    private User studentID;

}