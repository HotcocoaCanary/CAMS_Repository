package org.example.back.common;

import lombok.Getter;

/**
 * @author Canary
 * @version 1.0.0
 * @title Code
 * @description 定义常用的HTTP请求响应状态码及其描述信息
 * @create 2024/11/5 下午6:13
 */
@Getter
public enum Code {
    SUCCESS(200, "操作成功"),
    NO_CONTENT(204, "操作成功，无返回内容"),

    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到资源"),

    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
    NOT_IMPLEMENTED(501, "未实现的功能");

    private final int code;
    private final String description;

    Code(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
