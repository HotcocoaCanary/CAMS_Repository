package org.example.back.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.back.common.Response;
import org.example.back.entity.User;
import org.example.back.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    // 定义会话和cookie的有效期常量（单位：秒）
    private static final int SESSION_COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // 7天

    @PostMapping("/register")
    public Response<String> register(@RequestBody User user) {
        try{
            String message = userService.register(user);
            return Response.success(message);
        }catch (Exception e){
            return Response.internalServerError();
        }
    }

    @PostMapping("/login")
    public Response<String> login(@RequestParam("id") String id,
                                  @RequestParam("password") String password,
                                  @RequestParam("rememberMe") boolean rememberMe,
                                  HttpSession session,
                                  HttpServletResponse response) {
        try {
            User user = userService.login(id, password);
            if (user != null) {
                // 设置用户会话
                session.setAttribute("user", user);
                if (rememberMe) {
                    // 如果用户选择了记住我，设置cookie
                    Cookie cookie = new Cookie("rememberMe", "true");
                    cookie.setMaxAge(SESSION_COOKIE_MAX_AGE);
                    cookie.setHttpOnly(true); // 增加安全性，防止XSS攻击
                    cookie.setSecure(true); // 确保在HTTPS下传输
                    response.addCookie(cookie);
                }
                return Response.success();
            } else {
                // 用户不存在或密码错误，返回具体错误信息
                return Response.badRequest("用户不存在或密码错误");
            }
        } catch (Exception e) {
            return Response.internalServerError();
        }
    }

}
