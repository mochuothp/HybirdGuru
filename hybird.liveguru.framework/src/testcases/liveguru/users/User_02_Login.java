package liveguru.users;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class User_02_Login {
    WebDriver driver;

    @BeforeTest
    public void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTest()
    {
        driver.quit();
    }

    @Test
    public void Login_Empty_Data()
    {}

    @Test
    public void Login_Invalid_Email()
    {}

    @Test
    public void Login_Invalid_Password()
    {}

    @Test
    public void Login_Valid_Data()
    {}
}
