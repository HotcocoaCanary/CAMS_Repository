package org.example.back.util;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.back.entity.ComprehensiveEvaluation;
import org.apache.poi.ss.usermodel.*;

import java.io.Serializable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Canary
 * @version 1.0.0
 * @title CEExcelBuilder
 * @description 综测表格构建器
 * @creat 2024/11/13 下午8:31
 **/

@Getter
public class CEExcelBuilder implements Serializable {

    private final transient Workbook workbook;

    public CEExcelBuilder(Map<String, List<ComprehensiveEvaluation>> data) {
        workbook = new XSSFWorkbook();
        createSheets(data);
    }

    private void createSheets(Map<String, List<ComprehensiveEvaluation>> data) {
        Set<Map.Entry<String, List<ComprehensiveEvaluation>>> entries = data.entrySet();
        for (Map.Entry<String, List<ComprehensiveEvaluation>> entry : entries) {
            Sheet sheet = workbook.createSheet(entry.getKey());
            fillSheetData(sheet, entry.getValue());
        }
    }

    private void fillSheetData(Sheet sheet, List<ComprehensiveEvaluation> evaluations) {
        // 创建表头
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "学号", "姓名", "班级", "思想品德表现", "德育班级排名", "体育成绩", "学习成绩", "智育班级排名",
                "挂科门次", "其他得分1", "其他得分2", "总分", "总分班级排名", "本人签字"
        };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 初始化排名和总分计算
        Map<BigDecimal, List<ComprehensiveEvaluation>> moralityRanking = evaluations.stream()
                .collect(Collectors.groupingBy(ComprehensiveEvaluation::getMorality));
        Map<BigDecimal, List<ComprehensiveEvaluation>> academicRanking = evaluations.stream()
                .collect(Collectors.groupingBy(ComprehensiveEvaluation::getAcademicPerformance));

        // 对德育和学习成绩进行排序
        List<BigDecimal> sortedMorality = moralityRanking.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        List<BigDecimal> sortedAcademic = academicRanking.keySet().stream().sorted(Comparator.reverseOrder()).toList();

        // 填充数据
        int rowNum = 1;
        for (ComprehensiveEvaluation evaluation : evaluations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(evaluation.getId().getStudentID()); // 学号
            row.createCell(1).setCellValue(evaluation.getStudentID().getName()); // 姓名
            row.createCell(2).setCellValue(sheet.getSheetName()); // 班级

            // 思想品德表现
            Cell moralityCell = row.createCell(3);
            moralityCell.setCellValue(evaluation.getMorality().doubleValue());
            moralityCell.setCellStyle(getDecimalStyle(workbook)); // 设置保留两位小数的样式

            // 德育班级排名
            int moralityRank = sortedMorality.indexOf(evaluation.getMorality()) + 1;
            row.createCell(4).setCellValue(moralityRank);

            // 体育成绩
            Cell sportsCell = row.createCell(5);
            sportsCell.setCellValue(evaluation.getSportsAchievement().doubleValue());
            sportsCell.setCellStyle(getDecimalStyle(workbook)); // 设置保留两位小数的样式

            // 学习成绩
            Cell academicCell = row.createCell(6);
            academicCell.setCellValue(evaluation.getAcademicPerformance().doubleValue());
            academicCell.setCellStyle(getDecimalStyle(workbook)); // 设置保留两位小数的样式

            // 智育班级排名
            int academicRank = sortedAcademic.indexOf(evaluation.getAcademicPerformance()) + 1;
            row.createCell(7).setCellValue(academicRank);

            // 挂科门次，这里暂时设置为空
            row.createCell(8).setCellValue(evaluation.getFailedCourses());

            // 其他得分1
            Cell otherScore1Cell = row.createCell(9);
            otherScore1Cell.setCellValue(evaluation.getOtherScore1().doubleValue());
            otherScore1Cell.setCellStyle(getDecimalStyle(workbook)); // 设置保留两位小数的样式

            // 其他得分2
            Cell otherScore2Cell = row.createCell(10);
            otherScore2Cell.setCellValue(evaluation.getOtherScore2().doubleValue());
            otherScore2Cell.setCellStyle(getDecimalStyle(workbook)); // 设置保留两位小数的样式

            // 总分
            BigDecimal totalScore = evaluation.getMorality()
                    .add(evaluation.getSportsAchievement())
                    .add(evaluation.getAcademicPerformance())
                    .add(evaluation.getOtherScore1())
                    .add(evaluation.getOtherScore2());
            Cell totalScoreCell = row.createCell(11);
            totalScoreCell.setCellValue(totalScore.doubleValue());
            totalScoreCell.setCellStyle(getDecimalStyle(workbook)); // 设置保留两位小数的样式

            // 总分班级排名，这里需要根据总分重新计算排名
            Map<BigDecimal, List<ComprehensiveEvaluation>> totalScoreRanking = evaluations.stream()
                    .collect(Collectors.groupingBy(e -> e.getMorality()
                            .add(e.getSportsAchievement())
                            .add(e.getAcademicPerformance())
                            .add(e.getOtherScore1())
                            .add(e.getOtherScore2())));
            List<BigDecimal> sortedTotalScores = totalScoreRanking.keySet().stream().sorted(Comparator.reverseOrder()).toList();
            int totalScoreRank = sortedTotalScores.indexOf(totalScore) + 1;
            row.createCell(12).setCellValue(totalScoreRank);

            // 本人签字，这里暂时设置为空
            row.createCell(13).setCellValue("");
        }
    }

    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    private CellStyle getDecimalStyle(Workbook workbook) {
        // 创建一个数据格式化对象，设置为保留两位小数
        DataFormat format = workbook.createDataFormat();
        short decimalFormat = format.getFormat("0.00");

        // 创建单元格样式
        CellStyle decimalStyle = workbook.createCellStyle();
        decimalStyle.setDataFormat(decimalFormat);

        return decimalStyle;
    }

}
