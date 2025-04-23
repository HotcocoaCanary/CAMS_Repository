package org.example.back.util;


import org.apache.poi.ss.usermodel.*;
import org.example.back.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelOp {
    private final ScoreMapper scoreMapper;
    @Autowired
    public ExcelOp(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    public void initCourseSelection(String fileName){
        // 使用try-with-resources自动关闭资源
        try (FileInputStream file = new FileInputStream("src/main/java/org/example/back/temp/"+fileName)) {
            // 1. 创建Workbook对象（自动识别xls/xlsx）
            Workbook workbook = WorkbookFactory.create(file);

            // 2. 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 3. 创建日期格式化工具
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // 4. 跳过标题行
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // 跳过标题行1
            if (rowIterator.hasNext()) rowIterator.next(); // 跳过标题行2

//            int i = 0;//计数用

            // 5. 遍历数据行
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // 处理空行
                if (row == null) continue;

                // 6. 读取各列数据
                String studentId = getCellValue(row.getCell(0));
                String courseId = getCellValue(row.getCell(2));
                String semester = getCellValue(row.getCell(11));
                String year = getCellValue(row.getCell(15));

                String semesterId = null;


                int i = Integer.parseInt(year)-Integer.parseInt(semester.substring(0,4));
                semesterId = switch (i) {
                    case 0 -> "大一上";
                    case 1 -> "大一下";
                    case 2 -> "大二上";
                    case 3 -> "大二下";
                    case 4 -> "大三上";
                    case 5 -> "大三下";
                    case 6 -> "大四上";
                    case 7 -> "大四下";
                    default -> semesterId;
                };

//                System.out.println(studentId+","+courseId+","+semesterId);
                //7.调用mapper层
                scoreMapper.initCourseSelection(studentId,courseId,semesterId);
            }


            // 8. 关闭workbook（try-with-resources会自动处理，这里显式关闭更安全）
            workbook.close();

        } catch (Exception e) {
            System.err.println("读取Excel失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void readScore(String fileName){
        // 使用try-with-resources自动关闭资源
        try (FileInputStream file = new FileInputStream("src/main/java/org/example/back/temp/"+fileName)) {
            // 1. 创建Workbook对象（自动识别xls/xlsx）
            Workbook workbook = WorkbookFactory.create(file);

            // 2. 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 3. 创建日期格式化工具
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // 4. 跳过标题行
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // 跳过标题行1
            if (rowIterator.hasNext()) rowIterator.next(); // 跳过标题行2

//            int i = 0;//计数用

            // 5. 遍历数据行
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // 处理空行
                if (row == null) continue;

                // 6. 读取各列数据
                String studentId = getCellValue(row.getCell(0));
                String courseId = getCellValue(row.getCell(2));
                String semester = getCellValue(row.getCell(11));
                String year = getCellValue(row.getCell(15));

                String semesterId = null;


                int i = Integer.parseInt(year)-Integer.parseInt(semester.substring(0,4));
                semesterId = switch (i) {
                    case 0 -> "大一上";
                    case 1 -> "大一下";
                    case 2 -> "大二上";
                    case 3 -> "大二下";
                    case 4 -> "大三上";
                    case 5 -> "大三下";
                    case 6 -> "大四上";
                    case 7 -> "大四下";
                    default -> semesterId;
                };
                String strScore = getCellValue(row.getCell(5));
                double score = Double.parseDouble(strScore);
                String status;

                List<String> reBuild = scoreMapper.getRebuild(studentId,courseId);
                if(reBuild.size() > 1 && score >= 60){
                    status = "重修";
                }else if (reBuild.size() == 1 && score >= 60){
                    status = "正常";
                }else status = "挂科";


                //7.调用mapper层
                scoreMapper.setScoreToDb(studentId,courseId,semesterId,score,status);
            }


            // 8. 关闭workbook（try-with-resources会自动处理，这里显式关闭更安全）
            workbook.close();

        } catch (Exception e) {
            System.err.println("读取Excel失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 通用单元格值获取方法
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> handleFormulaCell(cell);
            default -> "";
        };
    }

    // 处理数字类型
    private static double getNumericValue(Cell cell) {
        return (cell != null && cell.getCellType() == CellType.NUMERIC) ?
                cell.getNumericCellValue() : 0;
    }

    // 处理日期类型
    private static String getDateValue(Cell cell, SimpleDateFormat dateFormat) {
        if (cell == null) return "无日期";

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return dateFormat.format(cell.getDateCellValue());
        }
        return "无效日期";
    }

    // 处理布尔类型
    private static boolean getBooleanValue(Cell cell) {
        return cell != null &&
                cell.getCellType() == CellType.BOOLEAN &&
                cell.getBooleanCellValue();
    }

    // 处理公式单元格
    private static String handleFormulaCell(Cell cell) {
        try {
            return String.valueOf(cell.getNumericCellValue());
        } catch (IllegalStateException e) {
            return cell.getRichStringCellValue().toString();
        }
    }
    public String getSemester() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        // 判断学期
        String semester;
        if (month >= 3 && month <= 7) {
            semester = "SPRING";
        } else if (month >= 9) {
            semester = "FALL";
        } else {
            // 处理跨年月份：1月、2月属于前一年的秋季学期；8月可自定义
            if (month == 1 || month == 2) {
                semester = "FALL";
                year--;  // 属于前一年
            } else {  // 8月可自定义为春季或秋季
                semester = "SPRING";  // 假设8月属于春季学期
            }
        }

        return year +"-"+ semester;
    }

}
