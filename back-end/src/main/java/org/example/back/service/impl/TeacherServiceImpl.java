package org.example.back.service.impl;

import jakarta.annotation.Resource;
import org.example.back.entity.Teacher;
import org.example.back.repository.TeacherRepository;
import org.example.back.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherRepository teacherRepository;

    @Override
    public void add(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
