package liveguru.users;

import commons.AbstractPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class User_01_Register extends AbstractPage {
    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }


    @BeforeMethod
    public void beforeMethod() {
        openPageURL(driver,"http://live.techpanda.org/index.php");

        //findElement(driver,"//div[@class='footer']//a[@title='My Account']");
        clickToElement(driver, "//div[@class='footer']//a[@title='My Account']");
    }

    @Test
    public void TC_01_Register_Empty_Data()
    {
        clickToElement(driver, "//button[@title='Register']");

        String errorMessage = findElement(driver, "//div[@id='advice-required-entry-email_address']").getText();
        Assert.assertEquals(errorMessage,"This is a required field.");
    }


    @Test
    public void TC_02_Create_New_Account()
    {
        clickToElement(driver,"//a[@title='Create an Account']");

        String email = "long.ph145"+randomNum()+"@mailinator.com";

        sendKeyToElement(driver,"//input[@id='firstname']","Long");
        sendKeyToElement(driver,"//input[@id='lastname']","La");
        sendKeyToElement(driver,"//input[@id='email_address']",email);
        sendKeyToElement(driver, "//input[@id='password']","123456");
        sendKeyToElement(driver, "//input[@id='confirmation']","123456");

        clickToElement(driver, "//button[@title='Register']");

        String successMessage = findElement(driver,"//span[(text()='Thank you for registering with Main Website Store.')]").getText();

        Assert.assertEquals(successMessage,"Thank you for registering with Main Website Store.");

    }

    public int randomNum()
    {
        Random random = new Random();
        return random.nextInt(10000);
    }
}
