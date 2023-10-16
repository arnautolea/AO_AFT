package org.myatf.definitions;

import io.cucumber.java.en.And;
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
    public HashMap<String, String > formData = new HashMap<>();
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

    @When("GET request to {string}")
    public void sendGetRequest(String endpoint) {
        Response response = RestAssured.when().get(endpoint);
        int statusCode = response.getStatusCode();
        scenarioContext.setContext("statusCode", statusCode);
        logger.info("Get request to search bar.\nEndpoint:" + endpoint + "\nStatus code: " + statusCode);
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        RestAssured.expect().statusCode(expectedStatusCode);
        Object actualStatusCode = scenarioContext.getContext("statusCode");
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
    }

    @Given("Valid endpoint with payload to create user {} {} {}")
    public void setupEndpointAndPostData(String firstName, String lastName, String password) {
        RestAssured.baseURI=baseUrl;
        Response response = RestAssured.get("/customer/account/createpost/");
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
    @And("Request is sent to the server")
    public void requestIsSendToTheServer() {
        response = given()
            .contentType("text/html; charset=UTF-8")
            .formParams(formData)
            .when()
            .post()
            .then()
            .statusCode(302)
            .extract()
            .response();
        int statusCode = response.getStatusCode();

        logger.info("Send post request. Get status code: " + statusCode);
        String contentType = response.getContentType();
        System.out.println("Content Type: " + contentType);
        System.out.println("!!!!Response headers: " + response.headers());
    }

    @Then("User get on My account page")
    public void userGetOnMyAccountPage() {
        RestAssured.baseURI=baseUrl;
        Response response = RestAssured.get("/customer/account/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        logger.info("GET My account page Status code: " +statusCode);

    }
    @Given("Valid endpoint with payload to login user {} {}")
    public void setupEndpointAndPostData(String email, String password) {
        RestAssured.baseURI=baseUrl;
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



