package org.example.back.controller;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.common.Term;
import org.example.back.service.TeacherCourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Canary
 * @version 1.0.0
 * @title TeacherCourseController
 * @description <TODO description class purpose>
 * @creat 2024/11/9 下午3:28
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherCourseController {
    @Resource
    private TeacherCourseService teacherCourseService;

    @PostMapping("/course")
    public Response<String> uploadCourse() {
        try {
            String path = "E:\\Code\\Canary\\CAMS_Repository\\back-end\\src\\main\\java\\org\\example\\back\\data\\data-test.xlsx";
            teacherCourseService.updateCourse(path);
            return Response.success();
        } catch (Exception e) {
            // 这里可以添加日志记录
            return Response.internalServerError();
        }
    }

    @PostMapping("/other")
    public Response<String> uploadOtherCourse() {
        try {
            String path = "E:\\Code\\Canary\\CAMS_Repository\\back-end\\src\\main\\java\\org\\example\\back\\data\\data-testo.xlsx";
            teacherCourseService.updateOtherCourse(path, Term.SOPHOMORE_SPRING);
            return Response.success();
        } catch (Exception e) {
            // 这里可以添加日志记录
            return Response.internalServerError();
        }
    }
}