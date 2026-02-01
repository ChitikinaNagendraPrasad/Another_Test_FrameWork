package com.optim.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader
{
    public Properties prop= new Properties();
    public ConfigReader()
    {
        try
        {
            String configFilePath =  System.getProperty("user.dir")+"\\Config\\config.properties";            
            FileInputStream fis = new FileInputStream(configFilePath);            
            prop.load(fis);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getBrowser()
    {
        String returnValue=null;
        try
        {
            returnValue=prop.getProperty("browser");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    public String getBaseURL()
    {
        String returnValue=null;
        try
        {
            returnValue=prop.getProperty("baseURL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return returnValue;
    }
    

}
