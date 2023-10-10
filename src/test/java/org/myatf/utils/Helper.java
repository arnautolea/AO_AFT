package org.myatf.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.ConfigurationLoader;
import org.myatf.enums.Browser;
import org.myatf.config.WebDriverFactory;
import org.openqa.selenium.WebDriver;


import java.util.Map;
public class Helper {

    private static Helper helperClass;
    private static WebDriver driver;
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");
    private static final Logger logger = LogManager.getLogger(Helper.class);
    private Helper() {driver = WebDriverFactory.getDriver(Browser.CHROME);
    }
    public static void openPage() {driver.get(baseUrl);
    }
    public static void setUpDriver() {

        if (helperClass==null) {

            helperClass = new Helper();
            logger.info("set Up Driver");
        }
        else{
            helperClass = new Helper();
            logger.info("set Up Next Driver");
        }
    }
        public static void tearDown() {

        if(driver != null) {
            driver.quit();
            logger.info("Closing browser");
        }

        helperClass = null;
    }
}