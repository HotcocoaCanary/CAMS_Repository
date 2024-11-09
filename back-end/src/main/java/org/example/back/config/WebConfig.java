package org.example.back.config;

import jakarta.annotation.Resource;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 指定拦截的URL模式
                .excludePathPatterns("/user/login", "/user/register");
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/teacher")
                .excludePathPatterns("/admin");
    }
}
