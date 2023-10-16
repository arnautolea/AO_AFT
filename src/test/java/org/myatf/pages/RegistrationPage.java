package org.myatf.pages;

import org.openqa.selenium.*;
public class RegistrationPage {

    private By clickCreateAnAccount = By.linkText("Create an Account");
    private By inputFirstName = By.xpath("//input[@id='firstname']");
    private By inputLastName = By.xpath("//input[@id='lastname']");
    private By inputEmail = By.xpath("//input[@id='email_address']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By inputConfirmPassword = By.xpath("//input[@id='password-confirmation']");
    private By btnCreateAnAccount = By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button/span");
    private By inscriptionMyAccount = By.xpath("//span[contains(text(),'My Account')]");
    private By clickOnSignIn = By.xpath("//header/div[1]/div[1]/ul[1]/li[2]/a[1]");
    private By inscriptionCustomerLogin = By.xpath("//span[contains(text(),'Customer Login')]");
    private By inputRegisteredEmail = By.xpath("//input[@id='email']");
    private By inputRegisteredPassword = By.cssSelector("input#pass");
    private By btnSingIn = By.xpath("//*[@id=\"send2\"]/span");
    private By clickOnDropdown = By.cssSelector("li.customer-welcome > span > button");
    private By clickOnSignOutOption = By.xpath("//header/div[1]/div[1]/ul[1]/li[2]/div[1]/ul[1]/li[3]/a[1]");
    private By inscriptionYouAreSignedOut = By.xpath("//span[contains(text(),'You are signed out')]");
    private By errorMessageFrame = By.xpath("//body/div[2]/main[1]/div[2]/div[2]/div[1]");
    private By errorMessageText = By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account ')]");
    public By getClickCreateAnAccount() { return clickCreateAnAccount; }
    public By getInputFirstName() {
        return inputFirstName;
    }
    public By getInputLastName() {
        return inputLastName;
    }
    public By getInputEmail() {
        return inputEmail;
    }
    public By getInputPassword() {
        return inputPassword;
    }
    public By getInputConfirmPassword() {
        return inputConfirmPassword;
    }
    public By getInscriptionMyAccount() { return inscriptionMyAccount; }
    public By getBtnCreateAnAccount() { return btnCreateAnAccount;}
    public By getClickOnSignIn() {return clickOnSignIn;}
    public By getInscriptionCustomerLogin() { return inscriptionCustomerLogin; }
    public By getInputRegisteredEmail() {
        return inputRegisteredEmail;
    }
    public By getInputRegisteredPassword() {
        return inputRegisteredPassword;
    }
    public By getBtnSingIn() {return btnSingIn;}
    public By getClickOnDropdown() {return clickOnDropdown;}
    public By getClickOnSignOutOption() { return clickOnSignOutOption; }
    public By getInscriptionYouAreSignedOut() { return inscriptionYouAreSignedOut; }
    public By getErrorMessageFrame() {return errorMessageFrame;}
    public By getErrorMessageText() {return errorMessageText;}
}