package org.example.back.controller;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.entity.User;
import org.example.back.service.UserService;
import org.example.back.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Response<String> register(String id, String password, String name, byte age,  String gender, String department, String className) {
        if (className !=null){
            //查询用户
            User u = userService.findStudentById(id);
            if (u == null) {
                //没有占用
                //注册
                userService.register(id, password, name, age, gender, department, className);
                return Response.success();
            } else {
                //占用
                return Response.badRequest("学号重复");
            }
        }else{
            //查询用户
            User u = userService.findTeacherById(id);
            if (u == null) {
                //没有占用
                //注册
                userService.register(id, password, name, age, gender, department);
                return Response.success();
            } else {
                //占用
                return Response.badRequest("工号重复");
            }
        }

    }

    @PostMapping("/login")
    public Response<String> login(String id, String password) {

        User loginUser = userService.login(id, password);
        if (loginUser == null) {
            return Response.badRequest("登录错误");
        }
        //登录成功
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserID", loginUser.getId());
        claims.put("Name", loginUser.getName());
        return Response.success(jwtUtil.getToken(claims));
    }

    @GetMapping("/userInfo")
    public Response<User> userInfo() {
        //根据用户名查询用户
        Map<String, Object> map = jwtUtil.parseToken(jwtUtil.getThisToken());
        String userId = (String) map.get("UserID");
        String userName = (String) map.get("Name");
        User user = userService.findUserByIdAndName(userId,userName);
        //不返回密码，保护用户隐私
        user.setPassword("热爱使我们进步");
        return Response.success(user);
    }
}
