package org.myatf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = ("//input[@id='firstname']"))
    public WebElement inputFirstName;
    @FindBy(xpath = "//input[@id='lastname']")
    public WebElement inputLastName;
    @FindBy(xpath = "//input[@id='email_address']")
    public WebElement inputEmail;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement inputPassword;
    @FindBy(xpath = "//input[@id='password-confirmation']")
    public WebElement inputConfirmPassword;
    @FindBy(linkText = "Create an Account")
    public WebElement clickCreateAnAccount;
    @FindBy(xpath = "//*[@id='form-validate']/div/div[1]/button/span")
    public WebElement btnCreateAnAccount;
    @FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[2]/a[1]")
    public WebElement clickOnSignIn;
    @FindBy(xpath = "//span[contains(text(),'My Account')]")
    public WebElement inscriptionMyAccount;
    @FindBy(xpath = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div/div[1]/p")
    public WebElement contactInformationName;

}
