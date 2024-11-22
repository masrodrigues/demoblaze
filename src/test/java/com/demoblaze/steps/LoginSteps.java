package com.demoblaze.steps;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ExtentTest test;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);


    }

    @After
    public void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DriverManager.quitDriver();
    }



    @Quando("eu abro o formulário de login")
    public void euAbroOFormularioDeLogin() {
        loginPage.openLoginForm();
    }

    @E("preencho o formulário de login com nome de usuário {string} e senha {string}")
    public void preenchoOFormularioDeLoginComNomeDeUsuarioESenha(String username, String password) {
        loginPage.fillLoginForm(username, password);
    }

    @Quando("confirmo o login")
    public void confirmoOLogin() {
        loginPage.submitLogin();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Entao("devo ver meu nome de usuário como {string}")
    public void devoVerMeuNomeDeUsuarioComo(String expectedUsername) {
        String actualWelcomeMessage = loginPage.getWelcomeMessage();


        System.out.println("Mensagem recebida: " + actualWelcomeMessage);


        assertTrue(actualWelcomeMessage.contains(expectedUsername),
                "Mensagem de boas-vindas incorreta: " + actualWelcomeMessage);


        try {
            Thread.sleep(2000); // Espera fixa de 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
