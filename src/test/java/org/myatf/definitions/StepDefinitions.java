package org.myatf.definitions;

import org.myatf.ConfigurationLoader;
import org.myatf.utils.GenerateFakeTestData;
import org.myatf.utils.Helper;
import org.myatf.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static java.time.Duration.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class StepDefinitions {

    private final WebDriver driver = Helper.getDriver();
    final RegistrationPage registrationPage = new RegistrationPage();
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);
    GenerateFakeTestData fakerData = new GenerateFakeTestData();
    private static final Map<String, Object> config = ConfigurationLoader.loadConfig();
    public static String baseUrl = (String) config.get("baseUrl");
    @Given("User is on the Home page")
    public void userIsOnHomePage() {
        try {
            driver.get(baseUrl);
            logger.info("User is on Home Page.");
        } catch (NullPointerException e) {
            // Handle the SessionNotCreatedException and log the error
            logger.error("NullPointerException occurred: " + e.getMessage());
        }
    }

    @When("User click on Create An Account link")
    public void userClicksOnCreateAnAccountLink() {
        driver.findElement(registrationPage.getClickCreateAnAccount()).click();
        logger.info("Click on Create An Account");
    }

    @When("User fills First Name")
    public void userFillsFirstNameFirstName() {
        fakerData.generateRandomFirstName();
        String firstName = fakerData.getFirstName();
        driver.findElement(registrationPage.getInputFirstName()).sendKeys(firstName);
        logger.info("First Name completed: " + firstName);
    }

    @When("User fills Last Name")
    public void userFillsLastName() {
        fakerData.generateRandomLastName();
        String lastName = fakerData.getLastName();
        driver.findElement(registrationPage.getInputLastName()).sendKeys(lastName);
        logger.info("Last Name completed: " + lastName);

    }

    @When("User fills Email")
    public void userFillsEmail() {
        fakerData.generateRandomEmail();
        String email = fakerData.getEmail();
        driver.findElement(registrationPage.getInputEmail()).sendKeys(email);
        logger.info("Email address: " + email);
    }

    @When("User fills password and confirmation password")
    public void userFillsPasswordAndConfirmationPassword() {
        fakerData.generateRandomPassword();
        String password = fakerData.getPassword();
        driver.findElement(registrationPage.getInputPassword()).sendKeys(password);
        driver.findElement(registrationPage.getInputConfirmPassword()).sendKeys(password);
        logger.info("Password and confirmation password: " + password);
    }

    @When("User clicks on Create an Account Button")
    public void userClicksOnCreateAnAccount() {
        driver.findElement(registrationPage.getBtnCreateAnAccount()).click();
        logger.info("Click on Create an Account Button");
    }

    @Then("User redirected on Account Page, 'My Account' inscription is displayed on the screen")
    public void inscriptionMyAccount() {
        String myAccount = driver.findElement(registrationPage.getInscriptionMyAccount()).getText();
        assertEquals("My Account", myAccount);
        logger.info("\"My Account\" Page displayed\n");
    }

    @When("User click on Sign In")
    public void userClickOnSignIn() {
        try {
            driver.findElement(registrationPage.getClickOnSignIn()).click();
            logger.info("Click on Sign In");
        } catch (NoSuchElementException e) {
            logger.error("WebDriver couldn’t locate the element Sign In" + e.getMessage());
        }
    }

    @Then("Customer Login page is displayed")
    public void customerLoginPageIsDisplayed() {
        try {
            String customerLogin = driver.findElement(registrationPage.getInscriptionCustomerLogin()).getText();
            assertEquals("Customer Login", customerLogin);
            logger.info("\"Customer Login\" Page displayed");
        } catch (AssertionError e) {
            logger.error("No Customer Login message" + e.getMessage());
        }
    }

    @When("User fills email: {}")
    public void userFillsEmail(String email) {
        driver.findElement(registrationPage.getInputRegisteredEmail()).sendKeys(email);
        logger.info("User fills email of registered user: " + email);
    }

    @When("User fills password: {}")
    public void userFillsPassword(String password) {
        driver.findElement(registrationPage.getInputRegisteredPassword()).sendKeys(password);
        logger.info("User fills password of registered user: " + password);
    }

    @When("User click on Sing In Button")
    public void userClickOnSingInButton() {
        driver.findElement(registrationPage.getBtnSingIn()).click();
        logger.info("Click on Sign In Button");
    }

    @When("User click on dropdown")
    public void userClickOnDropdown() {
        driver.findElement(registrationPage.getClickOnDropdown()).click();
        logger.info("Click on Dropdown");
    }

    @When("User click on My Account option")
    public void userClickOnSignOut() {
        driver.findElement(registrationPage.getClickOnMyAccountOption()).click();
        logger.info("Select My Account option");
    }
    @Then("User is logged in with Contact Information name and email")
    public void userIsLoggedInWithContactInformationName(String expectedName) {
        String actualName = driver.findElement(registrationPage.getContactInformationName()).getText();
        assertEquals(expectedName, actualName);
        logger.info("User name and email are displayed in Contact information");
    }
    @Then("Error message that sign-in was incorrect is displayed")
    public void ErrorMessageThatSignInWasIncorrectIsDisplayed() {
        try {
            Duration timeout = ofSeconds(9000);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(visibilityOfElementLocated(registrationPage.getErrorMessageFrame()));
            String errorText = driver.findElement(registrationPage.getErrorMessageText()).getText();
            assertEquals("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.", errorText);
            logger.info("Error message that sign-in was incorrect is displayed: \n" + errorText);
        }catch(NoSuchElementException e2) {
            logger.error("Frame loading exception" + e2.getMessage());

        }
    }

    @Then("User is still on {string} page")
    public void userIsStillOnPage(String customerLoginInscription) {
        try {
            String customerLoginPage = driver.findElement(registrationPage.getInscriptionCustomerLogin()).getText();
            assertEquals(customerLoginInscription, customerLoginPage);
            logger.info("User is still on Customer Login page");
        } catch (AssertionError e) {
            logger.error("User is not logged in");
        }
    }


}