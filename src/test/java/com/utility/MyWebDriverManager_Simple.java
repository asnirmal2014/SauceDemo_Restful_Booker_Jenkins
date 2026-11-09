package com.utility;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.opera.OperaDriver;         // Deprecated
//import io.github.bonigarcia.wdm.WebDriverManager;

/* Traditional Java Style */
/* Initializes, returns and closes the browser */
public class MyWebDriverManager_Simple 
{
    private static WebDriver driver = null;

    public static void startBrowser() 
    {
        if (driver == null) 
        {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().deleteAllCookies();
        }
    }
    
    public static WebDriver getDriver() 
    {
        return driver;
    }

    public static void closeBrowser() 
    {
        if (driver != null) 
        {
            driver.quit();
            driver = null;
        }
    }
}