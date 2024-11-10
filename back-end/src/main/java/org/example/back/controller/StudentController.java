package org.example.back.controller;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.common.Term;
import org.example.back.common.request.StudentScoreRequest;
import org.example.back.entity.ComprehensiveEvaluation;
import org.example.back.service.StudentCourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Canary
 * @version 1.0.0
 * @title StudentController
 * @description <TODO description class purpose>
 * @creat 2024/11/9 下午11:17
 **/

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentCourseService studentCourseService;

    @PostMapping("/score")
    public Response<List<ComprehensiveEvaluation>> getScoreByClassAndTerm(@RequestBody StudentScoreRequest studentScoreRequest) {
        try{
            String className = studentScoreRequest.getClassName();
            Term term = studentScoreRequest.getTerm();
            List<ComprehensiveEvaluation> list = studentCourseService.getComprehensiveEvaluation(className, term);
            return Response.success(list);
        }catch (Exception e){
            return Response.internalServerError();
        }
    }
}
