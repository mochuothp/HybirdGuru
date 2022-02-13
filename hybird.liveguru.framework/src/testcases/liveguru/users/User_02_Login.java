package liveguru.users;

import commons.AbstractPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class User_02_Login extends AbstractPage {
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        setImplicitWait(driver, 20);
    }

    @AfterTest
    public void afterTest() {
        driver.close();
    }

    @BeforeMethod
    public void beforeMethod() {
        openPageURL(driver, "http://live.techpanda.org/index.php");
        clickToElement(driver, "//div[@class='footer']//a[@title='My Account']");

    }





    @Test ()
    public void TC_01_Login_With_Invalid_Account()
    {
        //clickToElement(driver,"//div[@class='footer']//a[@title='My Account']");
        clickToElement(driver, "//button[@title='Login']");

        Assert.assertEquals(findElement(driver,"//p[(text()='* Required Fields')]").getText(),"* Required Fields");

    }

    @Test
    public void TC_02_Login_With_Invalid_Email()
    {
        //openPageURL( driver,"http://live.techpanda.org/index.php/customer/account/login/");
        sendKeyToElement(driver,"//input[@id='email']","long.ph145@gmail");
        sendKeyToElement(driver,"//input[@id='pass']","123456");
        clickToElement(driver, "//button[@title='Login']");

        Assert.assertEquals(findElement(driver,"//div[@id='advice-validate-email-email']").getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Login_Successful()
    {
        //openPageURL( driver,"http://live.techpanda.org/index.php/customer/account/login/");
        sendKeyToElement(driver,"//input[@id='email']","long.ph145@gmail.com");
        sendKeyToElement(driver,"//input[@id='pass']","123456");
        clickToElement(driver, "//button[@title='Login']");

        Assert.assertEquals(findElement(driver,"//strong[(text()='Hello, Long Pham!')]").getText(),"Hello, Long Pham!");
    }
}
