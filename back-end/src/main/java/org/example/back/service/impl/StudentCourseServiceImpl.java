package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.common.Term;
import org.example.back.entity.Class;
import org.example.back.entity.ComprehensiveEvaluation;
import org.example.back.repository.ClassRepository;
import org.example.back.repository.ComprehensiveEvaluationRepository;
import org.example.back.service.StudentCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Canary
 * @version 1.0.0
 * @title StudentCourseServiceImpl
 * @description <TODO description class purpose>
 * @creat 2024/11/10 上午10:53
 **/
@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Resource
    private ComprehensiveEvaluationRepository comprehensiveEvaluationRepository;

    @Resource
    private ClassRepository classRepository;

    @Override
    public List<ComprehensiveEvaluation> getComprehensiveEvaluation(String className, Term term) {
        //<TODO>
        return List.of();
    }
}
