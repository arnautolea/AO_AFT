package org.myatf.definitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.myatf.ConfigurationLoader;
import org.myatf.utils.GenerateFakeTestData;

import java.util.HashMap;
import java.util.Map;

public class StepsThinkingTester {
    private static final Logger logger = LogManager.getLogger(StepsThinkingTester.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrlAPI = (String) config.get("baseUrlAPI");
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    Response response;
    public HashMap<String, String> formData = new HashMap<>();
    final ScenarioContext scenarioContext;

    public StepsThinkingTester(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        logger.info("Initialize the Scenario Context");
    }

    @Given("Valid endpoint with payload to add user {} {} {}")
    public void valid_endpoint_with_payload(String firstName, String lastName, String password) {
        RestAssured.baseURI = baseUrlAPI;
        RestAssured.basePath = ("/users");
        fakerData.generateRandomEmail();
        String email = fakerData.getEmail();

        formData.put("firstName", firstName);
        formData.put("lastName", lastName);
        formData.put("email", email);
        formData.put("password", password);

        logger.info("Post endpoint with payload to create user with email: " + email);
        System.out.println(formData);
    }

    @When("Post request is sent to the server")
    public void postRequestIsSendToTheServer() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(formData)
                .post();
        int statusCode = response.getStatusCode();
        scenarioContext.setContext("statusCode", statusCode);
        logger.info("Send post request. Get status code: " + statusCode);
        String contentType = response.getContentType();
        System.out.println("Content Type: " + contentType);
    }

    @Given("the API endpoint is {string}")
    public void theAPIEndpointIs(String endpoint) {
        RestAssured.baseURI = baseUrlAPI;
        RestAssured.basePath = (endpoint);
        logger.info("Get Contacts endpoint");
    }

    @When("I send a POST request with the following JSON data and Authorization header")
    public void i_send_a_post_request_with_the_following_json_data_and_authorization_header() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTJkNTFkMzUzNzdiYjAwMTMxZjQ1MzciLCJpYXQiOjE2OTc1NTA5NTZ9.8Q2j6FbdfBNxsVOPVvJ2PJZ9ywQUP1jOqyzCzEKjL6o";

        formData.put("firstName", "Hello");
        formData.put("lastName", "World");
        formData.put("birthdate", "1970-01-01");
        formData.put("email", "privet@hotmail.com");
        formData.put("phone", "8005555555");
        formData.put("street1", "1 Main St.");
        formData.put("street2", "Apartment A");
        formData.put("city", "Anytown");
        formData.put("stateProvince", "KS");
        formData.put("postalCode", "12343");
        formData.put("country", "MDA");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(formData)
                .post();
        logger.info("Post Contact details");
        String contentType = response.getContentType();
        System.out.println("Content Type: " + contentType);
        System.out.println("formData: " + formData);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assert actualStatusCode == expectedStatusCode : "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode;
        logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
    }

    @When("Get Contact List details")
    public void get_contact_list_details() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTJkNTFkMzUzNzdiYjAwMTMxZjQ1MzciLCJpYXQiOjE2OTc1NTA5NTZ9.8Q2j6FbdfBNxsVOPVvJ2PJZ9ywQUP1jOqyzCzEKjL6o";
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get();

        int statusCode = response.getStatusCode();
        scenarioContext.setContext("statusCode", statusCode);
        String responseString = response.getBody().asString();

        try {
            // Use Jsoup to parse the HTML response
            Document document = Jsoup.parse(responseString);

            // Extract the JSON content from the HTML response
            String jsonContent = document.text();

            //Format JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            Object json = objectMapper.readValue(jsonContent, Object.class);
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

            // Log the formatted JSON response
            logger.info(formattedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

