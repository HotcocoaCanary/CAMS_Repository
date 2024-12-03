package org.example.back.controller.admin;

import org.example.back.entity.User;
import org.example.back.service.addStudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * @author Canary
 * @version 1.0.0
 * @title UserStudentController
 * @description <TODO description class purpose>
 * @creat 2024/11/13 下午7:55
 **/
@RestController
@RequestMapping("/admin/student")
public class AdminStudentController {

    //<TODO 添加学生接口>
    private addStudentService studentService;

    @Autowired
    public void StudentController(addStudentService studentService) {
        this.studentService = studentService;
    }

    public AdminStudentController() {
        studentService = null;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody User user, String className) {
        String result = studentService.addStudent(user, className);
        return ResponseEntity.ok(result);
    }
    //<TODO 删除学生接口>

    //<TODO 修改学生接口>

    //<TODO 查询所有学生信息接口>
}
