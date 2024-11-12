package org.example.back.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.back.common.Code;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取Session
        HttpSession session = request.getSession();

        // 打印Session ID和当前请求的URL
        System.out.println("Request URL: " + request.getRequestURL());
        String sessionId = (session != null) ? session.getId() : "No Session";
        System.out.println("Session ID: " + sessionId);

        // 假设用户登录后，我们将用户信息存储在session中，这里以"User"作为session的key
        Object loginUser = session.getAttribute("user");
        // 如果用户信息不为空，则表示已登录，放行
        if (loginUser != null) {
            return true;
        }

        // 打印Session为空时的信息
        System.out.println("User not logged in, returning 401");

        // 返回401未授权状态码
        response.setStatus(Code.UNAUTHORIZED.getCode());
        response.getWriter().write(Code.UNAUTHORIZED.getDescription());
        return false;
    }
}
