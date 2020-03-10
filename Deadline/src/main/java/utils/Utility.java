/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author Binh
 */
public final class Utility {
    
    // Load the value corresponsing to the given key
    public static String loadFromFile(String key) {
        try {
        PropertiesConfiguration config = new PropertiesConfiguration("src/test/java/config.properties");
        return config.getString(key);
        }
        catch (ConfigurationException e) {
            System.out.println("Couldn't open config file");
            return null;
        }   
    }
    
    public static void saveToFile(String key, String val) {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("src/test/java/config.properties");
            config.setProperty(key, val);
            config.save();
        } catch (ConfigurationException e) {
            System.out.println("Couldn't open config file");
        }
    }
}
