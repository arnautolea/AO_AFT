package org.myatf.definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import org.myatf.pages.AccountPage;
import org.myatf.pages.HomePage;
import org.myatf.pages.LoginPage;
import org.myatf.utils.WebDriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class StepDefLoginUI {

    private final LoginPage loginPage;
    private final HomePage homePage;

    public StepDefLoginUI() {
        WebDriver driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    private static final Logger logger = LogManager.getLogger(StepDefLoginUI.class);

    @Then("Customer Login page is displayed")
    public void customerLoginPageIsDisplayed() {
        String customerLogin = loginPage.returnText(loginPage.inscriptionCustomerLogin);
        if (!"Customer Login".equals(customerLogin)) {
            logger.info("No Customer Login message");
            throw new AssertionError("No Customer Login message");
        } else {
            logger.info("\"Customer Login\" Page displayed");
        }
    }

    @When("User fills email: {word}")
    public void userFillsEmail(String email) {
        loginPage.enterText(loginPage.inputRegisteredEmail, email);

        logger.info("User fills email of registered user: " + email);
    }

    @When("User fills password: {word}")
    public void userFillsPassword(String password) {
        loginPage.enterText(loginPage.inputRegisteredPassword, password);
        logger.info("User fills password of registered user: " + password);
    }

    @When("User click on Sing In Button")
    public void userClickOnSingInButton() {
        loginPage.click(loginPage.btnSignIn);
        logger.info("Click on Sign In Button");
    }

    @When("User click on dropdown")
    public void userClickOnDropdown() {
        loginPage.click(homePage.clickOnDropdown);
        logger.info("Click on Dropdown");
    }

    @When("User click on My Account option")
    public void userClickOnMyAccount() {
        loginPage.click(homePage.clickOnMyAccountOption);
        logger.info("Select My Account option");
    }

    @Then("Error message that sign-in was incorrect is displayed")
    public void ErrorMessageThatSignInWasIncorrectIsDisplayed() throws NoSuchElementException, AssertionError {
        // Use Awaitility to wait for the error message element to be visible
        try {
            Awaitility.await()
                    .atMost(10, SECONDS)  // Set a maximum waiting time
                    .until(() -> {
                        // Check if the error message element is displayed
                        WebElement errorMessageElement = loginPage.errorMessageFrame;
                        return errorMessageElement.isDisplayed();
                    });
            // Once the condition is met (error message is displayed)
            String errorText = loginPage.returnText(loginPage.errorMessageText);
            assertEquals("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.", errorText);
            logger.info("Error message that sign-in was incorrect is displayed: \n" + errorText);
        } catch (AssertionError e) {
            // Log the error message if the assertion fails
            logger.error("Assertion failed: " + e.getMessage());
            throw e; // Rethrow the exception to fail the step
        }
    }

    @Then("User is still on {string} page")
    public void userIsStillOnLoginPage(String customerLoginInscription) throws AssertionError {
        // Verify if the user is still on the specified page
        String customerLoginPage = loginPage.returnText(loginPage.inscriptionCustomerLogin);
        assertEquals(customerLoginInscription, customerLoginPage);
        logger.info("User is still on Customer Login page");
    }
}


