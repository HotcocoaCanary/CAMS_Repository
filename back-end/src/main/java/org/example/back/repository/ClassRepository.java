package org.example.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.back.entity.Class;

/**
 * @author Canary
 * @version 1.0.0
 * @title ClassRepository
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午4:44
 **/
public interface ClassRepository extends JpaRepository<Class, Integer> {
    Class findByName(String name);
}
