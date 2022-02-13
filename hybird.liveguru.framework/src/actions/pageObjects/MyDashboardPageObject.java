package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.MyDashboardPageUI;

public class MyDashboardPageObject extends AbstractPage {
    private WebDriver driver;

    public MyDashboardPageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public String checkWelComeMsg() {
        waitForElementVisible(driver, MyDashboardPageUI.WELCOME_MSG);
        return getElementText(driver, MyDashboardPageUI.WELCOME_MSG);
    }

    public String checkLoginSucceedMsg()
    {
        waitForElementVisible(driver, MyDashboardPageUI.HELLO_MSG);
        return getElementText(driver, MyDashboardPageUI.HELLO_MSG);
    }
}
