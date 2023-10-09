package org.myatf.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.Helper;
import static org.hamcrest.Matchers.containsString;


public class StepDefinitionAPI {

     private static final Logger logger = LogManager.getLogger(StepDefinitionAPI.class);

    @Given("Get main URL")
    public void getBaseURL() {
        RestAssured.baseURI = Helper.baseUrl;
        logger.info("Get main url");
    }

    @When("Send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        RestAssured.when().get(endpoint);
        logger.info("Send request to search bar");
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        RestAssured.expect().statusCode(expectedStatusCode);
        logger.info("Status code is 200");
    }

    @Then("The response body should contain {string}")
    public void verifyResponseBodyContains(String expectedText) {
        RestAssured.expect().body(containsString(expectedText));
        logger.info("Response body contain \"Tops\"\n");
    }
}


