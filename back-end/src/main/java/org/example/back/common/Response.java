package org.example.back.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Canary
 * @version 1.0.0
 * @title ApiResult
 * @description 简单易用的响应格式类，使用Code枚举设置状态码和描述
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String statusMessage;
    private T data;

    // 静态方法用于创建不同类型的响应
    public static <T> Response<T> success() {
        return Response.<T>builder().statusCode(Code.SUCCESS.getCode())
                .statusMessage(Code.SUCCESS.getDescription()).build();
    }

    public static <T> Response<T> success(T data) {
        return Response.<T>builder().statusCode(Code.SUCCESS.getCode())
                .statusMessage(Code.SUCCESS.getDescription()).data(data).build();
    }

    public static <T> Response<T> noContent() {
        return Response.<T>builder().statusCode(Code.NO_CONTENT.getCode())
                .statusMessage(Code.NO_CONTENT.getDescription()).build();
    }

    public static <T> Response<T> badRequest() {
        return Response.<T>builder().statusCode(Code.BAD_REQUEST.getCode())
                .statusMessage(Code.BAD_REQUEST.getDescription()).build();
    }

    public static <T> Response<T> badRequest(String statusMessage) {
        return Response.<T>builder().statusCode(Code.BAD_REQUEST.getCode())
                .statusMessage(statusMessage).build();
    }

    public static <T> Response<T> unauthorized() {
        return Response.<T>builder().statusCode(Code.UNAUTHORIZED.getCode())
                .statusMessage(Code.UNAUTHORIZED.getDescription()).build();
    }

    public static <T> Response<T> forbidden() {
        return Response.<T>builder().statusCode(Code.FORBIDDEN.getCode())
                .statusMessage(Code.FORBIDDEN.getDescription()).build();
    }

    public static <T> Response<T> notFound() {
        return Response.<T>builder().statusCode(Code.NOT_FOUND.getCode())
                .statusMessage(Code.NOT_FOUND.getDescription()).build();
    }

    public static <T> Response<T> internalServerError() {
        return Response.<T>builder().statusCode(Code.INTERNAL_SERVER_ERROR.getCode())
                .statusMessage(Code.INTERNAL_SERVER_ERROR.getDescription()).build();
    }

    public static <T> Response<T> notImplemented() {
        return Response.<T>builder().statusCode(Code.NOT_IMPLEMENTED.getCode())
                .statusMessage(Code.NOT_IMPLEMENTED.getDescription()).build();
    }

    // 重写toString方法，便于调试
    @Override
    public String toString() {
        return "ApiResult{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
