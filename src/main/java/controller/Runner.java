/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.MainFrame;

/**
 *
 * @author Binh
 */
public class Runner {
    
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        
        CalendarController controller = new CalendarController(frame);
    }
}
