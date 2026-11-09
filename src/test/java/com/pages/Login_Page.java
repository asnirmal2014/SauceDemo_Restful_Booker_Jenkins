package com.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* Only method calling happens in the Step Definition file. Method definitions and Selenium operations happen in the Page files. */
public class Login_Page
{
    public WebDriver driver = null;
    public WebDriverWait wait = null;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public Login_Page(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public void openLoginPage(String baseUrl)
    {
        driver.get(baseUrl);
    }

    public void login(String user, String pass)
    {
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        /*
         * No URL verification here.
         * Login can be successful or unsuccessful.
         * Navigation verification is handled separately.
         */
    }

    public String getErrorMessage()
    {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}
