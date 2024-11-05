package org.example.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comprehensive_Evaluation")
public class ComprehensiveEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Term")
    private Term term;

    @Column(name = "Academic_Performance")
    private BigDecimal academicPerformance;

    @Column(name = "Morality")
    private BigDecimal morality;

    @Column(name = "Sports_Achievement")
    private BigDecimal sportsAchievement;

    @Column(name = "Other_Score1")
    private BigDecimal otherScore1;

    @Column(name = "Other_Score2")
    private BigDecimal otherScore2;

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "ID")
    private Student student;
}

