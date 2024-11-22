package com.demoblaze.steps;

import com.demoblaze.pages.*;
import com.demoblaze.utils.DriverManager;
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

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        monitorPage = new MonitorPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Quando("navego para a categoria {string}")
    public void navegoParaACategoria(String category) {
        homePage.clickCategory(category);
    }

    @E("adiciono o primeiro monitor ao carrinho")
    public void adicionoOPrimeiroMonitorAoCarrinho() {
        monitorPage.clickFirstMonitor();
        monitorPage.clickAddToCart();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("Product added"),
                "Mensagem de confirmação ao adicionar ao carrinho está incorreta: " + alertText);
        driver.switchTo().alert().accept();
    }

    @E("vou para o carrinho")
    public void vouParaOCarrinho() {
        cartPage.clickCart();
    }

    @Quando("concluo a compra com detalhes válidos")
    public void concluoACompraComDetalhesValidos() {
        cartPage.clickPlaceOrder();
        orderPage.fillOrderDetails("Edson Arantes", "BRA", "Santos", "1234567812345678", "12", "2025");
        orderPage.clickPurchase();
    }

    @Entao("devo ver uma mensagem de confirmação de compra")
    public void devoVerUmaMensagemDeConfirmacaoDeCompra() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert.visible"))); // Localiza o modal pelo CSS


            String confirmationMessage = driver.findElement(By.cssSelector(".sweet-alert.visible h2")).getText();
            assertTrue(confirmationMessage.contains("Thank you for your purchase!"),
                    "Mensagem de confirmação incorreta: " + confirmationMessage);


            driver.findElement(By.cssSelector(".sweet-alert.visible .confirm")).click();
        } catch (TimeoutException e) {
            throw new AssertionError("O modal de confirmação não apareceu no tempo esperado.", e);
        }
    }



}
