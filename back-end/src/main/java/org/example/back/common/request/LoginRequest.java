package org.example.back.common.request;

import lombok.Data;

/**
 * @author Canary
 * @version 1.0.0
 * @title LoginRequest
 * @description 登录请求体
 * @creat 2024/11/7 下午12:15
 **/

@Data
public class LoginRequest {
    private String id;
    private String password;
    private boolean rememberMe;
}
