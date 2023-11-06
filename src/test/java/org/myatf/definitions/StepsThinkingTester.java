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
import org.myatf.ConfigurationLoader;
import org.myatf.DataObjects.Contact;
import org.myatf.enums.Keys;
import org.myatf.utils.GenerateFakeTestData;
import org.myatf.utils.ScenarioContext;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StepsThinkingTester {
    private static final Logger logger = LogManager.getLogger(StepsThinkingTester.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrlAPI = (String) config.get("baseUrlAPI");
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    Response response;
    public HashMap<String, String> formData = new HashMap<>();

    // Define valid_endpoint_with_payload step
    @Given("Valid endpoint with payload to add user {word} {word} {word}")
    public void valid_endpoint_with_payload(String firstName, String lastName, String password) {
        //add data table!!!!!!!!!!!!!!!!
        // Set the base URL and path for the REST API
        RestAssured.baseURI = baseUrlAPI;
        RestAssured.basePath = ("/users");
        // Generate a random email
        fakerData.generateRandomEmail();
        String email = fakerData.getEmail();
// Store user data in formData map
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
        // Get and set the status code in the ScenarioContext
        ScenarioContext.getInstance().saveValueToContext(Keys.STATUS_CODE, statusCode);
        logger.info("Send post request. Get status code: " + statusCode);
    }

    @When("User is logged in")
    public void userIsLoggedIn() {
        RestAssured.baseURI = baseUrlAPI;
        RestAssured.basePath = ("users/login");
        formData.put("email", "melissia.morar@hotmail.com");
        formData.put("password", "Qwerty123!");
        response = RestAssured.given()
                .contentType("application/json")
                .body(formData)
                .post();
    }

    @Given("the API endpoint is {string}")
    public void theAPIEndpointIs(String endpoint) {
        RestAssured.baseURI = baseUrlAPI;
        RestAssured.basePath = (endpoint);
        logger.info("Login and Get Contacts endpoint");
    }

    @When("I send a POST request with the following JSON data and Authorization header: {string}")
    public void i_send_a_post_request_with_the_following_json_data_and_authorization_header(String jsonFileName) {
        String tokenCookie = response.getCookie("token");
        try {
// Read the JSON data from the file and convert it to a Contact object using Jackson.
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(jsonFileName);
            Contact contact = objectMapper.readValue(inputStream, Contact.class);

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + tokenCookie)
                    .body(contact)
                    .post();
            logger.info("Posted Contact details successfully");
        } catch (Exception e) {
            logger.error("Contact is not created " + e.getMessage());
        }
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assert actualStatusCode == expectedStatusCode : "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode;
        logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
    }

    @When("Get Contact List details")
    public void get_contact_list_details() {
        String tokenCookie = response.getCookie("token");
        response = RestAssured.given()
                .header("Authorization", "Bearer " + tokenCookie)
                .get();

        int statusCode = response.getStatusCode();
        ScenarioContext.getInstance().saveValueToContext(Keys.STATUS_CODE, statusCode);
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

