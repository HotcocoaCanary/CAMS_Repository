CREATE DATABASE CAMS_repository_db;
USE CAMS_repository_db;

-- 用户表（教师和学生合并）
CREATE TABLE Users
(
    ID         varchar(50)                       NOT NULL,              # 工号/学号，主键
    Password   varchar(50)                       NOT NULL,              # 密码
    Name       varchar(50)                       NOT NULL,              # 姓名
    Gender varchar(50)                                    NOT NULL,     # 性别
    Role   enum ('TEACHER', 'STUDENT' ,'ADMIN') NOT NULL,     # 身份
    PRIMARY KEY (ID)
);

CREATE TABLE Teachers
(
    TeacherID  varchar(50) PRIMARY KEY,
    Department varchar(50) NOT NULL,
    FOREIGN KEY (TeacherID) REFERENCES Users (ID)
);

-- 班级表
CREATE TABLE Classes
(
    Name       varchar(50) NOT NULL, # 班级名称
    Grade      varchar(50) NOT NULL,
    Department varchar(50) NOT NULL, # 所属院系
    TeacherID  varchar(50),
    PRIMARY KEY (Name),
    FOREIGN KEY (TeacherID) REFERENCES Teachers (TeacherID)
);

CREATE TABLE Students
(
    StudentID  varchar(50) PRIMARY KEY,
    Class_name varchar(50),
    FOREIGN KEY (StudentID) REFERENCES Users (ID),
    FOREIGN KEY (Class_name) REFERENCES Classes (Name)
);

-- 学生选课表（更新分数范围）
CREATE TABLE Student_Course
(
    StudentID varchar(50)                 NOT NULL,                                                                     # 学生学号，外键（指向Users表的ID）
    Name      varchar(50)                 NOT NULL,                                                                     # 课程名称
    Score     decimal(5, 2) CHECK (Score >= 0 AND Score <= 100),                                                        # 成绩，范围0-100
    Credit    double                      NOT NULL,                                                                     # 学分
    Stats     enum ('REQUIRED', 'LIMITED', 'ELECTIVE') NOT NULL DEFAULT 'REQUIRED',                                                      # 选课属性
    Term      enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING') NOT NULL DEFAULT 'FRESHMAN_FALL', # 学期
    PRIMARY KEY (StudentID,Name,Term),
    CONSTRAINT fk_Student_Course_StudentID FOREIGN KEY (StudentID) REFERENCES Users (ID)
);

-- 综合测评表（更新分数范围）
CREATE TABLE Comprehensive_Evaluation
(                                                                       # 测评记录编号，主键，自增
    Term                 enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING') NOT NULL DEFAULT 'FRESHMAN_FALL', # 学期
    Academic_Performance decimal(5, 2),                           # 学习成绩，范围0-60
    Morality             decimal(5, 2) CHECK (Morality >= 0 AND Morality <= 15),                                                   # 思想品德表现，范围0-15
    Sports_Achievement   decimal(5, 2) CHECK (Sports_Achievement >= 0 AND Sports_Achievement <= 10) DEFAULT 6.00,                               # 体育成绩，范围0-10
    Other_Score1         decimal(5, 2),                                           # 其他得分1，范围0-10
    Other_Score2         decimal(5, 2),                                            # 其他得分2，范围0-5
    StudentID            varchar(50) NOT NULL,                                                                                        # 学生学号，外键（指向Users表的ID）
    PRIMARY KEY (StudentID,Term),
    CONSTRAINT fk_Comprehensive_Evaluation_StudentID FOREIGN KEY (StudentID) REFERENCES Users (ID)
);