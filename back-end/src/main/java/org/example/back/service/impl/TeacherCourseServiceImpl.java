package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.common.Term;
import org.example.back.entity.ComprehensiveEvaluationId;
import org.example.back.entity.StudentCourse;
import org.example.back.entity.User;
import org.example.back.repository.ComprehensiveEvaluationRepository;
import org.example.back.repository.StudentCourseRepository;
import org.example.back.repository.StudentRepository;
import org.example.back.repository.UserRepository;
import org.example.back.service.TeacherCourseService;
import org.example.back.util.CourseExcelList;
import org.example.back.util.OtherCourseExcelList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Canary
 * @version 1.0.0
 * @title Coue
 * @description <TODO description class purpose>
 * @creat 2024/11/8 下午10:32
 **/
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Resource
    private StudentCourseRepository studentCourseRepository;
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ComprehensiveEvaluationRepository comprehensiveEvaluationRepository;

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
}
