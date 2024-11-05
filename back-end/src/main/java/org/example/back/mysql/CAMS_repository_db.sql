CREATE DATABASE CAMS_repository_db;
USE CAMS_repository_db;

-- 教师表
CREATE TABLE Teachers
(
    ID         char(13)         NOT NULL,              # 工号，主键
    Password   char(64)         NOT NULL,              # 密码
    Name       varchar(50)      NOT NULL,              # 姓名
    Age        tinyint          NOT NULL,              # 年龄
    Gender     enum ('男','女') NOT NULL DEFAULT '男', # 性别
    Department varchar(50)      NOT NULL,              # 所属院系
    PRIMARY KEY (ID)
);

-- 班级表
CREATE TABLE Classes
(
    ClassID      int         NOT NULL AUTO_INCREMENT, # 班级编号，主键，自增
    ClassName    varchar(50) NOT NULL,                # 班级名称
    ClassTeacher char(10),                            # 班主任，外键
    Department   varchar(50) NOT NULL,                # 所属院系
    PRIMARY KEY (ClassID),
    CONSTRAINT fk_Classes_ClassTeacher FOREIGN KEY (ClassTeacher) REFERENCES Teachers (ID)
);

-- 学生表
CREATE TABLE Students
(
    ID         char(13)         NOT NULL,              # 学号，主键
    Password   char(64)         NOT NULL,              # 密码
    Name       varchar(50)      NOT NULL,              # 姓名
    Age        tinyint          NOT NULL,              # 年龄
    Gender     enum ('男','女') NOT NULL DEFAULT '男', # 性别
    Department varchar(50)      NOT NULL,              # 所属院系
    ClassID    int              NOT NULL,              # 班级编号，外键
    PRIMARY KEY (ID),
    CONSTRAINT fk_Students_ClassID FOREIGN KEY (ClassID) REFERENCES Classes (ClassID)
);

-- 学生选课表
CREATE TABLE Student_Course
(
    ID        bigint                                                                         NOT NULL AUTO_INCREMENT,   # 选课记录编号，主键，自增
    StudentID char(13)                                                                       NOT NULL,                  # 学生学号，外键
    CourseID  char(10)                                                                       NOT NULL,                  # 课程编号，外键
    Name      varchar(50)                                                                    NOT NULL,                  # 课程名称
    Score     decimal(5, 2),                                                                                            # 成绩
    Credit    tinyint                                                                        NOT NULL,                  # 学分
    Stats     enum ('必修','限选','选修')                                                    NOT NULL DEFAULT '必修',   # 选课属性
    Term      enum ('大一上','大一下','大二上','大二下','大三上','大三下','大四上','大四下') NOT NULL DEFAULT '大一上', # 学期
    PRIMARY KEY (ID),
    CONSTRAINT fk_Student_Course_StudentID FOREIGN KEY (StudentID) REFERENCES Students (ID)
);

-- 综合测评表
CREATE TABLE Comprehensive_Evaluation
(
    ID                   bigint                                                                         NOT NULL AUTO_INCREMENT,   # 测评记录编号，主键，自增
    Term                 enum ('大一上','大一下','大二上','大二下','大三上','大三下','大四上','大四下') NOT NULL DEFAULT '大一上', # 学期
    Academic_Performance decimal(5, 2),                                                                                            # 学习成绩
    Morality             decimal(5, 2),                                                                                            # 思想品德表现
    Sports_Achievement   decimal(5, 2),                                                                                            # 体育成绩
    Other_Score1         decimal(5, 2),                                                                                            # 其他得分1
    Other_Score2         decimal(5, 2),                                                                                            # 其他得分2
    StudentID            char(13)                                                                       NOT NULL,                  # 学生学号，外键
    PRIMARY KEY (ID),
    CONSTRAINT fk_Comprehensive_Evaluation_StudentID FOREIGN KEY (StudentID) REFERENCES Students (ID)
);

insert into teachers value
    ('0001', '123456', '陈俊杰', '18', '女', '计算机与信息工程学院');

insert into classes(ClassName, ClassTeacher,Department) value
    ('软工一班', '0001', '计算机与信息工程学院')