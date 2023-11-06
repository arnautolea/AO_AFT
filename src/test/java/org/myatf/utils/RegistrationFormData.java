package org.myatf.utils;

import org.myatf.enums.Keys;
import org.myatf.pages.RegisterPage;
import org.openqa.selenium.WebDriver;

public class RegistrationFormData {

    private final WebDriver driver = WebDriverFactory.getDriver();
    private final RegisterPage registerPage = new RegisterPage(driver);
    GenerateFakeTestData fakerData = new GenerateFakeTestData();

    public void RegistrationFormDataWithFaker() {
        // Generate random data
        fakerData.generateRandomFirstName();
        fakerData.generateRandomLastName();
        fakerData.generateRandomEmail();
        fakerData.generateRandomPassword();

        // Get and set the firstName in the ScenarioContext
        String firstName = fakerData.getFirstName();
        registerPage.enterText(registerPage.inputFirstName, firstName);
        ScenarioContext.getInstance().saveValueToContext(Keys.FIRST_NAME, firstName);

        // Get and set the lastName in the ScenarioContext
        String lastName = fakerData.getLastName();
        registerPage.enterText(registerPage.inputLastName,lastName);
        ScenarioContext.getInstance().saveValueToContext(Keys.LAST_NAME, lastName);

        // Get and set the Email in the ScenarioContext
        String email = fakerData.getEmail();
        registerPage.enterText(registerPage.inputEmail, email);
        ScenarioContext.getInstance().saveValueToContext(Keys.EMAIL, email);

        // Get the Password
        String password = fakerData.getPassword();
        registerPage.enterText(registerPage.inputPassword, password);
        registerPage.enterText(registerPage.inputConfirmPassword, password);
    }
}