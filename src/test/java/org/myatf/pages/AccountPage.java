package org.myatf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage{

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//span[contains(text(),'My Account')]")
    public WebElement inscriptionMyAccount;
    @FindBy(xpath = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div/div[1]/p")
    public WebElement contactInformationName;


}
