package org.myatf.utils;

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
    private Helper() {driver = WebDriverFactory.getDriver(Browser.CHROME);
    }
    public static void openPage() {driver.get(baseUrl);
    }
    public static void setUpDriver() {

        if (helperClass==null) {

            helperClass = new Helper();
            System.out.println("set Up Driver");
        }
        else{
            helperClass = new Helper();
            System.out.println("set Up Next Driver");
        }
    }

        public static void tearDown() {

        if(driver != null) {
            driver.quit();
            System.out.println("Closing browser");
        }

        helperClass = null;
    }
}