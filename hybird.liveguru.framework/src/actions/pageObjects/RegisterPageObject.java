package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends AbstractPage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        waitForElementVisible(driver,"//input[@id='firstname']");
        sendKeyToElement(driver,"//input[@id='firstname']",firstName);
    }

    public void enterLastName(String lastName) {
        waitForElementVisible(driver,"//input[@id='lastname']");
        sendKeyToElement(driver,"//input[@id='lastname']",lastName);
    }

    public void enterEmail(String email) {
        waitForElementVisible(driver,"//input[@id='email_address']");
        sendKeyToElement(driver,"//input[@id='email_address']", email);
    }

    public void enterPassword(String password) {
        waitForElementVisible(driver, "//input[@id='password']");
        sendKeyToElement(driver, "//input[@id='password']",password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        waitForElementVisible(driver,"//input[@id='confirmation']");
        sendKeyToElement(driver,"//input[@id='confirmation']",confirmPassword);
    }

    public void clickToRegisterBtn() {
        clickToElement(driver,"//button[@title='Register']");
    }


}
