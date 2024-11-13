package org.example.back.service;

import org.example.back.common.Term;
import org.example.back.entity.StudentCourse;

/**
 * @author Canary
 * @version 1.0.0
 * @title courseService
 * @description
 * @creat 2024/11/8 下午10:28
 **/
public interface courseService {

    void updateCourse(String fileName) throws Exception;

    void updateOtherCourse(String fileName, Term term) throws Exception;

    void updateCourse(StudentCourse studentCourse);

    void deleteCourse(StudentCourse studentCourse);

    void insertCourse(StudentCourse studentCourse);
}
