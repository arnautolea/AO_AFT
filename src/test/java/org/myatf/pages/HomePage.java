package org.myatf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(linkText = "Create an Account")
    public WebElement clickCreateAnAccount;

    @FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[2]/a[1]")
    public WebElement clickOnSignIn;

    @FindBy(css = "li.customer-welcome > span > button")
    public WebElement clickOnDropdown;

    @FindBy(xpath = "//header/div[1]/div/ul/li[2]/div/ul/li[1]/a")
    public WebElement clickOnMyAccountOption;
}
