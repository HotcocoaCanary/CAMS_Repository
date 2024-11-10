package org.example.back.service;

import org.example.back.common.Term;
import org.example.back.entity.ComprehensiveEvaluation;

import java.util.List;

/**
 * @author Canary
 * @version 1.0.0
 * @title StudentCourseService
 * @description <TODO description class purpose>
 * @creat 2024/11/10 上午10:52
 **/
public interface StudentCourseService {
    List<ComprehensiveEvaluation> getComprehensiveEvaluation(String className, Term term);
}
