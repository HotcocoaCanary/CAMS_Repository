package org.example.back.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Canary
 * @version 1.0.0
 * @title FileUpdata
 * @description
 * @creat 2024/11/8 下午10:33
 **/
public interface FileUploadService {
    String courseFileUpload(MultipartFile fileUpload) throws IOException;
    String otherCourseFileUpload(MultipartFile fileUpload) throws IOException;
}
