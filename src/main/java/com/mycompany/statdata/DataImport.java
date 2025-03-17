package com.mycompany.statdata;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataImport {

    public static Map<String, double[]> makeHashMapFromFile() {
        Map<String, double[]> resultMap = new HashMap<>();
        
        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String inputFileName = selectedFile.getAbsolutePath();
            
            try (FileInputStream fis = new FileInputStream(inputFileName);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet inputSheet = workbook.getSheetAt(0);

                if (inputSheet.getPhysicalNumberOfRows() == 0) {
                    return resultMap; // Если лист пустой, возвращаем пустую карту
                }

                int columnCount = inputSheet.getRow(0).getPhysicalNumberOfCells();
                int rowCount = inputSheet.getPhysicalNumberOfRows();
                System.out.println(columnCount); // 3
                System.out.println(rowCount); // 101

                for (int k = 0; k < columnCount; k++) {
                    double[] selection = new double[rowCount - 1];
                    for (int i = 0; i < rowCount - 1; i++) {
                        try {
                            selection[i] = inputSheet.getRow(i + 1).getCell(k).getNumericCellValue();
                        } catch (Exception e) {
                            selection[i] = 1000;
                        }
                    }

                    resultMap.put(inputSheet.getRow(0).getCell(k).toString(), selection);
                    System.out.println(inputSheet.getRow(0).getCell(k).toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
 
        return resultMap;
    }
}

