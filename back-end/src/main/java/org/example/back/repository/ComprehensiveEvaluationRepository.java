package org.example.back.repository;
import org.example.back.entity.ComprehensiveEvaluation;
import org.example.back.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ComprehensiveEvaluationRepository extends JpaRepository<ComprehensiveEvaluation, Long> {
    // 根据学生ID查找综合评价
    List<ComprehensiveEvaluation> findByStudentId(String studentId);

    // 根据学期查找综合评价
    List<ComprehensiveEvaluation> findByTerm(Term term);
}

