package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    private WebDriver driver;

    @FindBy(id = "sign-username")
    private WebElement usernameInput;

    @FindBy(id = "sign-password")
    private WebElement passwordInput;

    @FindBy(css = "button[onclick='register()']")
    private WebElement signupConfirmButton;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillSignupForm(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public void submitSignupForm() {
        signupConfirmButton.click();
    }
}
