package org.myatf.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.ConfigurationLoader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.myatf.utils.XmlParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class StepDefinitionsBNM {
    private static final Logger logger = LogManager.getLogger(StepDefinitionsBNM.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrlBNM = (String) config.get("baseUrlBNM");
    private final Map<String, Double> euroValuesMap = new HashMap<>();
    private String csvContent;

    @Given("I have a CSV file containing dates {string}")
    public void parseCsvFile(String csvFile) throws IOException {
        csvContent = Files.readString(Paths.get(csvFile), StandardCharsets.UTF_8);
        logger.info("Read the CSV file into a string");
    }


    @When("I retrieve the XML response for each date")
    public void iRetrieveTheXMLResponseForEachDate() throws Exception {
        CSVParser csvParser = CSVParser.parse(csvContent, CSVFormat.DEFAULT);

        for (CSVRecord record : csvParser) {
            String date = record.get(0);
            String url = baseUrlBNM + date;

            Response response = RestAssured.get(url);

            if (response.getStatusCode() == 200) {
                String xmlContent = response.getBody().asString();

                // Parse the XML content and extract EUR value
                double euroValue = XmlParser.parseEuroValueFromXML(xmlContent);
                //Store euroValue in Map
                euroValuesMap.put(date, euroValue);

                System.out.println("For Date: " + date + " EUR Value is: " + euroValue);
            } else {
                // Handle the case where the request did not return a successful status code
                System.out.println("Request for Date: " + date + " failed with status code: " + response.getStatusCode());
            }

        }
        logger.info("Parse the XML content and extract EUR value for each date from CSV");
    }

    @Then("I calculate the total paid amount for all dates with a salary of {}")
    public void iCalculateTheTotalPaidAmountForAllDatesWithASalaryOf(double salaryAmount) {
        double totalPaidAmount = 0.0;

        for (Map.Entry<String, Double> entry : euroValuesMap.entrySet()) {
            double euroValue = entry.getValue();
            totalPaidAmount += euroValue * salaryAmount;
        }

        String formattedTotalPaidAmount = String.format("%.2f lei", totalPaidAmount);
        logger.info("Total paid amount for all dates: " + formattedTotalPaidAmount);
    }

}


