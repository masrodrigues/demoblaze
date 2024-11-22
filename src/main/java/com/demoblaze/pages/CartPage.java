package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;

    @FindBy(id = "cartur")
    private WebElement cartButton;

    @FindBy(css = "button.btn.btn-success[data-target='#orderModal']")
    private WebElement placeOrderButton;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCart() {
        cartButton.click();
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }
}
