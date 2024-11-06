package org.example.back.repository;

import org.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Canary
 * @version 1.0.0
 * @title asd
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午4:43
 **/
public interface UserRepository extends JpaRepository<User, String> {
}

