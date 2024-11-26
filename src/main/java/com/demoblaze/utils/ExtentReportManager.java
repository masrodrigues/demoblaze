package com.demoblaze.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String REPORT_PATH = "target/ExtentReport.html";

   public static void setupReport() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setReportName("Relatório de Testes Automatizados");
            sparkReporter.config().setDocumentTitle("Relatório de Execução");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Ambiente", "QA");
            extent.setSystemInfo("Testador", "Marco Rodrigues");
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            openReport();
        }
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void attachScreenshot(String screenshotPath) throws IOException {
        getTest().info("Screenshot anexada", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    private static void openReport() {
        try {
            File reportFile = new File(REPORT_PATH);
            if (reportFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            } else {
                System.out.println("O relatório não foi encontrado ou o sistema não suporta abrir arquivos automaticamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
