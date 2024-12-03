package org.example.back.controller.admin;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.entity.Teacher;
import org.example.back.service.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Canary
 * @version 1.0.0
 * @title UserTeacherController
 * @description <TODO description class purpose>
 * @creat 2024/11/13 下午7:54
 **/
@RestController
@RequestMapping("/admin/teacher")
public class AdminTeacherController {
    @Resource
    private TeacherService teacherService;

    //<TODO 添加教师接口>
    @PostMapping("/add")
    public Response<String> addTeacher(@RequestBody Teacher teacher) {
        teacherService.add(teacher);
        return Response.success();
    }

    //<TODO 删除教师接口>

    //<TODO 修改教师接口>

    //<TODO 查询所有教师信息接口>
}
