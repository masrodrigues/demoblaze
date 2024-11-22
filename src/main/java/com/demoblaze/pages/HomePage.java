package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "signin2")
    private WebElement signupButton;

    @FindBy(xpath = "//a[@id='itemc' and contains(text(),'Monitors')]")
    private WebElement clickCategory;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openSignupForm() {
        signupButton.click();
    }

    public void clickCategory(String category) {
        clickCategory.click();
    }
}
