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
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyDashboardPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;

public class User_03_PageObject {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MyDashboardPageObject myDashboardPage;

    @BeforeTest
    public void beforeTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
    public void TC_02_Register_New_Account()
    {
        homePage.clickToMyAccountLink();
        loginPage = new LoginPageObject(driver);
        loginPage.openRegisterPage();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterFirstName("Long");
        registerPage.enterLastName("Test");
        registerPage.enterEmail("long.ph145"+randomNum()+"@mailinator.com");
        registerPage.enterPassword("123456");
        registerPage.enterConfirmPassword("123456");
        registerPage.clickToRegisterBtn();

        myDashboardPage = new MyDashboardPageObject(driver);

        Assert.assertEquals(myDashboardPage.checkWelComeMsg(),"Thank you for registering with Main Website Store.");

    }

    @Test
    public void TC_03_Login_To_Account()
    {
        homePage.clickToMyAccountLink();
        loginPage = new LoginPageObject(driver);

        loginPage.enterUsername("long.ph145@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickToLoginBtn();

        myDashboardPage = new MyDashboardPageObject(driver);

        Assert.assertEquals(myDashboardPage.checkLoginSucceedMsg(),"Hello, Long Pham!");
    }


    public int randomNum()
    {
        Random random = new Random();
        return random.nextInt(10000);
    }



}
