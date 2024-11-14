package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.common.Term;
import org.example.back.entity.ComprehensiveEvaluation;
import org.example.back.entity.ComprehensiveEvaluationId;
import org.example.back.entity.StudentCourse;
import org.example.back.entity.User;
import org.example.back.repository.*;
import org.example.back.service.CourseService;
import org.example.back.util.CEExcelBuilder;
import org.example.back.util.CourseExcelList;
import org.example.back.util.OtherCourseExcelList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Canary
 * @version 1.0.0
 * @title CourseServiceImpl
 * @description 综测服务实现类
 * @creat 2024/11/8 下午10:32
 **/
@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private StudentCourseRepository studentCourseRepository;
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ComprehensiveEvaluationRepository comprehensiveEvaluationRepository;
    @Resource
    private ClassRepository classRepository;

    @Override
    public void updateCourse(String fileName) throws Exception {
        CourseExcelList courseExcelList = new CourseExcelList(fileName);
        List<User> userList = courseExcelList.getUserList();
        Map<String, String> studentMap = courseExcelList.getStudentMap();
        List<StudentCourse> studentCourseList = courseExcelList.getStudentCourseList();
        try{
            userRepository.saveAll(userList);
            for(String key : studentMap.keySet()){
                studentRepository.addStudent(key, studentMap.get(key));
            }
            studentCourseRepository.saveAll(studentCourseList);
        } catch (Exception ignored) {}

    }

    @Override
    public void updateOtherCourse(String fileName, Term term) throws Exception {
        OtherCourseExcelList otherCourseExcelList = new OtherCourseExcelList(fileName, term);
        Map<ComprehensiveEvaluationId, Double> moralityMap = otherCourseExcelList.getMoralityMap();
        Map<ComprehensiveEvaluationId, Double> otherScore1Map = otherCourseExcelList.getOtherScore1Map();
        Map<ComprehensiveEvaluationId, Double> otherScore2Map = otherCourseExcelList.getOtherScore2Map();

        try {
            for(ComprehensiveEvaluationId id : moralityMap.keySet()){
                comprehensiveEvaluationRepository.updateMorality(moralityMap.get(id), id.getStudentID(),term);
            }
            for(ComprehensiveEvaluationId id : otherScore1Map.keySet()){
                comprehensiveEvaluationRepository.updateOtherScore1(otherScore1Map.get(id), id.getStudentID(),term);
            }
            for(ComprehensiveEvaluationId id : otherScore2Map.keySet()){
                comprehensiveEvaluationRepository.updateOtherScore2(otherScore2Map.get(id), id.getStudentID(),term);
            }
        } catch (Exception ignored) {}
    }

    @Override
    public void updateCourse(StudentCourse studentCourse) {
        studentCourseRepository.updateStudentCourse(studentCourse.getId(), studentCourse.getScore(), studentCourse.getStats(), studentCourse.getCredit());
    }

    @Override
    public void deleteCourse(StudentCourse studentCourse) {
        studentCourseRepository.delete(studentCourse);
    }

    @Override
    public void insertCourse(StudentCourse studentCourse) {
        studentCourseRepository.save(studentCourse);
    }

    @Override
    public void updateOtherCourse(ComprehensiveEvaluation comprehensiveEvaluation) {
        comprehensiveEvaluationRepository.updateMorality(comprehensiveEvaluation.getMorality().doubleValue(),comprehensiveEvaluation.getId().getStudentID(), comprehensiveEvaluation.getId().getTerm());
        comprehensiveEvaluationRepository.updateOtherScore1(comprehensiveEvaluation.getOtherScore1().doubleValue(),comprehensiveEvaluation.getId().getStudentID(), comprehensiveEvaluation.getId().getTerm());
        comprehensiveEvaluationRepository.updateOtherScore2(comprehensiveEvaluation.getOtherScore2().doubleValue(),comprehensiveEvaluation.getId().getStudentID(), comprehensiveEvaluation.getId().getTerm());
    }

    @Override
    public void deleteOtherCourse(ComprehensiveEvaluation comprehensiveEvaluation) {
        comprehensiveEvaluationRepository.delete(comprehensiveEvaluation);
    }

    @Override
    public void insertOtherCourse(ComprehensiveEvaluation comprehensiveEvaluation) {
        comprehensiveEvaluationRepository.save(comprehensiveEvaluation);
    }

    @Override
    public CEExcelBuilder getComprehensiveEvaluation(User user, Term term) {
        // 直接查询指定班级和学期的综合评价
        String className = studentRepository.getClassNameByStudentId(user.getId());
        List<String> studentIdList = studentRepository.findAllStudentId(className);
        List<ComprehensiveEvaluation> comprehensiveEvaluations = comprehensiveEvaluationRepository.findAllCEByTerm(term)
                .stream()
                .filter(comprehensiveEvaluation -> studentIdList.contains(comprehensiveEvaluation.getId().getStudentID()))
                .toList();

        // 将评价按班级分组
        Map<String, List<ComprehensiveEvaluation>> map = Collections.singletonMap(className, comprehensiveEvaluations);

        // 使用构建器生成Excel工作簿
        return new CEExcelBuilder(map);
    }


    @Override
    public CEExcelBuilder getComprehensiveEvaluation(Term term) {
        List<String> classNameList = classRepository.findAllClassName();
        Map<String,List<ComprehensiveEvaluation>> map = new HashMap<>();
        for(String className : classNameList){
            List<String> studentIdList = studentRepository.findAllStudentId(className);
            List<ComprehensiveEvaluation> comprehensiveEvaluations = comprehensiveEvaluationRepository.findAllCEByTerm(term)
                    .stream()
                    .filter(comprehensiveEvaluation -> studentIdList.contains(comprehensiveEvaluation.getId().getStudentID()))
                    .toList();
            map.put(className, comprehensiveEvaluations);
        }
        return new CEExcelBuilder(map);
    }

    @Override
    public void closeWorkbook(CEExcelBuilder ceExcelBuilder){
        if (ceExcelBuilder!=null){
            try {
                ceExcelBuilder.closeWorkbook();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
