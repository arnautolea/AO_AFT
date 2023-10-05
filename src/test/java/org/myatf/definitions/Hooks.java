package org.myatf.definitions;

import io.cucumber.java.AfterAll;
import org.myatf.utils.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private ScenarioContext scenarioContext;

    public Hooks() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @Before
    public static void setUp() {
        Helper.setUpDriver();
        System.out.println("Starting Browser" );
    }

    @After
    public void afterScenario(Scenario scenario) {
        boolean scenarioIsSuccessful = !scenario.isFailed();
        scenarioContext.setScenarioResults(scenario.getName(), scenarioIsSuccessful);
     System.out.println(scenario.getName() + " Test finished\n" );
    }

    @AfterAll
    public static void tearDown() {
        Helper.tearDown();

    }

}




