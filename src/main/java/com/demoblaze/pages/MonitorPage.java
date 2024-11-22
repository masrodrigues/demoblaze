package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MonitorPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Apple monitor 24')]")
    private WebElement firstMonitor;

    @FindBy(css = ".btn-success")
    private WebElement addToCartButton;

    public MonitorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFirstMonitor() {
        firstMonitor.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }
}
