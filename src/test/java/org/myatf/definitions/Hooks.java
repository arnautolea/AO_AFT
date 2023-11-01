package org.myatf.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.WebDriverFactory;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static String currentScenarioName;

    @Before("@UI")
    public static void setUp(Scenario scenario) {
        WebDriverFactory.setUpDriver();
        currentScenarioName = scenario.getName();
        logger.info("\nStarting UI test " + currentScenarioName);

    }

    @Before("@API")
    public static void setUpAPI(Scenario scenario) {
        currentScenarioName = scenario.getName();
        logger.info("\nStarting API test " + currentScenarioName);

    }
    @After("@UI")
    public static void afterScenario() {
       WebDriverFactory.tearDown();
    }

}