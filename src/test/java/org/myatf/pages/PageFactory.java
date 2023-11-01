package org.myatf.pages;

import org.openqa.selenium.*;

public class PageFactory {
    private final WebDriver driver;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    private final By clickCreateAnAccount = By.linkText("Create an Account");
    private final By inputFirstName = By.xpath("//input[@id='firstname']");
    private final By inputLastName = By.xpath("//input[@id='lastname']");
    private final By inputEmail = By.xpath("//input[@id='email_address']");
    private final By inputPassword = By.xpath("//input[@id='password']");
    private final By inputConfirmPassword = By.xpath("//input[@id='password-confirmation']");
    private final By btnCreateAnAccount = By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button/span");
    private final By inscriptionMyAccount = By.xpath("//span[contains(text(),'My Account')]");
    private final By clickOnSignIn = By.xpath("//header/div[1]/div[1]/ul[1]/li[2]/a[1]");
    private final By inscriptionCustomerLogin = By.xpath("//span[contains(text(),'Customer Login')]");
    private final By inputRegisteredEmail = By.xpath("//input[@id='email']");
    private final By inputRegisteredPassword = By.cssSelector("input#pass");
    private final By btnSingIn = By.xpath("//*[@id=\"send2\"]/span");
    private final By clickOnDropdown = By.cssSelector("li.customer-welcome > span > button");
    private final By clickOnMyAccountOption = By.xpath("//header/div[1]/div/ul/li[2]/div/ul/li[1]/a");
    private final By contactInformationName = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/div[2]/div/div[1]/p");
    private final By errorMessageFrame = By.xpath("//body/div[2]/main[1]/div[2]/div[2]/div[1]/div[1]");
    private final By errorMessageText = By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account ')]");

    public void getClickCreateAnAccount() {
        driver.findElement(clickCreateAnAccount).click();
    }

    public void getInputFirstName(String firstName) {
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    public void getInputLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    public void getInputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void getInputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void getInputConfirmPassword(String password) {
        driver.findElement(inputConfirmPassword).sendKeys(password);
    }

    public String getInscriptionMyAccount() {
        return driver.findElement(inscriptionMyAccount).getText();
    }

    public void getBtnCreateAnAccount() {
        driver.findElement(btnCreateAnAccount).click();
    }

    public void getClickOnSignIn() {
        driver.findElement(clickOnSignIn).click();
    }

    public String getInscriptionCustomerLogin() {
        return driver.findElement(inscriptionCustomerLogin).getText();
    }

    public void getInputRegisteredEmail(String email) {
        driver.findElement(inputRegisteredEmail).sendKeys(email);
    }

    public void getInputRegisteredPassword(String password) {
        driver.findElement(inputRegisteredPassword).sendKeys(password);
    }

    public void getBtnSingIn() {
        driver.findElement(btnSingIn).click();
    }

    public void getClickOnDropdown() {
        driver.findElement(clickOnDropdown).click();
    }

    public void getClickOnMyAccountOption() {
        driver.findElement(clickOnMyAccountOption).click();
    }

    public String getContactInformationName() {
        return driver.findElement(contactInformationName).getText();
    }

    public By getErrorMessageFrame() {
        return errorMessageFrame;
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessageText).getText();
    }
}