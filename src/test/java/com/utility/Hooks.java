package com.utility;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.opera.OperaDriver;         // Deprecated
//import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * Traditional Java Style.
 *
 * Responsibilities:
 * 1. Initialize the browser before each scenario.
 * 2. Capture screenshots when a scenario fails.
 * 3. Attach screenshots to the Cucumber HTML report.
 * 4. Close the browser after each scenario.
 */
public class Hooks
{
    @Before
    public void setUp()
    {
        MyWebDriverManager.startBrowser();
    }

    @After
    public void tearDown(Scenario scenario)
    {
        WebDriver driver = MyWebDriverManager.getDriver();

        try
        {
            if (scenario.isFailed() && driver != null)
            {
                System.out.println("##### Failed Scenario: " + scenario.getName());

                // Capture screenshot as PNG byte array.
                // This expression is downcasting (or more specifically, a type cast from a supertype/interface to a subtype/interface).
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Attach screenshot to Cucumber HTML report. (target/cucumber/report.html)
                scenario.attach(screenshot,"image/png",scenario.getName());
            }
        }
        catch (Exception e)
        {
            System.out.println("##### Screenshot capture failed: " + e.getMessage());

            e.printStackTrace();
        }
        finally
        {
            // Close browser after every scenario.
            MyWebDriverManager.closeBrowser();
        }
    }
}
