package org.example.back.util;

import lombok.Data;
import org.example.back.common.CourseStats;
import org.example.back.common.Role;
import org.example.back.common.Term;
import org.example.back.entity.StudentCourse;
import org.example.back.entity.StudentCourseId;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.back.entity.User;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Canary
 * @version 1.0.0
 * @title CourseExcelList
 * @description 将学生选课信息保存到数据库
 * @creat 2024/11/9 上午12:35
 **/

@Data
public class CourseExcelList {

    private final List<StudentCourse> studentCourseList = new ArrayList<>();
    private final List<User> userList = new ArrayList<>();
    private final Map<String, String> studentMap = new HashMap<>();

    public CourseExcelList(String fileName) throws Exception {
        Map<String, Integer> headerMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0); // 获取表头行

            // 创建表头与列索引的映射
            for (Cell cell : headerRow) {
                String headerName = cell.getStringCellValue();
                int columnIndex = cell.getColumnIndex();
                headerMap.put(headerName, columnIndex);
            }

            // 遍历所有行
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }

                // 根据表头映射获取对应列的值
                String studentId = getCellValue(row.getCell(headerMap.get("学号")));
                String name = getCellValue(row.getCell(headerMap.get("姓名")));
                String courseNumber = getCellValue(row.getCell(headerMap.get("课程号")));
                String courseName = getCellValue(row.getCell(headerMap.get("课程名")));
                double scoreValue = Double.parseDouble(getCellValue(row.getCell(headerMap.get("成绩"))));
                double creditValue = Double.parseDouble(getCellValue(row.getCell(headerMap.get("学分"))));
                String courseType = getCellValue(row.getCell(headerMap.get("选课属性")));
                String yearTerm = getCellValue(row.getCell(headerMap.get("学年学期")));
                String department = getCellValue(row.getCell(headerMap.get("院系")));
                String grade = getCellValue(row.getCell(headerMap.get("年级")));
                String clazz = getCellValue(row.getCell(headerMap.get("班级")));

                int enrolmentYear = Integer.parseInt(grade);
                int currentYear = Integer.parseInt(yearTerm.substring(0,4));
                String semester = yearTerm.substring(4,5);
                int studentYear = currentYear-enrolmentYear;
                Term term = determineTerm(studentYear, semester);

                User user = new User(studentId, studentId, name, "", Role.STUDENT);
                userList.add(user);

                // 根据班级信息将学生学号添加到studentMap中
                if (!studentMap.containsKey(studentId)) {
                    studentMap.put(studentId, clazz);
                }

                StudentCourse studentCourse = new StudentCourse(
                        new StudentCourseId(studentId, courseName,term),
                        user,
                        new BigDecimal(scoreValue),
                        creditValue,
                        courseType.equals("必修") ? CourseStats.REQUIRED : courseType.equals("限选")?CourseStats.LIMITED:CourseStats.ELECTIVE
                );

                studentCourseList.add(studentCourse);
            }
        }
    }

    private Term determineTerm(int studentYear, String semester) {
        boolean isFall = semester.equals("春");

        return switch (studentYear) {
            case 0 -> Term.FRESHMAN_FALL;
            case 1 -> isFall ? Term.FRESHMAN_SPRING: Term.SOPHOMORE_FALL;
            case 2 -> isFall ? Term.SOPHOMORE_SPRING:Term.JUNIOR_FALL;
            case 3 -> isFall ? Term.JUNIOR_SPRING:Term.SENIOR_FALL;
            case 4 -> Term.SOPHOMORE_SPRING;
            default -> throw new IllegalArgumentException("Invalid student year");
        };
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
