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

    @FindBy(css = "li.customer-welcome > span > button")
    public WebElement clickOnDropdown;

    @FindBy(xpath = "//header/div[1]/div/ul/li[2]/div/ul/li[1]/a")
    public WebElement clickOnMyAccountOption;



    @FindBy(xpath = "//div[contains(text(),'The account sign-in was incorrect or your account ')]")
    public WebElement errorMessageText;

    @FindBy(xpath = "//span[contains(text(),'Customer Login')]")
    public WebElement inscriptionCustomerLogin;
    @FindBy(xpath = "//span[contains(text(),'Customer Login')]")
    public WebElement errorMessageFrame;


//    private final By errorMessageFrame = By.xpath("//body/div[2]/main[1]/div[2]/div[2]/div[1]/div[1]");
//
//    public By getErrorMessageFrame() {
//        return errorMessageFrame;
//    }
}
