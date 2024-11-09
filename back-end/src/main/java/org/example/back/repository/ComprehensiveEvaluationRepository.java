package org.example.back.repository;

import jakarta.transaction.Transactional;
import org.example.back.common.Term;
import org.example.back.entity.ComprehensiveEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ComprehensiveEvaluationRepository extends JpaRepository<ComprehensiveEvaluation, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ComprehensiveEvaluation ce " +
            "SET ce.morality = :morality " +
            "WHERE ce.id.studentID = :studentId AND ce.id.term = :term")
    void updateMorality(Double morality, String studentId, Term term);

    @Modifying
    @Transactional
    @Query("UPDATE ComprehensiveEvaluation ce " +
            "SET ce.otherScore1 = :otherScore1 " +
            "WHERE ce.id.studentID = :studentId AND ce.id.term = :term")
    void updateOtherScore1(Double otherScore1, String studentId, Term term);

    @Modifying
    @Transactional
    @Query("UPDATE ComprehensiveEvaluation ce " +
            "SET ce.otherScore2 = :otherScore2 " +
            "WHERE ce.id.studentID = :studentId AND ce.id.term = :term")
    void updateOtherScore2(Double otherScore2, String studentId, Term term);
}

