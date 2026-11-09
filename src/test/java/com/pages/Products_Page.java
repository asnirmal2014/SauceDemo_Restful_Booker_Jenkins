package com.pages;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Products_Page
{
    public WebDriver driver = null;
    public WebDriverWait wait = null;

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(className = "product_sort_container")
    private WebElement productSortDropdown;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public Products_Page(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int getProductsCount()
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        return products.size();
    }

    public void sortProductsByNameAscending()
    {
        wait.until(ExpectedConditions.elementToBeClickable(productSortDropdown));
        Select select = new Select(productSortDropdown);
        select.selectByValue("az");
    }

    public List<String> getProductNames()
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));

        List<String> names = new ArrayList<String>();
        
        for (WebElement productName : productNames)
        {
            names.add(productName.getText());
        }

        return names;
    }

    public void sortProductsByPriceLowToHigh()
    {
        wait.until(ExpectedConditions.elementToBeClickable(productSortDropdown));

        Select select = new Select(productSortDropdown);

        select.selectByValue("lohi");
    }

    public List<BigDecimal> getProductPrices()
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));

        List<BigDecimal> prices = new ArrayList<BigDecimal>();

        for (WebElement productPrice : productPrices)
        {
            String priceText = productPrice.getText();
            priceText = priceText.replace("$", "").trim();
            prices.add(new BigDecimal(priceText));
        }

        return prices;
    }
}
