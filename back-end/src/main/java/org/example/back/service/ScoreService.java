package org.example.back.service;

import java.util.List;

public interface ScoreService {
    void initCourseSelection(String fileName);
    void readScoreToDb(String fileName);
    List<String> getAllStudentIds();
    void computeEvaluationResult(String semesterId);

    void initEvaluationResult(String semesterId);
}
