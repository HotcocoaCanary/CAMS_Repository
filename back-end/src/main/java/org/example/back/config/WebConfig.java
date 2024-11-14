package org.example.back.config;

import jakarta.annotation.Resource;
import org.example.back.interceptor.AdminInterceptor;
import org.example.back.interceptor.LoginInterceptor;
import org.example.back.interceptor.TeacherInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private TeacherInterceptor teacherInterceptor;

    @Resource
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register"); // 排除login和register
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/teacher")
                .excludePathPatterns("/admin");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin");
    }
}
