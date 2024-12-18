package org.example.back.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.back.common.Term;
import org.example.back.entity.User;
import org.example.back.service.CourseService;
import org.example.back.util.CEExcelBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @title UserStudentController
 * @description 学生可以用的操作
 * @author Canary
 * @creat 2024/11/13 下午11:40
 * @version 1.0.0
 **/

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private CourseService courseService;

    @GetMapping("/ce")
    public ResponseEntity<byte[]> register(@RequestParam Term term, HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        CEExcelBuilder ceExcelBuilder = null;
        try {
            ceExcelBuilder = courseService.getComprehensiveEvaluation(loginUser, term);
            Workbook workbook = ceExcelBuilder.getWorkbook();

            // 将Workbook写入ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            baos.close();

            // 设置HTTP响应头
            byte[] bytes = baos.toByteArray();
            String filename = term.toString() + "ce.xlsx";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // 返回ResponseEntity
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            courseService.closeWorkbook(ceExcelBuilder);
        }
    }

}
