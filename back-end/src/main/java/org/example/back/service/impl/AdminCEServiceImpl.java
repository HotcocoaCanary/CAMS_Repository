package org.example.back.service.impl;

import org.example.back.entity.ComprehensiveEvaluation;
import org.example.back.entity.ComprehensiveEvaluationId;
import org.example.back.repository.ComprehensiveEvaluationRepository;
import org.example.back.service.AdminCEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 实现AdminCEService接口，完成具体的综合评价管理操作逻辑
@Service
public  class AdminCEServiceImpl implements AdminCEService {


    @Autowired
    private ComprehensiveEvaluationRepository comprehensiveEvaluationRepository;

    // 添加综合评价信息的实现方法
    @Override
    public ComprehensiveEvaluation addComprehensiveEvaluation(ComprehensiveEvaluation comprehensiveEvaluation) {
        return comprehensiveEvaluationRepository.save(comprehensiveEvaluation);
    }

    @Override
    public void deleteComprehensiveEvaluation(ComprehensiveEvaluation id) {

    }

    // 更新综合评价信息的实现方法
    @Override
    public ComprehensiveEvaluation updateComprehensiveEvaluation(ComprehensiveEvaluation comprehensiveEvaluation) {
        return null;
    }

    @Override
    public ComprehensiveEvaluation findComprehensiveEvaluationById(ComprehensiveEvaluation id) {
        return null;
    }
}