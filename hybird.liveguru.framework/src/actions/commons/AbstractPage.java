package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {
    //WebDriver driver;
    private Alert alert;
    private Actions action;
    private WebDriverWait explicitWait;
    private WebElement element;
    private List<WebElement> elements;
    private Select select;
    private JavascriptExecutor jsExecutor;

    private long longWait = 30;
    private long shortWait = 10;

   public void setImplicitWait(WebDriver driver, long timeOut)
   {
       driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
   }


    //Web browser commands
    public void openPageURL(WebDriver driver, String pageURL) {
        driver.get(pageURL);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void waitAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longWait);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void sendKeyToAlert(WebDriver driver, String text) {
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
    public By byXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement findElement(WebDriver driver, String locator) {
        return driver.findElement(byXpath(locator));
    }

    public List<WebElement> findElements(WebDriver driver, String locator) {
        return driver.findElements(byXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        findElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value) {
        element = findElement(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String value) {
        select = new Select(findElement(driver, locator));
        select.selectByValue(value);
    }

    public void getSelectedItemInDropDown(WebDriver driver, String locator, String value) {
        select = new Select(findElement(driver, locator));
        select.getFirstSelectedOption();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(findElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        findElement(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait = new WebDriverWait(driver, longWait);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

        elements = findElements(driver, childItemLocator);

        for (WebElement item : elements) {
            if (item.getText().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return findElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return findElement(driver, locator).getText();
    }

    public int countElementNumber(WebDriver driver, String locator) {
        return findElements(driver, locator).size();
    }

    public void checkTheCheckbox(WebDriver driver, String locator) {
        element = findElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator) {
        element = findElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(findElement(driver, locator)).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(findElement(driver, locator), key).perform();
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longWait);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longWait);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator)
    {
        explicitWait = new WebDriverWait(driver, longWait);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }






    //jsExecutor
    public boolean isImageLoaded (WebDriver driver, String locator)
    {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].natualWidth>0",findElement(driver, locator));
        if (status)
        {
            return true;
        }
        return false;
    }
    public void scrollToElement(WebDriver driver, String locator) {

        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElement(driver,locator));
    }





}
