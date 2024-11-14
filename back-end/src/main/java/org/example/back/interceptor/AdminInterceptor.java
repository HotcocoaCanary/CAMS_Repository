package org.example.back.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.back.common.Code;
import org.example.back.common.Role;
import org.example.back.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Canary
 * @version 1.0.0
 * @title AdminInterceptor
 * @description 管理员身份判断器
 * @creat 2024/11/14 上午8:26
 **/
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取Session
        HttpSession session = request.getSession();

        // 假设用户登录后，我们将用户信息存储在session中，这里以"User"作为session的key
        User loginUser = (User) session.getAttribute("user");

        // 根据用户角色判断是否有权限访问资源
        if (Role.ADMIN.equals(loginUser.getRole())) {
            return true; // 如果是教师，则放行
        } else {
            // 如果权限不足，可以设置响应状态码为403
            response.setStatus(Code.FORBIDDEN.getCode());
            // 可以选择返回错误信息
            response.getWriter().write("您没有足够的权限执行此操作");
            return false; // 拦截请求
        }
    }
}
