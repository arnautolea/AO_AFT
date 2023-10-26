package org.myatf.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.File;
import java.util.Map;

public class Helper {

    private static WebDriver driver;
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String driverPath = (String) config.get("driverPath");
    private static final Logger logger = LogManager.getLogger(Helper.class);

// Initialize the WebDriver
    public static void setUpDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            logger.info("Driver has been initialized.");
        } else {
            logger.info("Driver is already initialized.");
        }
    }

    // Get the WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call setUpDriver() first.");
        }
        return driver;
    }

    // Close and Quit the WebDriver after each test
    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null; // Reset the driver instance
            logger.info("WebDriver has been quit and reset.");
        }
    }

}