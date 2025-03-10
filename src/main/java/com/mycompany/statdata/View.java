
package com.mycompany.statdata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View {
    protected JFrame frame;
    public  View(){
        
        frame = new JFrame("Интерфейс для создания статистических данных из вашего файла");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLayout(new BorderLayout());

        JButton button = new JButton("Прочитать данные");        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(frame, "Файл успешно прочитан");
            }
        });

        frame.add(button, BorderLayout.CENTER);

        //frame.setVisible(true);
    }
}
