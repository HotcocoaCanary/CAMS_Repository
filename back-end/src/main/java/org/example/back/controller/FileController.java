package org.example.back.controller;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.service.FileUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Canary
 * @version 1.0.0
 * @title FileController
 * @description <TODO description class purpose>
 * @creat 2024/11/8 下午10:47
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/course")
    public Response<String> uploadCourseFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return Response.badRequest("文件不能为空");
            }
            String result = fileUploadService.courseFileUpload(file);
            return Response.success(result);
        } catch (Exception e) {
            return Response.internalServerError();
        }
    }

    @PostMapping("/other")
    public Response<String> uploadOtherFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return Response.badRequest("文件不能为空");
            }
            String result = fileUploadService.otherCourseFileUpload(file);
            return Response.success(result);
        } catch (Exception e) {
            // 这里可以添加日志记录
            return Response.internalServerError();
        }
    }
}
