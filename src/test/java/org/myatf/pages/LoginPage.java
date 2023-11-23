package org.myatf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@id='email']")
    public WebElement inputRegisteredEmail;
    @FindBy(css = "input#pass")
    public WebElement inputRegisteredPassword;
    @FindBy(xpath = "//*[@id=\"send2\"]/span")
    public WebElement btnSignIn;
    @FindBy(xpath = "//div[contains(text(),'The account sign-in was incorrect or your account ')]")
    public WebElement errorMessageText;
    @FindBy(xpath = "//span[contains(text(),'Customer Login')]")
    public WebElement inscriptionCustomerLogin;

}
