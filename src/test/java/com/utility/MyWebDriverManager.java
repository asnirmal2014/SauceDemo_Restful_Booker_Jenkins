package com.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.opera.OperaDriver;         // Deprecated
//import io.github.bonigarcia.wdm.WebDriverManager;

/* Traditional Java Style */
/* Initializes, returns and closes the browser */
public class MyWebDriverManager 
{
    private static WebDriver driver = null;

    public static void startBrowser() 
    {
        if (driver == null) 
        {
            String browser = ConfigReader.get("browser").toLowerCase();

            switch (browser) 
            {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    
                    if (Boolean.parseBoolean(ConfigReader.get("headless"))) 
                    {
                        options.addArguments("--headless=new");
                    }
                    
                    driver = new ChromeDriver(options);
                    break;
            }

            // Browser configuration
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("timeout.seconds")));
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
            try 
            {
                driver.quit();
            } 
            finally 
            {
                driver = null;
            }
        }
    }
}