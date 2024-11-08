package org.example.back.service.impl;

import org.example.back.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author Canary
 * @version 1.0.0
 * @title FileUploadServiceImpl
 * @description <TODO description class purpose>
 * @creat 2024/11/8 下午10:54
 **/

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Path rootLocation = Paths.get("main/java/org/example/back/data");

    @Override
    public String courseFileUpload(MultipartFile file) throws IOException {
        // 保存文件到指定目录
        Files.copy(file.getInputStream(), this.rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        return "文件上传成功";
    }

    @Override
    public String otherCourseFileUpload(MultipartFile file) throws IOException {
        // 保存文件到指定目录
        Files.copy(file.getInputStream(), this.rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        return "文件上传成功";
    }
}
