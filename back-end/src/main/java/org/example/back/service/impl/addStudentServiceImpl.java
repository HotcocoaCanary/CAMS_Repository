package org.example.back.service.impl;
import org.example.back.common.Role;
import org.example.back.entity.User;
import org.example.back.repository.StudentRepository;
import org.example.back.repository.UserRepository;
import org.example.back.service.addStudentService;
import org.springframework.stereotype.Service;

@Service
public class addStudentServiceImpl implements addStudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    // 通过构造器注入依赖
    public addStudentServiceImpl(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public String addStudent(User user, String className) {
        // 检查用户是否已经存在
        if (userRepository.findById(user.getId()).isPresent()) {
            return "用户已存在";
        }

        // 设置用户角色为学生
        user.setRole(Role.STUDENT);

        // 保存用户信息
        userRepository.save(user);

        // 保存学生信息到学生表
        studentRepository.addStudent(user.getId(), className);

        return "学生添加成功";
    }
}

