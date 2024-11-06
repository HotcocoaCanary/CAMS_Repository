package org.example.back.controller;

import jakarta.annotation.Resource;
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
                                  HttpSession session) {
        try{
            System.out.println(id);
            System.out.println(password);
            User user = userService.login(id, password);
            if (user != null) {
                session.setAttribute("user", user);
                return Response.success();
            }
            return Response.badRequest("登录错误");
        }catch (Exception e){
            e.printStackTrace();
            return Response.internalServerError();
        }
    }

}
