package com.demoblaze.steps;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utils.DriverManager;
import com.demoblaze.utils.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ExtentTest test;

    @Before
    public void setup() {
        ExtentReportManager.setupReport();
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        test = ExtentReportManager.createTest("Teste de Login");
        test.info("Configuração inicial concluída.");
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
        ExtentReportManager.flushReport();
    }

    @Quando("eu abro o formulário de login")
    public void euAbroOFormularioDeLogin() {
        loginPage.openLoginForm();
        test.info("Formulário de login aberto.");
    }

    @E("preencho o formulário de login com nome de usuário {string} e senha {string}")
    public void preenchoOFormularioDeLoginComNomeDeUsuarioESenha(String username, String password) {
        loginPage.fillLoginForm(username, password);
        test.info("Formulário de login preenchido com usuário: " + username);
    }

    @Quando("confirmo o login")
    public void confirmoOLogin() {
        loginPage.submitLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        test.info("Login confirmado.");
    }

    @Entao("devo ver meu nome de usuário como {string}")
    public void devoVerMeuNomeDeUsuarioComo(String expectedUsername) {
        String actualWelcomeMessage = loginPage.getWelcomeMessage();
        test.info("Mensagem de boas-vindas recebida: " + actualWelcomeMessage);

        assertTrue(actualWelcomeMessage.contains(expectedUsername),
                "Mensagem de boas-vindas incorreta: " + actualWelcomeMessage);
        test.pass("Login validado com sucesso. Mensagem: " + actualWelcomeMessage);
    }
}
