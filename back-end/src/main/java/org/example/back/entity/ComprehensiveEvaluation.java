package org.example.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "comprehensive_evaluation")
public class ComprehensiveEvaluation {
    @EmbeddedId
    private ComprehensiveEvaluationId id;

    @MapsId("studentID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StudentID", nullable = false)
    private User studentID;

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

}