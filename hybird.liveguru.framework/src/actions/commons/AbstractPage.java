package commons;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public abstract class AbstractPage {
    //WebDriver driver;
    private Alert alert;
    private WebDriverWait explicitWait;
    private long longWait = 30;
    private long shortWait = 10;

    //Web browser commands
    public void openPageURL(WebDriver driver, String pageURL)
    {
        driver.get(pageURL);
    }

    public String getPageTitle(WebDriver driver)
    {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver)
    {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver)
    {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver)
    {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver)
    {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver)
    {
        driver.navigate().refresh();
    }

    public void waitAlertPresence(WebDriver driver)
    {
        explicitWait = new WebDriverWait(driver,longWait);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver)
    {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert(WebDriver driver)
    {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText (WebDriver driver)
    {
        waitAlertPresence(driver);
        alert =driver.switchTo().alert();
        return alert.getText();
    }

    public void sendKeyToAlert (WebDriver driver, String text)
    {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    // WebElement commands
    public By byXpath (String locator)
    {
        return byXpath(locator);
    }

    public WebElement findElement(WebDriver driver, String locator)
    {
       return driver.findElement(byXpath(locator));
    }

    public List<WebElement> findElements(WebDriver driver, String locator)
    {
        return driver.findElements(byXpath(locator));
    }


}
