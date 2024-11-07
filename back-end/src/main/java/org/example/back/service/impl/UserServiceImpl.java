package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.common.Role;
import org.example.back.entity.User;
import org.example.back.repository.StudentRepository;
import org.example.back.repository.TeacherRepository;
import org.example.back.repository.UserRepository;
import org.example.back.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Canary
 * @version 1.0.0
 * @title UserServiceImpl
 * @description <TODO description class purpose>
 * @creat 2024/11/6 下午1:12
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private StudentRepository studentRepository;

    @Override
    public User login(String id, String password) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return user.get();
            }
        }
        return null;
    }

    @Override
    public String register(User user, String classNameOrDepartment) {
        if (user.getRole().equals(Role.STUDENT)) {
            userRepository.save(user);
            studentRepository.addStudent(user.getId(), classNameOrDepartment);
        }else {
            userRepository.save(user);
            teacherRepository.addTeacher(user.getId(), classNameOrDepartment);
        }
        return "注册成功";
    }
}
