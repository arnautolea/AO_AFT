package org.myatf.definitions;

import com.github.javafaker.Faker;
import org.myatf.enums.Browser;
import org.myatf.config.WebDriverFactory;
import org.myatf.utils.GenerateFakeTestData;
import org.myatf.utils.Helper;
import org.myatf.pages.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;

public class StepDefinitions {

    final static WebDriver driver = WebDriverFactory.getDriver(Browser.CHROME);;
    final RegistrationPage registrationPage = new RegistrationPage();
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);
    Faker fkobj = new Faker();
    GenerateFakeTestData tdobj= new GenerateFakeTestData();

    private static final String CSV_FILE_PATH = "CreatedUser.csv";
        @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }
     @Given("User is on the Home page")
    public void userIsOnHomePage() {
        try {
            Helper.openPage();
            logger.info("User is on Home Page.");
        } catch (SessionNotCreatedException e) {
            // Handle the SessionNotCreatedException and log the error
            logger.error("SessionNotCreatedException occurred: " + e.getMessage());
        }
    }

    @When("User click on Create An Account link")
    public void userClicksOnCreateAnAccountLink() {
        driver.findElement(registrationPage.getClickCreateAnAccount()).click();
        logger.info("Click on Create An Account");
    }

    @And("User fills firstName")
    public void userFillsFirstNameFirstName() {
        String firstName =fkobj.address().firstName();
        driver.findElement(registrationPage.getInputFirstName()).sendKeys(tdobj.getFirstName(firstName));
        logger.info("First Name completed: " + firstName);
    }

    @And("User fills lastName")
    public void userFillsLastName() {
        String lastName =fkobj.address().lastName();
        driver.findElement(registrationPage.getInputLastName()).sendKeys(tdobj.getLastName(lastName));
        logger.info("Last Name completed: " + lastName);
    }

    @And("User fills email")
    public void userFillsEmail() {
        String email =fkobj.internet().emailAddress();
        driver.findElement(registrationPage.getInputEmail()).sendKeys (tdobj.getEmail(email));
        logger.info("Email address: "+ email);
    }

    @And("User fills password and confirmation password")
    public void userFillsPasswordAndConfirmationPassword() {
        String password = fkobj.internet().password();
        driver.findElement(registrationPage.getInputPassword()).sendKeys(tdobj.getPassword(password) + "!1Qw");
        driver.findElement(registrationPage.getInputConfirmPassword()).sendKeys(tdobj.getPassword(password) + "!1Qw");
        logger.info("Password and confirmation password: " + password + "!1Qw");
    }

    @And("User clicks on Create an Account Button")
    public void userClicksOnCreateAnAccount() {
        WebElement element = driver.findElement(registrationPage.getBtnCreateAnAccount());
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        driver.findElement(registrationPage.getBtnCreateAnAccount()).click();
        logger.info("Click on Create an Account Button");
    }
    @Then("User redirected on Account Page, 'My Account' inscription is displayed on the screen")
    public void inscriptionMyAccount() {
        String myAccount = driver.findElement(registrationPage.getInscriptionMyAccount()).getText();
        assertEquals("My Account", myAccount);
        logger.info("\"My Account\" Page displayed");
    }

    @And("User click on Sign In")
    public void userClickOnSignIn() {
        driver.findElement(registrationPage.getClickOnSignIn()).click();
        logger.info("Click on Sign In");
    }

    @And("Customer Login page is displayed")
    public void customerLoginPageIsDisplayed() {
        String customerLogin = driver.findElement(registrationPage.getInscriptionCustomerLogin()).getText();
        assertEquals("Customer Login", customerLogin);
        logger.info("\"Customer Login\" Page displayed");
    }

    @And("User fills email: {} of registered user")
    public void userFillsEmailOfRegisteredUser(String email) {
        driver.findElement(registrationPage.getInputRegisteredEmail()).sendKeys(email);
        logger.info("User fills email of registered user: " + email);
    }

    @And("User fills password: {} of registered user")
    public void userFillsPasswordOfRegisteredUser(String password) {
        driver.findElement(registrationPage.getInputRegisteredPassword()).sendKeys(password);
        logger.info("User fills password of registered user: " + password);
    }

    @When("User click on Sing In Button")
    public void userClickOnSingInButton() {
        driver.findElement(registrationPage.getBtnSingIn()).click();
        logger.info("Click on Sign In Button");
    }

    @Then("User is logged in and redirected on main page")
    public void userIsLoggedInAndRedirectedOnMainPage() {
        String mainPage = driver.getCurrentUrl();
        assertEquals("https://magento.softwaretestingboard.com/" , mainPage);
        logger.info("User is logged in and redirected on main page");
    }

    @When("User click on dropdown")
    public void userClickOnDropdown() {
        driver.findElement(registrationPage.getClickOnDropdown()).click();
        logger.info("Click on Dropdown");
    }

    @And("User click on Sign Out option")
    public void userClickOnSignOut() {
        driver.findElement(registrationPage.getClickOnSignOutOption()).click();
        logger.info("Select Sign Out option");
    }

    @Then("'You are signed out' message displayed")
    public void youAreSignedOutMessageDisplayed() {
        String signOut = driver.findElement(registrationPage.getInscriptionYouAreSignedOut()).getText();
        assertEquals("You are signed out", signOut);
        logger.info("\"You are signed out\" message displayed \n" );
    }

}
