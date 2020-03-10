/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Binh
 */
public final class Utility {
    
    // Load the value corresponsing to the given key
    public static String loadFromFile(String key) {
        String toReturn = null;
        try (InputStream in = new FileInputStream("src/test/java/config.properties")) {
            Properties prop = new Properties();
            prop.load(in);
                    
            toReturn = String.valueOf(prop.getProperty(key));
                    
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return toReturn;
    }
    
    public static void saveToFile(String key, String val) {
        try (OutputStream out = new FileOutputStream("src/test/java/config.properties")) {
            Properties prop = new Properties();
            prop.setProperty(key, val);
            prop.store(out, null);
            
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    
    
}
