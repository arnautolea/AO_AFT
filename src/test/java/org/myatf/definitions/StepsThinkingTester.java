package org.myatf.definitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
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
import org.myatf.enums.Keys;
import org.myatf.utils.GenerateFakeTestData;
import org.myatf.utils.ScenarioContext;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepsThinkingTester {
    private static final Logger logger = LogManager.getLogger(StepsThinkingTester.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrlAPI = (String) config.get("baseUrlAPI");
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    Response response;
    public HashMap<String, String> formData = new HashMap<>();

    @When("add user details:")
    public void addUserDetails(DataTable details) {
        // Initialize the userData map
        Map<String, String> userData = new HashMap<>();

        // Generate a random email
        fakerData.generateRandomEmail();
        String email = fakerData.getEmail();
        List<Map<String, String>> data = details.asMaps(String.class, String.class);

        // Add the email to the userData map
        userData.put("email", email);
        userData.putAll(data.get(0)); // Merge the data from the DataTable into userData

        // Save userData in the scenario context
        ScenarioContext.getInstance().saveValueToContext(Keys.USERDATA, userData);

        logger.info("UserDetails: " + userData);
    }

    @When("Post request is sent to the server")
    public void postRequestIsSendToTheServer() {
        // Retrieve userData from the scenario context
        Object userData = ScenarioContext.getInstance().getValueFromContext(Keys.USERDATA);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userData)
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

    @When("Send a POST request with the following JSON data and Authorization header: {string}")
    public void SendAPostRequestWithJsonAndAuthorizationHeader(String jsonFileName){
        String tokenCookie = response.getCookie("token");
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(jsonFileName);

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + tokenCookie)
                    .body(inputStream)
                    .post();
            logger.info("Posted Contact details successfully");
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assert actualStatusCode == expectedStatusCode : "Expected status code: " + expectedStatusCode + ", actual: " + actualStatusCode;
        logger.info("Status code expected: " + expectedStatusCode + " Status code actual: " + actualStatusCode);
    }

    @When("Get Contact List details")
    public void getContactListDetails() {
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

