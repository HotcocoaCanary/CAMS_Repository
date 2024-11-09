package org.example.back.controller;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Canary
 * @version 1.0.0
 * @title CourseController
 * @description <TODO description class purpose>
 * @creat 2024/11/9 下午3:28
 **/
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @PostMapping("/n")
    public Response<String> uploadCourse() {
        try {
            String path = "E:\\Code\\Canary\\CAMS_Repository\\back-end\\src\\main\\java\\org\\example\\back\\data\\data-test.xlsx";
            courseService.updateCourse(path);
            return Response.success();
        } catch (Exception e) {
            // 这里可以添加日志记录
            return Response.internalServerError();
        }
    }
}
