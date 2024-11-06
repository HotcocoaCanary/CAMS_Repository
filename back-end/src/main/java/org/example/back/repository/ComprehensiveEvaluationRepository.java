package org.example.back.repository;

import org.example.back.entity.ComprehensiveEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComprehensiveEvaluationRepository extends JpaRepository<ComprehensiveEvaluation, Long> {
}

