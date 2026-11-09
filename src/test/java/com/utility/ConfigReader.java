package com.utility;

import java.io.InputStream;
import java.util.Properties;

/* Traditional Java Style */
/* Reads browser and timeout settings */
public class ConfigReader 
{
    private static final Properties props = new Properties();

    static 
    {
        InputStream in = null;

        try 
        {
            in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");

            if (in == null) 
            {
                throw new IllegalStateException("config.properties not found");
            }

            props.load(in);
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Failed to load config.properties", e);
        }
        finally 
        {
            if (in != null) 
            {
                try 
                {
                    in.close();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String get(String key) 
    {
        return System.getProperty(key, props.getProperty(key));
    }

    public static int getInt(String key) 
    {
        return Integer.parseInt(get(key));
    }
}