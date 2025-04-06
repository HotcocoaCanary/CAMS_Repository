CREATE DATABASE CAMS_repository_db;
USE CAMS_repository_db;

-- 创建用户表
CREATE TABLE `User` (
                        `user_id` VARCHAR(20) PRIMARY KEY COMMENT '学号或者工号',
                        `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                        `pwd_hash` VARCHAR(255) NOT NULL COMMENT '密码哈希',
                        `role` ENUM('admin','instructor','student') NOT NULL COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建专业表
CREATE TABLE `Major` (
                         `major_code` VARCHAR(10) PRIMARY KEY COMMENT '专业代码',
                         `name` VARCHAR(50) NOT NULL COMMENT '专业名称',
                         `college` VARCHAR(50) NOT NULL COMMENT '所属学院'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建教师表
CREATE TABLE `Instructor` (
                              `user_id` VARCHAR(20) PRIMARY KEY COMMENT '工号',
                              `name` VARCHAR(50) NOT NULL COMMENT '姓名',
                              FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建班级表
CREATE TABLE `Class` (
                         `class_id` VARCHAR(20) PRIMARY KEY COMMENT '班级ID',
                         `major_code` VARCHAR(10) NOT NULL COMMENT '所属专业',
                         `year` INT NOT NULL COMMENT '入学年份',
                         `instructor_id` VARCHAR(20) NOT NULL COMMENT '班主任ID',
                         FOREIGN KEY (`major_code`) REFERENCES `Major`(`major_code`),
                         FOREIGN KEY (`instructor_id`) REFERENCES `Instructor`(`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建学生表
CREATE TABLE `Student` (
                           `user_id` VARCHAR(20) PRIMARY KEY COMMENT '学号',
                           `name` VARCHAR(50) NOT NULL COMMENT '姓名',
                           `class_id` VARCHAR(20) NOT NULL COMMENT '所属班级',
                           `is_monitor` BOOLEAN DEFAULT false COMMENT '是否班长',
                           FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`)ON DELETE CASCADE,
                           FOREIGN KEY (`class_id`) REFERENCES `Class`(`class_id`)ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建学期表
CREATE TABLE `Semester` (
                            `semester_id` VARCHAR(20) PRIMARY KEY COMMENT '学期ID',
                            `academic_year` VARCHAR(9) NOT NULL COMMENT '学年',
                            `term` ENUM('春','秋') NOT NULL COMMENT '学期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建课程表
CREATE TABLE `Course` (
                          `course_id` VARCHAR(20) PRIMARY KEY COMMENT '课程ID',
                          `name` VARCHAR(100) NOT NULL COMMENT '课程名称',
                          `credits` FLOAT NOT NULL COMMENT '学分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建教学计划表
CREATE TABLE `TeachingPlan` (
                                `plan_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '规划ID',
                                `major_code` VARCHAR(10) NOT NULL COMMENT '专业代码',
                                `course_id` VARCHAR(20) NOT NULL COMMENT '课程ID',
                                `semester_id` VARCHAR(20) NOT NULL COMMENT '学期ID',
                                `course_category` ENUM('必修','限选','选修') NOT NULL COMMENT '课程类型',
                                FOREIGN KEY (`major_code`) REFERENCES `Major`(`major_code`),
                                FOREIGN KEY (`course_id`) REFERENCES `Course`(`course_id`),
                                FOREIGN KEY (`semester_id`) REFERENCES `Semester`(`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建选课表
CREATE TABLE `CourseSelection` (
                                   `student_id` VARCHAR(20) COMMENT '学生ID',
                                   `course_id` VARCHAR(20) COMMENT '课程ID',
                                   `semester_id` VARCHAR(20) COMMENT '学期ID',
                                   `score` FLOAT DEFAULT NULL COMMENT '课程成绩',
                                   `status` ENUM('正常','重修','挂科') NOT NULL COMMENT '状态',
                                   PRIMARY KEY (`student_id`, `course_id`, `semester_id`),
                                   FOREIGN KEY (`student_id`) REFERENCES `Student`(`user_id`) ON DELETE CASCADE,
                                   FOREIGN KEY (`course_id`) REFERENCES `Course`(`course_id`),
                                   FOREIGN KEY (`semester_id`) REFERENCES `Semester`(`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建加分表
CREATE TABLE `BonusPoint` (
                              `bonus_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '加分ID',
                              `student_id` VARCHAR(20) NOT NULL COMMENT '学生ID',
                              `semester_id` VARCHAR(20) NOT NULL COMMENT '学期ID',
                              `value` FLOAT NOT NULL COMMENT '加分值',
                              FOREIGN KEY (`student_id`) REFERENCES `Student`(`user_id`)ON DELETE CASCADE,
                              FOREIGN KEY (`semester_id`) REFERENCES `Semester`(`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建综评结果表
CREATE TABLE `EvaluationResult` (
                                    `student_id` VARCHAR(20) COMMENT '学生ID',
                                    `semester_id` VARCHAR(20) COMMENT '学期ID',
                                    `academic_score` FLOAT NOT NULL COMMENT '学业成绩(60%)',
                                    `sports_score` FLOAT NOT NULL COMMENT '体育成绩(10%)',
                                    `moral_score` FLOAT NOT NULL COMMENT '德育成绩(15%)',
                                    `other_bonus1` FLOAT NOT NULL COMMENT '其他加分1(5%)',
                                    `other_bonus2` FLOAT NOT NULL COMMENT '其他加分2(10%)',
                                    PRIMARY KEY (`student_id`, `semester_id`),
                                    FOREIGN KEY (`student_id`) REFERENCES `Student`(`user_id`)ON DELETE CASCADE,
                                    FOREIGN KEY (`semester_id`) REFERENCES `Semester`(`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;