package org.myatf.pages;

import org.openqa.selenium.*;
public class PageFactory {

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
    private final By contactInformationName = By.xpath("//body[1]/div[2]/main[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/p[1]");
    private final By errorMessageFrame = By.xpath("//body/div[2]/main[1]/div[2]/div[2]/div[1]");
    private final By errorMessageText = By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account ')]");
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
    public By getClickOnMyAccountOption() { return clickOnMyAccountOption; }
    public By getContactInformationName() { return contactInformationName; }
    public By getErrorMessageFrame() {return errorMessageFrame;}
    public By getErrorMessageText() {return errorMessageText;}
}