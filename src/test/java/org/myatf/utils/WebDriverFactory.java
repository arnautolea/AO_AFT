package org.myatf.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.ConfigurationLoader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class WebDriverFactory {
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    private static final String driverPath = (String) config.get("driverPath");
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver;

    // Private constructor to prevent external instantiation
    private WebDriverFactory() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        logger.info("Driver has been initialized.");
    }

    // Initialize the driver using a static block
    // lazy initialization pattern. The WebDriver is not created until it's actually requested by calling getDriver
    static {
        new WebDriverFactory();
    }

    // Get the driver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            // If the driver is null, create a new instance
            new WebDriverFactory();
        }
        return driver;
    }

    // Close and Quit the WebDriver
    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}

