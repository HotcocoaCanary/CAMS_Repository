package org.example.back.common.request;

import lombok.Data;
import org.example.back.entity.User;

/**
 * @author Canary
 * @version 1.0.0
 * @title register
 * @description <TODO description class purpose>
 * @creat 2024/11/7 下午12:11
 **/
@Data
public class RegisterRequest {
    private User user;
    private String classNameOrDepartment;
}
