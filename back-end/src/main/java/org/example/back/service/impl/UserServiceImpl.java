package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.entity.Class;
import org.example.back.entity.Student;
import org.example.back.entity.Teacher;
import org.example.back.entity.User;
import org.example.back.repository.ClassRepository;
import org.example.back.repository.StudentRepository;
import org.example.back.repository.TeacherRepository;
import org.example.back.service.UserService;

/**
 * @author Canary
 * @version 1.0.0
 * @title UserServiceImpl
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午7:52
 **/
public class UserServiceImpl implements UserService {
    @Resource
    StudentRepository studentRepository;

    @Resource
    TeacherRepository teacherRepository;

    @Resource
    ClassRepository classRepository;

    @Override
    public User login(String id, String password) {
        User user;
        User teacher = teacherRepository.findTeacherByIdAndPassword(id,password);
        User student = studentRepository.findStudentByIdAndPassword(id,password);
        user = teacher;
        user = student==null?user:student;
        return user;
    }

    @Override
    public void register(String id, String password, String name, byte age, String gender, String department, String className) {
        Class c = classRepository.findByName(className);
        Student student = new Student(id, password,name,age,gender,department,c);
        studentRepository.addStudent(student);
    }

    @Override
    public void register(String id, String password, String name, byte age, String gender, String department) {
        Teacher teacher = new Teacher(id, password,name,age,gender,department);
        teacherRepository.save(teacher);
    }

    @Override
    public User findStudentById(String id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public User findTeacherById(String id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public User findUserByIdAndName(String userId, String userName) {
        User user;
        user = teacherRepository.findTeacherByIdAndName(userId,userName)==null?null:teacherRepository.findTeacherByIdAndName(userId,userName);
        user = studentRepository.findStudentByIdAndName(userId,userName)==null?user:studentRepository.findStudentByIdAndName(userId,userName);
        return user;
    }
}
