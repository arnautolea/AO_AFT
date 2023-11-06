package org.myatf.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.ScenarioContext;
import org.myatf.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;


public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static String currentScenarioName;
     @Before
    public static void beforeScenario(Scenario scenario) {
        currentScenarioName = scenario.getName();
        ScenarioContext.getInstance().clearContext();
        logger.info("Logger initialized for scenario: " + scenario.getName());

    }

    @Before("@UI")
    public static void setUp(Scenario scenario) {
        WebDriverFactory.getDriver();
        currentScenarioName = scenario.getName();
        ScenarioContext.getInstance().clearContext();
        logger.info(System.lineSeparator() + "Starting UI test " + currentScenarioName);

    }

    @Before("@API")
    public static void setUpAPI(Scenario scenario) {
        currentScenarioName = scenario.getName();
        logger.info(System.lineSeparator() + "Starting API test " + currentScenarioName);

    }
    @After("@UI")
    public static void afterScenario() {
       WebDriverFactory.tearDown();
    }

}