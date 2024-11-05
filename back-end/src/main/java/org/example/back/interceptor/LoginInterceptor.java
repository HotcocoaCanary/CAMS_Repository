package org.example.back.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.back.common.Code;
import org.example.back.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            jwtUtil.parseToken(token);
            //放行
            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(Code.UNAUTHORIZED.getCode());
            response.getWriter().write(Code.UNAUTHORIZED.getDescription());
            //不放行
            return false;
        }
    }
}
