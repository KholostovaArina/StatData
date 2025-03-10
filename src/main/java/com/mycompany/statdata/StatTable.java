
package com.mycompany.statdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StatTable {
    public static List<Map<String, Object>> generateStatTable(Map<String, double[]> dataSets) {
        
        List<Map<String, Object>> table = new ArrayList<>();
      
        for (Map.Entry<String, double[]> entry : dataSets.entrySet()) {
            String sampleName = entry.getKey();
            double[] data = entry.getValue();
            
            Map<String, Object> stats = StatIndicators.calculateStatIndicators(data);
            
           stats.put("sample", sampleName);
          
            table.add(stats);
            System.out.println(stats.toString());
        }
        return table;
    }
}
