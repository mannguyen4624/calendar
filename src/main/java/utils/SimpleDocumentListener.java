/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
 * @author Binh
 */
public interface SimpleDocumentListener extends DocumentListener {
    void update(DocumentEvent e);
    @Override
    default void insertUpdate(DocumentEvent e) {
        update(e);
    }
    @Override
    default void removeUpdate(DocumentEvent e) {
        update(e);
    }
    @Override
    default void changedUpdate(DocumentEvent e) {
        update(e);
    }
}
