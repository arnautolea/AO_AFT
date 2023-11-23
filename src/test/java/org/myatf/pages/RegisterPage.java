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
    @FindBy(xpath = "//*[@id='form-validate']/div/div[1]/button/span")
    public WebElement btnCreateAnAccount;
}
