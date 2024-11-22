package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "loginusername")
    private WebElement usernameInput;

    @FindBy(id = "loginpassword")
    private WebElement passwordInput;

    @FindBy(css = "button[onclick='logIn()']")
    private WebElement submitButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginForm() {
        loginButton.click();
    }

    public void fillLoginForm(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public void submitLogin() {
        submitButton.click();
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }
}
