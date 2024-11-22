package com.demoblaze.steps;

import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.SignupPage;
import com.demoblaze.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupSteps {
    private WebDriver driver;
    private HomePage homePage;
    private SignupPage signupPage;
    private Faker faker;
    private String username;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
        faker = new Faker();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Dado("que estou na página inicial do Demoblaze")
    public void queEstouNaPaginaInicialDoDemoblaze() {
        driver.get("https://www.demoblaze.com");
    }

    @Quando("eu abro o formulário de cadastro")
    public void euAbroOFormularioDeCadastro() {
        homePage.openSignupForm();
    }

    @E("preencho o formulário de cadastro com dados dinâmicos")
    public void preenchoOFormularioDeCadastroComDadosDinamicos() {

        username = faker.name().username() + faker.number().digits(3);
        String password = faker.internet().password();


        signupPage.fillSignupForm(username, password);
    }

    @Quando("confirmo o cadastro")
    public void submetoOFormularioDeCadastro() {
        signupPage.submitSignupForm();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    @Entao("devo ver uma mensagem de sucesso confirmando o cadastro")
    public void devoVerUmaMensagemDeSucesso() {

        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("Sign up successful"), "A mensagem de sucesso não foi exibida corretamente.");


        driver.switchTo().alert().accept();
    }
}
