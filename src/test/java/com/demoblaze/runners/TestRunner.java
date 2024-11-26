package com.demoblaze.runners;

import com.demoblaze.utils.ExtentReportManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.demoblaze.steps"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@login"
)
public class TestRunner {

    @BeforeClass
    public static void setup() {
        ExtentReportManager.setupReport();
    }

    @AfterClass
    public static void tearDown() {
        ExtentReportManager.flushReport();
    }
}
