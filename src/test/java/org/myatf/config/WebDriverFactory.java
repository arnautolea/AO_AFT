package org.myatf.config;

import org.myatf.ConfigurationLoader;
import org.myatf.enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WebDriverFactory {
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();;
    static String driverPath = (String) config.get("driverPath");
    private static final String CHROMEDRIVER_PATH = driverPath;
    private static WebDriver driver;
    private static Browser currentBrowser;

    static {
        initChromeDriverProperties();
    }

    public static WebDriver getDriver(Browser browser) {
        if (null != driver) {
            if (browser == currentBrowser) {
                return driver;
            }
            //driver.quit();
        }

        currentBrowser = browser;

        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Must supply a supported Browser type!");
        }
        return driver;
    }

    private static void initChromeDriverProperties() {
        if (System.getProperty("webdriver.chrome.driver") == null && new File(CHROMEDRIVER_PATH).exists()) {
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        }
        if (System.getProperty("webdriver.chrome.driver") != null) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
         } else {
            System.err.println("WARNING: Cannot locate Chrome WebDriver!");
        }
    }
}