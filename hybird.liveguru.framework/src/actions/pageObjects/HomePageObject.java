package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver)
    {
        this.driver = driver;
    }


    public void clickToMyAccountLink() {
        clickToElement(driver, HomePageUI.MYACCOUNT_BOTTOM);
    }
}
