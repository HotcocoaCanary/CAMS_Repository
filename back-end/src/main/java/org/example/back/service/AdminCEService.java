package org.example.back.service;

import org.example.back.entity.ComprehensiveEvaluation;

// 定义AdminCEService接口，用于规范综合评价相关的管理操作
public interface AdminCEService {

    // 添加ComprehensiveEvaluation
     ComprehensiveEvaluation addComprehensiveEvaluation(ComprehensiveEvaluation comprehensiveEvaluation);

    // 删除ComprehensiveEvaluation
    void deleteComprehensiveEvaluation(ComprehensiveEvaluation id);

    // 修改ComprehensiveEvaluation
     ComprehensiveEvaluation updateComprehensiveEvaluation(ComprehensiveEvaluation comprehensiveEvaluation);

    // 查询ComprehensiveEvaluation
    ComprehensiveEvaluation findComprehensiveEvaluationById(ComprehensiveEvaluation id);
}
