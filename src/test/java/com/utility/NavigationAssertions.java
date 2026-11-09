package com.utility;

import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationAssertions
{
    public static void verifyUrlContains(String expectedUrl)
    {
        WebDriver driver = MyWebDriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlMatched = wait.until(ExpectedConditions.urlContains(expectedUrl));
		Assertions.assertTrue(isUrlMatched, "Navigation verification failed. Expected URL to contain: " + expectedUrl);
    }
}
