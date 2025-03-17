package com.mycompany.statdata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Controller {
    private  Map<String, double[]> data = new HashMap<>();
    private  List<Map<String, Object>> statData = new ArrayList<>();
    private  List<Map<String, Object>> covData = new ArrayList<>();
    
    public Controller(){
        View view = new View();
        view.frame.setVisible(true);
        
        view.inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.inputButton.setEnabled(false);
                view.processButton.setEnabled(true);                
                data = DataImport.makeHashMapFromFile();           
            }
        });
        
        
        view.processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view.frame, "Данные обработаны успешно!",
                                              "Статус обработки", JOptionPane.INFORMATION_MESSAGE);
                view.processButton.setEnabled(false);
                view.writeButton.setEnabled(true);
                
                statData = StatTable.generateStatTable(data);
                covData = StatTable.generateTableK_Cov(data);
            }
        });

        view.writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Сохранить как...");
                int userSelection = fileChooser.showSaveDialog(view.frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String outputFileName = fileToSave.getAbsolutePath();

                    JOptionPane.showMessageDialog(view.frame, "Данные записаны успешно!",
                            "Статус обработки", JOptionPane.INFORMATION_MESSAGE);
                    view.writeButton.setEnabled(false);
                    DataExport ex = new DataExport();

                    ex.export(statData, outputFileName, "Статистические показатели");
                    ex.export(covData, outputFileName, "Таблица коэффициентов ковариации");

                    // Сохраняем все изменения в файл.
                    ex.saveToFile(outputFileName);

// Закрываем Workbook в самом конце.
                    ex.closeWorkbook();
                }
            }
        });
       
        
    }
}
