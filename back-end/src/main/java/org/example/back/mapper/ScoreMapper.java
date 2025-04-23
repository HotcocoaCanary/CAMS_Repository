package org.example.back.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.back.entity.AllStudentIdsAndSemester;
import org.example.back.entity.CourseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScoreMapper {

    int initCourseSelection(
            @Param("studentId") String studentId,
            @Param("courseId") String courseId,
            @Param("semesterId")String semesterId
    );
    int setScoreToDb(
            @Param("studentId") String studentId,
            @Param("courseId") String courseId,
            @Param("semesterId")String semesterId,
            @Param("score")double score,
            @Param("status") String status
    );

    List<String> getRebuild(
            @Param("studentId") String studentId,
            @Param("courseId") String courseId
    );

    List<String> getAllStudentIds();

    List<CourseInfo> getScore1(@Param("studentId")String studentId,
                                @Param("semesterId")String semesterId);
    List<CourseInfo> getScore2(@Param("studentId")String studentId,
                                @Param("semesterId")String semesterId);
    List<CourseInfo> getScore3(@Param("studentId")String studentId,
                                @Param("semesterId")String semesterId);
    void computeAcademic_score1(
            @Param("studentId") String studentId,
            @Param("semesterId")String semesterId,
            @Param("academicScore")double academicScore
    );
    void computeAcademic_score2(
            @Param("studentId") String studentId,
            @Param("semesterId")String semesterId,
            @Param("academicScore")double academicScore
    );
    void computeAcademic_score3(
            @Param("studentId") String studentId,
            @Param("semesterId")String semesterId,
            @Param("academicScore")double academicScore
    );

    void initEvaluationResult(@Param("studentId") String studentId,
                              @Param("semesterId")String semesterId);

    List<AllStudentIdsAndSemester> getAllStudentIdsAndSemester(@Param("semesterId")String semesterId);
}
