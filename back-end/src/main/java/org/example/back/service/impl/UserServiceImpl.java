package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.entity.User;
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
    public String register(User user) {
        userRepository.save(user);
        return "注册成功";
    }
}
