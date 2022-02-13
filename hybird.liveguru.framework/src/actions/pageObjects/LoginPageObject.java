package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    private WebDriver driver;
    public LoginPageObject(WebDriver driver)
    {
        this.driver=driver;
    }

    public void enterUsername(String username)
    {
        waitForElementVisible(driver, LoginPageUI.EMAIL);
        sendKeyToElement(driver, LoginPageUI.EMAIL, username);
    }
    public void enterPassword(String password)
    {
        waitForElementVisible(driver, LoginPageUI.PASSWORD);
        sendKeyToElement(driver, LoginPageUI.PASSWORD,password);
    }

    public void clickToLoginBtn() {
        clickToElement( driver, LoginPageUI.LOGIN_BTN);
    }

    public String checkRequiredMsg()
    {
        waitForElementVisible(driver, LoginPageUI.REQUIRED_MSG);
        return getElementText(driver, LoginPageUI.REQUIRED_MSG);
    }
    public void openRegisterPage()
    {
        waitForElementVisible(driver, LoginPageUI.REGISTER_LINK);
        clickToElement(driver, LoginPageUI.REGISTER_LINK);
    }
}
