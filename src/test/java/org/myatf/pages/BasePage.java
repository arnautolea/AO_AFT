package org.myatf.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    private final WebDriverWait wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void click(WebElement element) throws NoSuchElementException {
        if (element != null) {
            try {
                Awaitility.await()
                        .atMost(Duration.ofSeconds(20))
                        .until(() -> {
                            try {
                                element.click();
                                return true;
                            } catch (Exception e) {
                                logger.error("Error during click: " + e.getMessage());
                                return false;
                            }
                        });
            } catch (Throwable e) {
                throw new NoSuchElementException("Error during click", e);
            }
        } else {
            logger.error("Element is null; cannot perform the click.");
        }
    }

    public void enterText(WebElement element, String text) throws NoSuchElementException {
        if (element != null) {
            try {
                Awaitility.await()
                        .atMost(Duration.ofSeconds(20))
                        .until(() -> {
                            try {
                                element.sendKeys(text);
                                return true;
                            } catch (Exception e) {
                                logger.error("Error during text input: " + e.getMessage());
                                return false;
                            }
                        });
            } catch (Throwable e) {
                throw new NoSuchElementException("Error during text input", e);
            }
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
