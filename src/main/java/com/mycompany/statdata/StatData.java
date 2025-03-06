package com.mycompany.statdata;

import java.util.Map;


public class StatData {

    public static void main(String[] args) {  
        String inputFileName = "C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\Лаба_1 образцы данных.xlsx";
        Map<String, double[]> data = Reader.makeHashMapFromFile(inputFileName);
        System.out.println(data.get("X")[99]);
    }
        
        
}
