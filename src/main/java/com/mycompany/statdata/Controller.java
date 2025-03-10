package com.mycompany.statdata;

import java.util.List;
import java.util.Map;

public class Controller {
    public Controller(){
        View view = new View();
        view.frame.setVisible(true);
             
       // String inputFileName= view.getFileName("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\Лаба_1 образцы данных.xlsx");
        Map<String, double[]> data = DataImport.makeHashMapFromFile("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\Лаба_1 образцы данных.xlsx");    
        List<Map<String, Object>> statData = StatTable.generateStatTable(data);
         
        //передать стат дату в view
        String outputFileName;
        DataExport export = new DataExport(statData, outputFileName);
    }
}
