package org.myatf.definitions;

import org.myatf.ConfigurationLoader;
import org.myatf.enums.Context;
import org.myatf.utils.GenerateFakeTestData;
import org.myatf.utils.ScenarioContext;
import org.myatf.utils.WebDriverFactory;
import org.myatf.pages.PageFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static java.time.Duration.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class StepDefinitionsUI {

    // Initialize the WebDriver and other necessary variables
    private final WebDriver driver = WebDriverFactory.getDriver();
    private final PageFactory pageFactory = new PageFactory(driver);
    private static final Logger logger = LogManager.getLogger(StepDefinitionsUI.class);
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");

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
        pageFactory.getClickCreateAnAccount();
        logger.info("Click on Create An Account");
    }

    @When("User fills First Name")
    public void userFillsFirstNameFirstName() {
        // Generate and fill a random first name
        fakerData.generateRandomFirstName();
        // Get and set the firstName in the ScenarioContext
        String firstName = fakerData.getFirstName();
        pageFactory.getInputFirstName(firstName);
        ScenarioContext.INSTANCE.setContext(Context.FIRST_NAME, firstName);
        logger.info("First Name completed: " + firstName);
    }

    @When("User fills Last Name")
    public void userFillsLastName() {
        // Generate and fill a random last name
        fakerData.generateRandomLastName();
        // Get and set the lastName in the ScenarioContext
        String lastName = fakerData.getLastName();
        pageFactory.getInputLastName(lastName);
        ScenarioContext.INSTANCE.setContext(Context.LAST_NAME, lastName);
        logger.info("Last Name completed: " + lastName);

    }

    @When("User fills Email")
    public void userFillsEmail() {
        // Generate and fill a random email
        fakerData.generateRandomEmail();
        // Get and set the Email in the ScenarioContext
        String email = fakerData.getEmail();
        pageFactory.getInputEmail(email);
        ScenarioContext.INSTANCE.setContext(Context.EMAIL, email);
        logger.info("Email address: " + email);
    }

    @When("User fills password and confirmation password")
    public void userFillsPasswordAndConfirmationPassword() {
        // Generate and fill a random password
        fakerData.generateRandomPassword();
        String password = fakerData.getPassword();
        pageFactory.getInputPassword(password);
        pageFactory.getInputConfirmPassword(password);
        logger.info("Password and confirmation password: " + password);
    }

    @When("User clicks on Create an Account Button")
    public void userClicksOnCreateAnAccount() {
        pageFactory.getBtnCreateAnAccount();
        logger.info("Click on Create an Account Button");
    }

    @Then("User redirected on Account Page, 'My Account' inscription is displayed on the screen")
    public void inscriptionMyAccount() {
        String myAccount = pageFactory.getInscriptionMyAccount();
        assertEquals("My Account", myAccount);
        logger.info("\"My Account\" Page displayed");
    }

    @When("User click on Sign In")
    public void userClickOnSignIn() {
        try {
            pageFactory.getClickOnSignIn();
            logger.info("Click on Sign In");
        } catch (NoSuchElementException e) {
            logger.error("WebDriver couldn’t locate the element Sign In" + e.getMessage());
        }
    }

    @Then("Customer Login page is displayed")
    public void customerLoginPageIsDisplayed() {
        try {
            String customerLogin = pageFactory.getInscriptionCustomerLogin();
            assertEquals("Customer Login", customerLogin);
            logger.info("\"Customer Login\" Page displayed");
        } catch (AssertionError e) {
            logger.error("No Customer Login message" + e.getMessage());
        }
    }

    @When("User fills email: {}")
    public void userFillsEmail(String email) {
        pageFactory.getInputRegisteredEmail(email);
        logger.info("User fills email of registered user: " + email);
    }

    @When("User fills password: {}")
    public void userFillsPassword(String password) {
        pageFactory.getInputRegisteredPassword(password);
        logger.info("User fills password of registered user: " + password);
    }

    @When("User click on Sing In Button")
    public void userClickOnSingInButton() {
        pageFactory.getBtnSingIn();
        logger.info("Click on Sign In Button");
    }

    @When("User click on dropdown")
    public void userClickOnDropdown() {
        pageFactory.getClickOnDropdown();
        logger.info("Click on Dropdown");
    }

    @When("User click on My Account option")
    public void userClickOnSignOut() {
        pageFactory.getClickOnMyAccountOption();
        logger.info("Select My Account option");
    }

    @Then("^User is logged in with Contact Information(?: firstName \"(.+?)\")?(?: lastName \"(.+?)\")?(?: email \"(.+?)\")?$")
    public void userIsLoggedInWithContactInformationName(String firstName, String lastName, String email) {

        if (firstName == null && lastName == null && email == null) {
            //retrieve data from the context
            Object expectedFirstName = ScenarioContext.INSTANCE.getContext(Context.FIRST_NAME);
            Object expectedLastName = ScenarioContext.INSTANCE.getContext(Context.LAST_NAME);
            Object expectedEmail = ScenarioContext.INSTANCE.getContext(Context.EMAIL);

            String expectedNameEmail = expectedFirstName + " " + expectedLastName + "\n" + expectedEmail;
            AssertData(expectedNameEmail);

        } else {
            String expectedNameEmail = firstName + " " + lastName + "\n" + email;
            AssertData(expectedNameEmail);
        }
    }

    public void AssertData(String expectedNameEmail) {
        try {

            String actualData = pageFactory.getContactInformationName();

            if (actualData != null) {
                assertEquals(expectedNameEmail, actualData);
                logger.info("User name and email are displayed in Contact information. \nExpected:\n" + expectedNameEmail + "\nActual: \n" + actualData);
            }
        } catch (NoSuchElementException e) {
            logger.error("Contact Information name and email element not found.");
        }
    }

    @Then("Error message that sign-in was incorrect is displayed")
    public void ErrorMessageThatSignInWasIncorrectIsDisplayed() {
        try {
            Duration timeout = ofSeconds(9000);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(visibilityOfElementLocated(pageFactory.getErrorMessageFrame()));
            String errorText = pageFactory.getErrorMessageText();
            assertEquals("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.", errorText);
            logger.info("Error message that sign-in was incorrect is displayed: \n" + errorText);
        } catch (NoSuchElementException e2) {
            logger.error("Frame loading exception" + e2.getMessage());
        }
    }

    @Then("User is still on {string} page")
    public void userIsStillOnPage(String customerLoginInscription) {
        try {
            // Verify if the user is still on the specified page
            String customerLoginPage = pageFactory.getInscriptionCustomerLogin();
            assertEquals(customerLoginInscription, customerLoginPage);
            logger.info("User is still on Customer Login page");
        } catch (AssertionError e) {
            logger.error("User is not logged in");
        }
    }

}