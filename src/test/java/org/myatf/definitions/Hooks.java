package org.myatf.definitions;

import io.cucumber.java.AfterAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

     @Before("@UI")
    public static void setUp() {
        Helper.setUpDriver();
        logger.info("\nStarting UI test" );

    }
    @Before("@API")
    public static void setUpAPI() {

        logger.info("\nStarting API test" );

    }
    @After
    public void afterScenario() {
      logger.info("Test finished");
    }

    @AfterAll
    public static void tearDown() {
       Helper.tearDown();
    }

}




