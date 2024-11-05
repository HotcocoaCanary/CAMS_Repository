package org.example.back.service;

import org.example.back.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Canary
 * @version 1.0.0
 * @title UserService
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午7:51
 **/
@Service
public interface UserService {

    User login(String id, String password);

    void register(String id, String password, String name, byte age, String gender, String department, String className);

    void register(String id, String password, String name, byte age, String gender, String department);

    User findStudentById(String id);

    User findTeacherById(String id);

    User findUserByIdAndName(String userId, String userName);
}
