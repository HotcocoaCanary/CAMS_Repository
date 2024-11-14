package org.example.back.controller.user;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.common.Term;
import org.example.back.service.CourseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Canary
 * @version 1.0.0
 * @title courseController
 * @description 上传综测控制类
 * @creat 2024/11/9 下午3:28
 **/
@RestController
@RequestMapping("/teacher/course")
public class courseController {

    @Resource
    private CourseService courseService;
    // 定义文件保存的路径
    private static final String UPLOAD_DIR = "src\\main\\java\\org\\example\\back\\data\\";

    @PostMapping("/intellectual")
    public Response<String> uploadCourse(@RequestParam("courseExcelFile") MultipartFile file) {
        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 构建新的文件名，确保文件名不重复
            String newFilename = generateUniqueFilename(originalFilename);
            // 构建完整的文件保存路径
            Path targetLocation = Paths.get(UPLOAD_DIR + newFilename);
            // 保存文件
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            file.getInputStream().close();

            courseService.updateCourse(UPLOAD_DIR+newFilename);

            return Response.success();
        } catch (Exception e) {
            // 这里可以添加日志记录
            return Response.internalServerError();
        }
    }

    @PostMapping("/other")
    public Response<String> uploadOtherCourse(@RequestParam("otherCourseExcelFile") MultipartFile file,
                                              @RequestParam("term") Term term) {
        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 构建新的文件名，确保文件名不重复
            String newFilename = generateUniqueFilename(originalFilename);
            // 构建完整的文件保存路径
            Path targetLocation = Paths.get(UPLOAD_DIR + newFilename);
            // 保存文件
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            file.getInputStream().close();

            courseService.updateOtherCourse(targetLocation.toString(), term);

            return Response.success();
        } catch (Exception e) {
            // 这里可以添加日志记录
            return Response.internalServerError();
        }
    }

    // 生成唯一文件名的方法
    private String generateUniqueFilename(String originalFilename) {
        String fileName = Paths.get(originalFilename).getFileName().toString();
        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }
        // 添加时间戳来确保文件名唯一
        String timestamp = String.valueOf(System.currentTimeMillis());
        return fileName + "_" + timestamp + fileExtension;
    }
}
