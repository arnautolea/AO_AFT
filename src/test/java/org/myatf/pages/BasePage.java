package org.myatf.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myatf.utils.WebDriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(WebElement element) throws NoSuchElementException {
        if (element != null) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element)).click();
            } catch (NoSuchElementException e) {
                logger.error("Element not found: " + e.getMessage());
                throw new NoSuchElementException("Element not found", e);
            }
        } else {
            logger.error("Element is null; cannot perform the click.");
        }
    }

    public void enterText(WebElement element, String text) throws NoSuchElementException {
        if (element != null) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
            } catch (Exception e) {
                logger.error("Error while entering text: " + e.getMessage());
                throw new NoSuchElementException("Error while entering text", e);
            }
        } else {
            logger.error("Element is null; cannot enter text.");
        }
    }

    public String returnText(WebElement element) throws NoSuchElementException {
        if (element != null) {
            try {
                return wait.until(ExpectedConditions.visibilityOf(element)).getText();
            } catch (Exception e) {
                logger.error("Error while getting text: " + e.getMessage());
                throw new NoSuchElementException("Error while getting text", e);
            }
        } else {
            logger.error("Element is null; cannot get text.");
            return "";
        }
    }
}