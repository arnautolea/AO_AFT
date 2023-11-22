package org.myatf.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.ScenarioContext;
import org.myatf.utils.WebDriverFactory;

import java.nio.file.Paths;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static String scenarioName;

    @Before
    public static void beforeScenario() {
        ScenarioContext.getInstance().clearContext();
    }

    @Before("@UI")
    public static void setUpUI(Scenario scenario) {
        WebDriverFactory.getDriver();
        scenarioName = scenario.getName();
        logger.info(System.lineSeparator() + "Starting UI test " + scenarioName);
    }

    @Before("@API")
    public static void setUpAPI(Scenario scenario) {
        scenarioName = scenario.getName();
        logger.info(System.lineSeparator() + "Starting API test " + scenarioName);
    }

    @After("@UI")
    public static void afterScenario() {
        WebDriverFactory.tearDown();
        logger.info("WebDriver has been quit and reset.");
    }

}