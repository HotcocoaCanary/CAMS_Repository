package org.example.back.service.impl;

import org.example.back.entity.AllStudentIdsAndSemester;
import org.example.back.entity.CourseInfo;
import org.example.back.mapper.ScoreMapper;
import org.example.back.service.ScoreService;
import org.example.back.util.ExcelOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ExcelOp excelOp;


//    public ScoreServiceImpl(ScoreMapper scoreMapper, ExcelOp excelOp) {
//        this.scoreMapper = scoreMapper;
//        this.excelOp = excelOp;
//    }

    @Override
    public void initCourseSelection(String fileName) {
        excelOp.initCourseSelection(fileName);
    }

    @Override
    public void readScoreToDb(String fileName) {
        excelOp.readScore(fileName);
    }

    @Override
    public List<String> getAllStudentIds() {
        return scoreMapper.getAllStudentIds();
    }

    @Override
    public void computeEvaluationResult(String semesterId) {
        for (String studentId : scoreMapper.getAllStudentIds()) {
            for (int k = 0; k < 3; k++) {
                double totalCredits = 0;
                double weightedSum = 0;
                String semester = null;
                List<CourseInfo> courseInfo = null;
                if (k==0)courseInfo = scoreMapper.getScore1(studentId, semesterId);
                if (k==1)courseInfo = scoreMapper.getScore2(studentId, semesterId);
                if (k==2)courseInfo = scoreMapper.getScore3(studentId, semesterId);

                // 计算总学分和加权分数
                for (int i = 0; i < courseInfo.size(); i++) {
                    CourseInfo course = courseInfo.get(i);
                    if (course == null) {
                        System.err.println("跳过 null 课程信息");
                        continue;
                    }
                    totalCredits += course.getCredits();
                    weightedSum += course.getScore() * course.getCredits();

                    // 记录最后一个学期
                    if (i == courseInfo.size() - 1) {
                        semester = course.getSemester();
                    }
                }

                // 计算加权平均分
                double totalScore = weightedSum / totalCredits;

                if (k==0)scoreMapper.computeAcademic_score1(studentId, semester, totalScore * 0.6);
                if (k==1) scoreMapper.computeAcademic_score2(studentId, semester, totalScore * 0.6);
                if (k==2) scoreMapper.computeAcademic_score3(studentId, semester, totalScore * 0.6);
            }


        }
    }

    @Override
    public void initEvaluationResult(String semesterId) {
        for (AllStudentIdsAndSemester studentIdsAndSemester : scoreMapper.getAllStudentIdsAndSemester(semesterId)) {
            scoreMapper.initEvaluationResult(studentIdsAndSemester.getStudentId(),studentIdsAndSemester.getSemesterId());
        }
    }


}
