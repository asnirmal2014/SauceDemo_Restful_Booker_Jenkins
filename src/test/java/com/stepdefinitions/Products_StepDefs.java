package com.stepdefinitions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import com.pages.Products_Page;
import com.utility.MyWebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*
 * Only method calling happens in the Step Definition file.
 * Method definitions and Selenium operations happen in the Page files.
 */

public class Products_StepDefs
{
    private Products_Page productsPage = null;

    /*
     * Lazy initialization:
     * Products_Page is created only when required.
     */

    private Products_Page productsPage()
    {
        if (productsPage == null)
        {
            productsPage = new Products_Page(MyWebDriverManager.getDriver());
        }

        return productsPage;
    }

    @Then("I should see {int} products displayed")
    public void iShouldSeeProductsDisplayed(int expectedCount)
    {
        int actualCount = productsPage().getProductsCount();

        Assertions.assertEquals(expectedCount, actualCount, "The number of products displayed is incorrect");
    }

    @When("I sort products by name in ascending order")
    public void iSortProductsByNameInAscendingOrder()
    {
        productsPage().sortProductsByNameAscending();
    }

    @Then("products should be displayed in alphabetical order")
    public void productsShouldBeDisplayedInAlphabeticalOrder()
    {
        List<String> actualNames = productsPage().getProductNames();

        List<String> expectedNames = new ArrayList<String>(actualNames);

        Collections.sort(expectedNames);

        Assertions.assertEquals(
            expectedNames,
            actualNames,
            "Products are not displayed in alphabetical order"
        );
    }

    @When("I sort products by price from low to high")
    public void iSortProductsByPriceFromLowToHigh()
    {
        productsPage().sortProductsByPriceLowToHigh();
    }

    @Then("products should be displayed from lowest to highest price")
    public void productsShouldBeDisplayedFromLowestToHighestPrice()
    {
        List<BigDecimal> actualPrices = productsPage().getProductPrices();

        List<BigDecimal> expectedPrices = new ArrayList<BigDecimal>(actualPrices);

        Collections.sort(expectedPrices);

        Assertions.assertEquals(
            expectedPrices,
            actualPrices,
            "Products are not displayed from lowest to highest price"
        );
    }
}
