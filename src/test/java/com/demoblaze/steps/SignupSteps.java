package com.demoblaze.steps;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.SignupPage;
import com.demoblaze.utils.DriverManager;
import com.demoblaze.utils.ExtentReportManager;
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
    private ExtentTest test;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
        faker = new Faker();

        test = ExtentReportManager.createTest("Teste de Cadastro");
        test.info("Configuração inicial concluída.");
    }

    @After
    public void tearDown() {
        try {
            test.info("Encerrando o navegador.");
            DriverManager.quitDriver();
            test.pass("Teste de cadastro concluído com sucesso.");
        } catch (Exception e) {
            test.fail("Erro durante o encerramento: " + e.getMessage());
        } finally {
            ExtentReportManager.flushReport();
        }
    }

    @Dado("que estou na página inicial do Demoblaze")
    public void queEstouNaPaginaInicialDoDemoblaze() {
        driver.get("https://www.demoblaze.com");
        test.info("Acessada a página inicial do Demoblaze.");
    }

    @Quando("eu abro o formulário de cadastro")
    public void euAbroOFormularioDeCadastro() {
        homePage.openSignupForm();
        test.info("Formulário de cadastro aberto.");
    }

    @E("preencho o formulário de cadastro com dados dinâmicos")
    public void preenchoOFormularioDeCadastroComDadosDinamicos() {
        username = faker.name().username() + faker.number().digits(3);
        String password = faker.internet().password();

        signupPage.fillSignupForm(username, password);
        test.info("Formulário preenchido com usuário: " + username);
    }

    @Quando("confirmo o cadastro")
    public void submetoOFormularioDeCadastro() {
        signupPage.submitSignupForm();
        test.info("Formulário de cadastro submetido.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        test.info("Alerta de confirmação de cadastro exibido.");
    }

    @Entao("devo ver uma mensagem de sucesso confirmando o cadastro")
    public void devoVerUmaMensagemDeSucesso() {
        String alertText = driver.switchTo().alert().getText();
        test.info("Texto do alerta recebido: " + alertText);

        assertTrue(alertText.contains("Sign up successful"),
                "A mensagem de sucesso não foi exibida corretamente.");
        test.pass("Cadastro realizado com sucesso. Mensagem: " + alertText);

        driver.switchTo().alert().accept();
    }
}
