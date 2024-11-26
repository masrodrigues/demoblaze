package com.demoblaze.steps;

import com.aventstack.extentreports.ExtentTest;
import com.demoblaze.pages.*;
import com.demoblaze.utils.DriverManager;
import com.demoblaze.utils.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonitorSteps {
    private WebDriver driver;
    private HomePage homePage;
    private MonitorPage monitorPage;
    private CartPage cartPage;
    private OrderPage orderPage;
    private LoginPage loginPage;
    private ExtentTest test;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        monitorPage = new MonitorPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        loginPage = new LoginPage(driver);

        test = ExtentReportManager.createTest("Teste de Fluxo de Compra de Monitor");

    }

    @After
    public void tearDown() {
        try {
            test.info("Encerrando o navegador.");
            DriverManager.quitDriver();
            test.pass("Teste concluído com sucesso.");
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
    @Quando("navego para a categoria {string}")
    public void navegoParaACategoria(String category) {
        homePage.clickCategory(category);
        test.info("Naveguei para a categoria: " + category);
    }

    @E("adiciono o primeiro monitor ao carrinho")
    public void adicionoOPrimeiroMonitorAoCarrinho() {
        monitorPage.clickFirstMonitor();
        test.info("Cliquei no primeiro monitor da lista.");

        monitorPage.clickAddToCart();
        test.info("Cliquei para adicionar ao carrinho.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        test.info("Texto do alerta ao adicionar ao carrinho: " + alertText);

        assertTrue(alertText.contains("Product added"),
                "Mensagem de confirmação ao adicionar ao carrinho está incorreta: " + alertText);
        test.pass("Monitor adicionado ao carrinho com sucesso.");

        driver.switchTo().alert().accept();
    }

    @E("vou para o carrinho")
    public void vouParaOCarrinho() {
        cartPage.clickCart();
        test.info("Acessei o carrinho.");
    }

    @Quando("concluo a compra com detalhes válidos")
    public void concluoACompraComDetalhesValidos() {
        cartPage.clickPlaceOrder();
        test.info("Cliquei em 'Place Order'.");

        orderPage.fillOrderDetails("Edson Arantes", "BRA", "Santos", "1234567812345678", "12", "2025");
        test.info("Preenchi os detalhes da compra.");

        orderPage.clickPurchase();
        test.info("Concluí a compra.");
    }

    @Entao("devo ver uma mensagem de confirmação de compra")
    public void devoVerUmaMensagemDeConfirmacaoDeCompra() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert.visible"))); // Localiza o modal pelo CSS

            String confirmationMessage = driver.findElement(By.cssSelector(".sweet-alert.visible h2")).getText();
            test.info("Mensagem de confirmação exibida: " + confirmationMessage);

            assertTrue(confirmationMessage.contains("Thank you for your purchase!"),
                    "Mensagem de confirmação incorreta: " + confirmationMessage);
            test.pass("Mensagem de confirmação exibida corretamente.");

            driver.findElement(By.cssSelector(".sweet-alert.visible .confirm")).click();
        } catch (TimeoutException e) {
            test.fail("O modal de confirmação não apareceu no tempo esperado.");
            throw new AssertionError("O modal de confirmação não apareceu no tempo esperado.", e);
        }
    }
}
