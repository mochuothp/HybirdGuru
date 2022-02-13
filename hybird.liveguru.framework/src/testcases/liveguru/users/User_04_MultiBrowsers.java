package liveguru.users;

import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyDashboardPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;

public class User_04_MultiBrowsers extends AbstractTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    MyDashboardPageObject myDashboardPage;

    @Parameters(value = "browser")
    @BeforeTest
    public void beforeTest(String browserName)
    {
        driver = getBrowserName(browserName);
    }

    @AfterTest
    public void afterTest()
    {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod()
    {
        homePage = new HomePageObject(driver);
        homePage.openPageURL(driver,"http://live.techpanda.org/index.php" );
        homePage.setImplicitWait(driver, 30);
    }

    @Test
    public void TC_01_Login_With_Empty_Data()
    {
        homePage.clickToMyAccountLink();
        loginPage = new LoginPageObject(driver);
        loginPage.clickToLoginBtn();

        Assert.assertEquals(loginPage.checkRequiredMsg(),"* Required Fields");
    }


    @Test
    public void TC_02_Login_To_Account()
    {
        homePage.clickToMyAccountLink();
        loginPage = new LoginPageObject(driver);

        loginPage.enterUsername("long.ph145@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickToLoginBtn();

        myDashboardPage = new MyDashboardPageObject(driver);

        Assert.assertEquals(myDashboardPage.checkLoginSucceedMsg(),"Hello, Long Pham!");
    }


}
