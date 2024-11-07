package org.example.back.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.back.common.Response;
import org.example.back.common.Role;
import org.example.back.common.request.LoginRequest;
import org.example.back.entity.Class;
import org.example.back.entity.User;
import org.example.back.service.UserService;
import org.example.back.common.request.RegisterRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    // 定义会话和cookie的有效期常量（单位：秒）
    private static final int SESSION_COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // 7天

    @PostMapping("/register")
    public Response<String> register(@RequestBody RegisterRequest data) {
        try{
            User user = data.getUser();
            String department = data.getClassNameOrDepartment();
            String message = userService.register(user,department);
            return Response.success(message);
        }catch (Exception e){
            return Response.internalServerError();
        }
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody LoginRequest loginRequest, HttpSession session, HttpServletResponse response) {
        try {
            User user = userService.login(loginRequest.getId(), loginRequest.getPassword());
            if (user != null) {
                // 设置用户会话
                session.setAttribute("user", user);
                if (loginRequest.isRememberMe()) {
                    // 如果用户选择了记住我，设置cookie
                    Cookie cookie = new Cookie("rememberMe", "true");
                    cookie.setMaxAge(SESSION_COOKIE_MAX_AGE); // 假设这是一个常量，表示cookie的有效期
                    cookie.setHttpOnly(true); // 增加安全性，防止XSS攻击
                    cookie.setSecure(true); // 确保在HTTPS下传输
                    response.addCookie(cookie);
                }
                return Response.success("登录成功");
            } else {
                // 用户不存在或密码错误，返回具体错误信息
                return Response.badRequest("用户不存在或密码错误");
            }
        } catch (Exception e) {
            // 可以记录日志
            return Response.internalServerError();
        }
    }

}
