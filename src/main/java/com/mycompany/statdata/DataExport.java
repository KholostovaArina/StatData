package com.mycompany.statdata;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataExport {

    public void DataExport(List<Map<String, Object>> data, String filePath) {
        Workbook workbook = new XSSFWorkbook(); 
        Sheet sheet = workbook.createSheet("Statistical Data"); // Создаем лист

        if (data.isEmpty()) {
            throw new IllegalArgumentException("The data list cannot be empty");
        }

        // Создаем заголовок, используя ключи из первой мапы
        Row headerRow = sheet.createRow(0);
        Map<String, Object> firstEntry = data.get(0);
        int headerCellIndex = 0;
        for (String key : firstEntry.keySet()) {
            Cell cell = headerRow.createCell(headerCellIndex++);
            cell.setCellValue(key);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        // Записываем данные
        int rowIndex = 1;
        for (Map<String, Object> dataMap : data) {
            Row row = sheet.createRow(rowIndex++);
            int cellIndex = 0;
            for (Object value : dataMap.values()) {
                Cell cell = row.createCell(cellIndex++);
                if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                } else {
                    cell.setCellValue(value.toString());
                }
            }
        }

        // Автоматически подстраиваем ширину колонок
        for (int i = 0; i < firstEntry.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Записываем файл
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    // Метод для создания стиля заголовка
    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
