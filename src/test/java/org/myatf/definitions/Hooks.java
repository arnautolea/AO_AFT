package org.myatf.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.ScenarioContext;
import org.myatf.utils.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static String scenarioName;

    @Before
    public static void beforeScenario(Scenario scenario) {
        ScenarioContext.getInstance().clearContext();
        scenarioName = scenario.getName();
    }

    @Before("@UI")
    public static void setUpUI() {
        logger.info(System.lineSeparator() + "Starting UI test " + scenarioName);
        WebDriverFactory.getDriver();
    }

    @Before("@API")
    public static void setUpAPI() {
        logger.info(System.lineSeparator() + "Starting API test " + scenarioName);
    }

    @After("@UI")
    public static void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot and embed it in the Cucumber report
            final WebDriver driver = WebDriverFactory.getDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "FailureScreenshot");
        }
        WebDriverFactory.tearDown();
        logger.info("WebDriver has been quit and reset.");
    }

}