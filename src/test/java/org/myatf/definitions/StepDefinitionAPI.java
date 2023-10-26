package org.myatf.definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.myatf.ConfigurationLoader;
import org.myatf.enums.Context;
import org.myatf.utils.GenerateFakeTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepDefinitionAPI {
    private static final Logger logger = LogManager.getLogger(StepDefinitionAPI.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    Response response;
    public HashMap<String, String> formData = new HashMap<>();

    ObjectMapper objectMapper = new ObjectMapper();

    @Given("test context is reset")
    public void testContextIsReset() {
        ScenarioContext.INSTANCE.clearContext();
    }

    @Given("Get main URL")
    public void getBaseURL() {
        RestAssured.baseURI = baseUrl;
        logger.info("Get main url " + baseUrl);
    }

    @When("GET request to {string}")
    public void sendGetRequest(String endpoint) {
        Response response = RestAssured.when().get(endpoint);
        int statusCode = response.getStatusCode();
        ScenarioContext.INSTANCE.setContext(Context.STATUS_CODE, statusCode);
        logger.info("Get request to search bar.\nEndpoint:" + endpoint + "\nStatus code: " + statusCode);
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Object actualStatusCodeObj = ScenarioContext.INSTANCE.getContext(Context.STATUS_CODE);
        if (actualStatusCodeObj instanceof Integer) {
            int actualStatusCode = (int) actualStatusCodeObj; // Cast the actual status code to int
            Assert.assertEquals(expectedStatusCode, actualStatusCode);
            logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
        } else {
            throw new ClassCastException("Actual status code is not of type int.");
        }
    }

    @Given("Valid endpoint with payload to create user {} {} {}")
    public void setupEndpointAndPostData(String firstName, String lastName, String password) {
        RestAssured.baseURI = baseUrl;
        Response response = RestAssured.get("/customer/account/create/");
        fakerData.generateRandomEmail();
        String email = fakerData.getEmail();

// Parse the HTML response
        String htmlResponse = response.getBody().asString();
        Document document = Jsoup.parse(htmlResponse);
// Find the <input> element with the name attribute equal to "form_key"
        Element inputElement = document.select("input[name=form_key]").first();
// Extract the value of the "value" attribute
        assert inputElement != null;
        String formKey = inputElement.attr("value");

        formData.put("form_key", formKey);
        formData.put("firstname", firstName);
        formData.put("lastname", lastName);
        formData.put("email", email);
        formData.put("password", password);
        formData.put("password_confirmation", password);
        logger.info("Post endpoint with payload to create user with email: " + email);
        System.out.println(formData);

    }

    @When("Request is sent to the server")
    public void requestIsSendToTheServer() throws JsonProcessingException {

        String payload = objectMapper.writeValueAsString(formData);
        System.out.println(payload);

        response = given()
                .contentType("text/html; charset=UTF-8")
                .body(payload)
                .when()
                .post()
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        ScenarioContext.INSTANCE.setContext(Context.STATUS_CODE, statusCode);
        logger.info("Send post request. Get status code: " + statusCode);
        String contentType = response.getContentType();
        System.out.println("Content Type: " + contentType);
    }

    @Given("Valid endpoint with payload to log in user {} {}")
    public void setupEndpointAndPostData(String email, String password) {
        RestAssured.baseURI = baseUrl;
        Response response = RestAssured.get("/customer/account/loginPost/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS9jdXN0b21lci9hY2NvdW50L2xvZ291dC8%2C/");

// Parse the HTML response
        String htmlResponse = response.getBody().asString();
        Document document = Jsoup.parse(htmlResponse);
// Find the <input> element with the name attribute equal to "form_key"
        Element inputElement = document.select("input[name=form_key]").first();
// Extract the value of the "value" attribute
        assert inputElement != null;
        String formKey = inputElement.attr("value");

        formData.put("form_key", formKey);
        formData.put("email", email);
        formData.put("password", password);
        logger.info("Post endpoint with payload to login using email: " + email + "and password: " + password);
        System.out.println(formData);
    }
}



