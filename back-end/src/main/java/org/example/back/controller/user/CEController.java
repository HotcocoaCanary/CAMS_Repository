package org.example.back.controller.user;

import jakarta.annotation.Resource;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.back.common.Term;
import org.example.back.service.CourseService;
import org.example.back.util.CEExcelBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Canary
 * @version 1.0.0
 * @title AdminCEController
 * @description 教师或班长对综测的操作
 * @creat 2024/11/14 上午8:32
 **/
@RestController
@RequestMapping("/teacher/ce")
public class CEController {

    @Resource
    private CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<byte[]> register(@RequestParam Term term) {
        CEExcelBuilder ceExcelBuilder = null;
        try {
            ceExcelBuilder = courseService.getComprehensiveEvaluation(term);
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
    //<TODO 添加选课记录接口>

    //<TODO 删除选课记录接口>

    //<TODO 修改选课记录接口>

    //<TODO 通过班级查询选课记录接口>

    //<TODO 修改综测记录>
}
