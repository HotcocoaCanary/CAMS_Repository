package org.example.back.repository;

import org.example.back.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Canary
 * @version 1.0.0
 * @title StudentRepository
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午4:44
 **/
public interface StudentRepository extends JpaRepository<Student, String> {
}
