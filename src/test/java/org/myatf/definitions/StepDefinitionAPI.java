package org.myatf.definitions;

import org.myatf.enums.Browser;
import org.myatf.config.WebDriverFactory;
import org.myatf.pages.PageAPI;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepDefinitionAPI {

    final static WebDriver driver = WebDriverFactory.getDriver(Browser.CHROME);
    final PageAPI pageAPI = new PageAPI();

    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

        @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }
}

