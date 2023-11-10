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
import org.junit.Assert;
import org.myatf.ConfigurationLoader;
import org.myatf.enums.Keys;
import org.myatf.utils.GenerateFakeTestData;
import org.myatf.utils.ScenarioContext;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepDefinitionsAPI {
    private static final Logger logger = LogManager.getLogger(StepDefinitionsAPI.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    Response response;
    public HashMap<String, String> formData = new HashMap<>();

    ObjectMapper objectMapper = new ObjectMapper();

    @Given("Get main URL")
    public void getBaseURL() {
        RestAssured.baseURI = baseUrl;
        logger.info("Get main url: " + baseUrl);
    }

    @When("GET request to {string}")
    public void sendGetRequest(String endpoint) {
        Response response = RestAssured.when().get(endpoint);
        int statusCode = response.getStatusCode();
        ScenarioContext.getInstance().saveValueToContext(Keys.STATUS_CODE, statusCode);
        logger.info("Get request to search bar.\nEndpoint:" + endpoint + "\nStatus code: " + statusCode);
    }

    @Then("The response status code is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Object actualStatusCodeObj = ScenarioContext.getInstance().getValueFromContext(Keys.STATUS_CODE);
        if (actualStatusCodeObj instanceof Integer) {
            int actualStatusCode = (int) actualStatusCodeObj; // Cast the actual status code to int
            Assert.assertEquals(expectedStatusCode, actualStatusCode);
            logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
        } else {
            throw new ClassCastException("Actual status code is not of type int.");
        }
    }

    @When("POST payload")
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
        ScenarioContext.getInstance().saveValueToContext(Keys.STATUS_CODE, statusCode);
        logger.info("Send post request. Get status code: " + statusCode);
    }

    @Given("^Get endpoint and create payload to \"(create|signIn)\" user with(?: firstName: \"(.+?)\")?(?: lastName: \"(.+?)\")?(?: password: \"(.+?)\")?(?: email: \"(.+?)\")?$")
    public void setupEndpointAndPostData(String action, String firstName, String lastName, String password, String email) {
        String endpoint = getEndpoint(action);
        Response response = RestAssured.get(endpoint);
        // Parse the HTML response
        Document document = Jsoup.parse(response.getBody().asString());
        // Extract the form key
        String formKey = document.select("input[name=form_key]").attr("value");
        // Add form key to the form data
        formData.put("form_key", formKey);

        if ("create".equals(action)) {
            // For user creation, generate and use a random email
            fakerData.generateRandomEmail();
            String generatedEmail = fakerData.getEmail();
            formData.put("firstname", firstName);
            formData.put("lastname", lastName);
            formData.put("email", generatedEmail);
            formData.put("password", password);
            formData.put("password-confirmation", password);
            logger.info("Post endpoint with payload to create user with email: " + generatedEmail);
        } else if ("signIn".equals(action)) {
            // For user login, use the provided email and password
            formData.put("email", email);
            formData.put("password", password);
            logger.info("Post endpoint with payload to login using email: " + email + " and password: " + password);
        }

        System.out.println(formData);
    }

    private String getEndpoint(String action) {
        //Ternary Operator short way of writing an if-else statement. condition ? trueExpression : falseExpression
        return "/customer/account/" + (action.equals("create") ? "create" : "login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");
    }
}




