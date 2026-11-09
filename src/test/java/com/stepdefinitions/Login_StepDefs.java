package com.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import com.pages.Login_Page;
import com.utility.ConfigReader;
import com.utility.MyWebDriverManager;
import com.utility.NavigationAssertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*
 * Only method calling happens in the Step Definition file.
 * Method definitions and Selenium operations happen in the Page files.
 */

public class Login_StepDefs
{
    private Login_Page loginPage = null;

    /*
     * Lazy initialization:
     * Login_Page is created only when required.
     */

    private Login_Page loginPage()
    {
        if (loginPage == null)
        {
            loginPage = new Login_Page(MyWebDriverManager.getDriver());
        }

        return loginPage;
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage()
    {
        loginPage().openLoginPage(ConfigReader.get("ui.base.url"));
    }

    @When("I log in as {string} with password {string}")
    public void iLogInAsWithPassword(String user, String pass)
    {
        loginPage().login(user, pass);
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage()
    {
        NavigationAssertions.verifyUrlContains("inventory");
    }

    @Then("I should see the login error message")
    public void iShouldSeeTheLoginErrorMessage()
    {
        String actualMessage = loginPage().getErrorMessage();

        Assertions.assertFalse(
            actualMessage.isEmpty(),
            "No error message was displayed on invalid login"
        );
    }

    /*
     * Reusable login step for other feature files.
     */
    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String user)
    {
        Login_Page page = loginPage();
        page.openLoginPage(ConfigReader.get("ui.base.url"));
        page.login(user, "secret_sauce");

        NavigationAssertions.verifyUrlContains("inventory");
    }
}
