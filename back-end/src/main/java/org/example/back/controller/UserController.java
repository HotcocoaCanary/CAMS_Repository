package org.example.back.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.back.common.Response;
import org.example.back.common.request.EditPasswordRequest;
import org.example.back.common.request.LoginRequest;
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
    public Response<User> login(@RequestBody LoginRequest loginRequest, HttpSession session, HttpServletResponse response) {
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
                user.setPassword("总要有所坚持");
                return Response.success(user);
            } else {
                // 用户不存在或密码错误，返回具体错误信息
                return Response.badRequest("用户不存在或密码错误");
            }
        } catch (Exception e) {
            // 可以记录日志
            return Response.internalServerError();
        }
    }

    @PostMapping("/editPassword")
    public Response<String> editPassword(@RequestBody EditPasswordRequest data) {
        try {
            String id = data.getId();
            String password = data.getPassword();
            String newPassword = data.getNewPassword();
            String message = userService.editPassword(id, password, newPassword);
            return Response.success(message);
        } catch (Exception e) {
            // 可以记录日志
            return Response.internalServerError();
        }
    }

    @GetMapping("/logout")
    public Response<String> logout(HttpSession session, HttpServletResponse response) {
        try{
            // 清除会话信息
            session.invalidate();

            // 删除记住我cookie
            Cookie cookie = new Cookie("rememberMe", null);
            cookie.setMaxAge(0); // 立即删除cookie
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);

            return Response.success("退出登录成功");
        } catch (Exception e) {
            return Response.internalServerError();
        }
    }

}
