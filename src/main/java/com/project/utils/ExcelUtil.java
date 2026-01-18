package com.project.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {

    public static List<Map<String, String>> getTestData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0); // 첫 번째 줄 (컬럼명)

            // 데이터가 있는 두 번째 줄(index 1)부터 끝까지 반복
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String columnName = headerRow.getCell(j).getStringCellValue();
                    Cell cell = currentRow.getCell(j);

                    // 셀의 데이터 타입에 상관없이 문자열로 변환 (숫자, 콤마 포함 문자 등)
                    String cellValue = "";
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue().toString();
                                } else {
                                    cellValue = String.valueOf((int) cell.getNumericCellValue());
                                }
                                break;
                            case FORMULA:
                                cellValue = cell.getCellFormula();
                                break;
                            default:
                                cellValue = "";
                        }
                    }
                    dataMap.put(columnName, cellValue);
                }
                dataList.add(dataMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
