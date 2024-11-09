package org.example.back.util;

import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.back.common.Term;
import org.example.back.entity.ComprehensiveEvaluationId;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Canary
 * @version 1.0.0
 * @title OtherCourseExcelList
 * @description 读取小分数据
 * @creat 2024/11/9 下午6:04
 **/
@Data
public class OtherCourseExcelList {

    private final Map<ComprehensiveEvaluationId, Double> moralityMap = new HashMap<>();
    private final Map<ComprehensiveEvaluationId, Double> otherScore1Map = new HashMap<>();
    private final Map<ComprehensiveEvaluationId, Double> otherScore2Map = new HashMap<>();

    public OtherCourseExcelList(String fileName, Term term) throws IOException {

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
                double otherScore1 = Double.parseDouble(getCellValue(row.getCell(headerMap.get("其他得分1"))));
                double otherScore2 = Double.parseDouble(getCellValue(row.getCell(headerMap.get("其他得分2"))));

                ComprehensiveEvaluationId evaluationId = new ComprehensiveEvaluationId(studentId, term);

                if (!otherScore1Map.containsKey(evaluationId)) {
                    otherScore1Map.put(evaluationId, otherScore1);
                }

                if (!otherScore2Map.containsKey(evaluationId)) {
                    otherScore2Map.put(evaluationId, otherScore2);
                }

                if (!moralityMap.containsKey(evaluationId)) {
                    moralityMap.put(evaluationId, getMoralityScore());
                }

            }
        }
    }

    private static double getMoralityScore(){
        Random random = new Random();
        return Math.round((random.nextDouble() * (15.00 - 14.88) + 14.88) * 100.0) / 100.0;
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
