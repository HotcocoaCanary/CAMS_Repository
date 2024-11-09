package org.example.back.service;

import org.example.back.entity.User;

/**
 * @author Canary
 * @version 1.0.0
 * @title UserService
 * @description
 * @creat 2024/11/6 下午1:12
 **/
public interface UserService {
    User login(String id, String password);
    String register(User user, String department);
}
