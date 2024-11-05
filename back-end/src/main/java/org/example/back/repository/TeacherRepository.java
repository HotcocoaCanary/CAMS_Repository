package org.example.back.repository;

import org.example.back.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Canary
 * @version 1.0.0
 * @title asd
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午4:43
 **/
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    void addTeacher(Teacher teacher);
    Teacher findTeacherByIdAndPassword(String id, String password);
    Teacher findTeacherByIdAndName(String id, String name);
}
