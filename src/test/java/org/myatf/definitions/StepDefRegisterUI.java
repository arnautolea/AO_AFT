package org.myatf.definitions;

import org.myatf.ConfigurationLoader;
import org.myatf.enums.Keys;
import org.myatf.pages.AccountPage;
import org.myatf.pages.HomePage;
import org.myatf.pages.RegisterPage;
import org.myatf.utils.RegistrationFormData;
import org.myatf.utils.ScenarioContext;
import org.myatf.utils.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;

import static org.junit.Assert.*;

public class StepDefRegisterUI {

    // Initialize the WebDriver and other necessary variables
    private final WebDriver driver;
    private final RegisterPage registerPage;
    private final HomePage homePage;
    private final AccountPage accountPage;

    public StepDefRegisterUI() {
        // Initialize the WebDriver and the RegisterPage in the constructor
        driver = WebDriverFactory.getDriver();
        registerPage = new RegisterPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
    }
    private static final Logger logger = LogManager.getLogger(StepDefRegisterUI.class);
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");
    RegistrationFormData data = new RegistrationFormData();

    @Given("User is on the Home page")
    public void userIsOnHomePage() {
        try {
            // Navigate to the base URL
            driver.get(baseUrl);
            logger.info("User is on Home Page.");
        } catch (NullPointerException e) {
            // Handle the SessionNotCreatedException and log the error
            logger.error("NullPointerException occurred: " + e.getMessage());
        }
    }

    @When("User click on Create An Account link")
    public void userClicksOnCreateAnAccountLink() {
        // Click on the "Create An Account" link
        registerPage.click(homePage.clickCreateAnAccount);
        logger.info("Click on Create An Account");
    }
    @When("user fill the registration form with valid data")
    public void userFillTheRegistrationFormWithValidData() {
        data.RegistrationFormDataWithFaker();
        logger.info("user fill the registration form with valid data");
    }

    @When("User clicks on Create an Account Button")
    public void userClicksOnCreateAnAccount() {
        registerPage.click(registerPage.btnCreateAnAccount);
        logger.info("Click on Create an Account Button");
    }

    @Then("User redirected on Account Page, 'My Account' inscription is displayed on the screen")
    public void inscriptionMyAccount() {
        String myAccount = registerPage.returnText(accountPage.inscriptionMyAccount);
        assertEquals("My Account", myAccount);
        logger.info("\"My Account\" Page displayed");
    }

    @When("User click on Sign In")
    public void userClickOnSignIn() {
            registerPage.click(homePage.clickOnSignIn);
            logger.info("Click on Sign In");
    }
    @Then("^User is logged in with Contact Information(?: First Name: \"(.+?)\")?(?: Last Name: \"(.+?)\")?(?: Email: \"(.+?)\")?$")
    public void userIsLoggedInWithContactInformation(String firstName, String lastName, String email) {

        if (firstName == null && lastName == null && email == null) {
            //retrieve data from the context
            Object expectedFirstName = ScenarioContext.getInstance().getValueFromContext(Keys.FIRST_NAME);
            Object expectedLastName = ScenarioContext.getInstance().getValueFromContext(Keys.LAST_NAME);
            Object expectedEmail = ScenarioContext.getInstance().getValueFromContext(Keys.EMAIL);

            String expectedNameEmail = expectedFirstName + " " + expectedLastName + "\n" + expectedEmail;
            AssertData(expectedNameEmail);

        } else {
            String expectedNameEmail = firstName + " " + lastName + "\n" + email;
            AssertData(expectedNameEmail);
        }
    }

    public void AssertData(String expectedNameEmail) {
        try {

            String actualData = registerPage.returnText(accountPage.contactInformationName);

            if (actualData != null) {
                assertEquals(expectedNameEmail, actualData);
                logger.info("User name and email are displayed in Contact information. \nExpected:\n" + expectedNameEmail + "\nActual: \n" + actualData);
            }
        } catch (NoSuchElementException e) {
            logger.error("Contact Information name and email element not found.");
        }
    }

}
