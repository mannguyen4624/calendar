/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import view.CalendarPanel;
import view.EventPanel;


/**
 *
 * @author itzu
 */
public class StudentView {
    private JFrame frame;
    private CalendarPanel cPanel;
    
    public StudentView() {
        frame = new MainFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        cPanel = new CalendarPanel();
        
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup().addComponent(cPanel)));
        
        frame.getContentPane().setLayout(layout);
    }
    
}


