package org.example.back.service;

import org.example.back.entity.StudentCourse;

/**
 * @author Canary
 * @version 1.0.0
 * @title CourseService
 * @description <TODO description class purpose>
 * @creat 2024/11/8 下午10:28
 **/
public interface CourseService {
    void updataCourse();
    void updataCourse(StudentCourse studentCourse);
    void updataOtherCourse();
}
