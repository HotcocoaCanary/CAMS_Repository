DELIMITER $$

CREATE TRIGGER Insert_Academic_Performance
    AFTER INSERT
    ON Student_Course
    FOR EACH ROW
BEGIN
    DECLARE total_score decimal(10, 2);
    DECLARE total_credits int;
    DECLARE avg_score decimal(5, 2);
    DECLARE failed_courses int;
    DECLARE student_term enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING');
    -- 获取学生对应的学期
    SET student_term = NEW.Term;
    -- 计算总分和总学分
    SELECT SUM(SC.Score * SC.Credit), SUM(SC.Credit)
    INTO total_score, total_credits
    FROM Student_Course SC
    WHERE SC.StudentID = NEW.StudentID
      AND SC.Term = student_term;
    -- 计算平均分
    SET avg_score = (total_score / total_credits)*0.6;
    -- 统计Score为0的课程数量
    SELECT COUNT(*)
    INTO failed_courses
    FROM Student_Course SC
    WHERE SC.StudentID = NEW.StudentID
      AND SC.Term = student_term
      AND SC.Score = 0;
    -- 检查Comprehensive_Evaluation表中是否存在对应的记录
    IF EXISTS (SELECT 1 FROM Comprehensive_Evaluation WHERE StudentID = NEW.StudentID AND Term = student_term) THEN
        -- 更新记录
        UPDATE Comprehensive_Evaluation
        SET Academic_Performance = avg_score, Failed_courses = failed_courses
        WHERE StudentID = NEW.StudentID
          AND Term = student_term;
    ELSE
        -- 插入新记录
        INSERT INTO Comprehensive_Evaluation (StudentID, Term, Academic_Performance, Failed_courses)
        VALUES (NEW.StudentID, student_term, avg_score, failed_courses);
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER Update_Academic_Performance
    AFTER UPDATE
    ON Student_Course
    FOR EACH ROW
BEGIN
    DECLARE total_score decimal(10, 2);
    DECLARE total_credits int;
    DECLARE avg_score decimal(5, 2);
    DECLARE failed_courses int;
    DECLARE student_term enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING');
    -- 获取学生对应的学期
    SET student_term = NEW.Term;
    -- 计算总分和总学分
    SELECT SUM(SC.Score * SC.Credit), SUM(SC.Credit)
    INTO total_score, total_credits
    FROM Student_Course SC
    WHERE SC.StudentID = NEW.StudentID
      AND SC.Term = student_term;
    -- 计算平均分
    SET avg_score = (total_score / total_credits)*0.6;
    -- 统计Score为0的课程数量
    SELECT COUNT(*)
    INTO failed_courses
    FROM Student_Course SC
    WHERE SC.StudentID = NEW.StudentID
      AND SC.Term = student_term
      AND SC.Score = 0;
    -- 检查Comprehensive_Evaluation表中是否存在对应的记录
    IF EXISTS (SELECT 1 FROM Comprehensive_Evaluation WHERE StudentID = NEW.StudentID AND Term = student_term) THEN
        -- 更新记录
        UPDATE Comprehensive_Evaluation
        SET Academic_Performance = avg_score, Failed_courses = failed_courses
        WHERE StudentID = NEW.StudentID
          AND Term = student_term;
    ELSE
        -- 插入新记录
        INSERT INTO Comprehensive_Evaluation (StudentID, Term, Academic_Performance, Failed_courses)
        VALUES (NEW.StudentID, student_term, avg_score, failed_courses);
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER Delete_Academic_Performance
    AFTER DELETE
    ON Student_Course
    FOR EACH ROW
BEGIN
    DECLARE total_score decimal(10, 2);
    DECLARE total_credits int;
    DECLARE avg_score decimal(5, 2);
    DECLARE failed_courses int;
    DECLARE student_term enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING');
    -- 获取学生对应的学期
    SET student_term = OLD.Term;
    -- 检查总学分是否为0
    IF total_credits = 0 THEN
        -- 如果总学分为0，则删除Comprehensive_Evaluation表中的对应记录
        DELETE FROM Comprehensive_Evaluation
        WHERE StudentID = OLD.StudentID
          AND Term = student_term;
    ELSE
        -- 计算平均分
        SET avg_score = (total_score / total_credits) * 0.6;
        -- 统计Score为0的课程数量
        SELECT COUNT(*)
        INTO failed_courses
        FROM Student_Course SC
        WHERE SC.StudentID = OLD.StudentID
          AND SC.Term = student_term
          AND SC.Score = 0;
        -- 检查Comprehensive_Evaluation表中是否存在对应的记录
        IF EXISTS (SELECT 1 FROM Comprehensive_Evaluation WHERE StudentID = OLD.StudentID AND Term = student_term) THEN
            -- 更新记录
            UPDATE Comprehensive_Evaluation
            SET Academic_Performance = avg_score, Failed_courses = failed_courses
            WHERE StudentID = OLD.StudentID
              AND Term = student_term;
        ELSE
            -- 插入新记录
            INSERT INTO Comprehensive_Evaluation (StudentID, Term, Academic_Performance, Failed_courses)
            VALUES (OLD.StudentID, student_term, avg_score, failed_courses);
        END IF;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER Insert_Sports_Achievement
    AFTER INSERT
    ON Student_Course
    FOR EACH ROW
BEGIN
    DECLARE sports_score decimal(5, 2);
    DECLARE student_term enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING');
    -- 如果课程名称包含“大学体育”，则更新Sports_Achievement
    IF NEW.Name LIKE '%大学体育%' THEN
        SET sports_score = NEW.Score / 10;
        SET student_term = NEW.Term;
        -- 检查Comprehensive_Evaluation表中是否存在对应的记录
        IF EXISTS (SELECT 1 FROM Comprehensive_Evaluation WHERE StudentID = NEW.StudentID AND Term = student_term) THEN
            -- 更新记录
            UPDATE Comprehensive_Evaluation
            SET Sports_Achievement = sports_score
            WHERE StudentID = NEW.StudentID
              AND Term = student_term;
        ELSE
            -- 插入新记录
            INSERT INTO Comprehensive_Evaluation (StudentID, Term, Sports_Achievement)
            VALUES (NEW.StudentID, student_term, sports_score);
        END IF;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER Update_Sports_Achievement
    AFTER UPDATE
    ON Student_Course
    FOR EACH ROW
BEGIN
    DECLARE sports_score decimal(5, 2);
    DECLARE student_term enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING');
    -- 如果课程名称包含“大学体育”，则更新Sports_Achievement
    IF NEW.Name LIKE '%大学体育%' THEN
        SET sports_score = NEW.Score / 10;
        SET student_term = NEW.Term;
        -- 检查Comprehensive_Evaluation表中是否存在对应的记录
        IF EXISTS (SELECT 1 FROM Comprehensive_Evaluation WHERE StudentID = NEW.StudentID AND Term = student_term) THEN
            -- 更新记录
            UPDATE Comprehensive_Evaluation
            SET Sports_Achievement = sports_score
            WHERE StudentID = NEW.StudentID
              AND Term = student_term;
        ELSE
            -- 插入新记录
            INSERT INTO Comprehensive_Evaluation (StudentID, Term, Sports_Achievement)
            VALUES (NEW.StudentID, student_term, sports_score);
        END IF;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER Delete_Sports_Achievement
    AFTER DELETE
    ON Student_Course
    FOR EACH ROW
BEGIN
    DECLARE student_term enum ('FRESHMAN_FALL', 'FRESHMAN_SPRING', 'SOPHOMORE_FALL', 'SOPHOMORE_SPRING', 'JUNIOR_FALL', 'JUNIOR_SPRING', 'SENIOR_FALL', 'SENIOR_SPRING');
    -- 如果删除的课程名称包含“大学体育”，则重置Sports_Achievement
    IF OLD.Name LIKE '%大学体育%' THEN
        SET student_term = OLD.Term;
        -- 更新Comprehensive_Evaluation表，将Sports_Achievement重置为默认值
        UPDATE Comprehensive_Evaluation
        SET Sports_Achievement = DEFAULT(Sports_Achievement)
        WHERE StudentID = OLD.StudentID
          AND Term = student_term;
    END IF;
END$$

DELIMITER ;