package org.myatf.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.myatf.ConfigurationLoader;

import java.util.Map;

public class StepDefinitionAPI {
    private static final Logger logger = LogManager.getLogger(StepDefinitionAPI.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");
    final ScenarioContext scenarioContext;
       public StepDefinitionAPI(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        logger.info("Initialize the Scenario Context");
    }
    @Given("test context is reset")
    public void testContextIsReset() {
        scenarioContext.clearContext();
    }
    @Given("Get main URL")
    public void getBaseURL() {
        RestAssured.baseURI = baseUrl;
        logger.info("Get main url " + baseUrl);
    }

    @When("Send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
      Response response= RestAssured.when().get(endpoint);
        int statusCode = response.getStatusCode();
      scenarioContext.setContext("statusCode", statusCode);
      logger.info("Send request to search bar.\nEndpoint:" + endpoint + "\nStatus code: " + statusCode);
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        RestAssured.expect().statusCode(expectedStatusCode);
        Object actualStatusCode = scenarioContext.getContext("statusCode");
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
    }
}


