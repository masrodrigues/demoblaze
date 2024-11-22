package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "country")
    private WebElement countryInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "card")
    private WebElement cardInput;

    @FindBy(id = "month")
    private WebElement monthInput;

    @FindBy(id = "year")
    private WebElement yearInput;

    @FindBy(css = "button.btn.btn-primary[onclick='purchaseOrder()']")
    private WebElement purchaseButton;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOrderDetails(String name, String country, String city, String card, String month, String year) {
        nameInput.sendKeys(name);
        countryInput.sendKeys(country);
        cityInput.sendKeys(city);
        cardInput.sendKeys(card);
        monthInput.sendKeys(month);
        yearInput.sendKeys(year);
    }

    public void clickPurchase() {
        purchaseButton.click();
    }
}
