package com.apps.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils
{
    public static String getValueByName(String filepath, String proname){
        String value = "";
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath); 
        Properties prop = new Properties();
        try
        {
            if(null!=in){
                prop.load(in);
                value = prop.getProperty(proname);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return value;
    }
    
}
