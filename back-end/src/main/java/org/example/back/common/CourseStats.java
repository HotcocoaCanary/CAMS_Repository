package org.example.back.common;

import lombok.Getter;

/**
 * @author Canary
 * @version 1.0.0
 * @title CourseStats
 * @description <TODO description class purpose>
 * @creat 2024/11/5 下午4:39
 **/ // Enum for CourseStats

@Getter
public enum CourseStats {
    REQUIRED, // 必修
    LIMITED,  // 限选
    ELECTIVE  // 选修
}
