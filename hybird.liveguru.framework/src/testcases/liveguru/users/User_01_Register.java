package liveguru.users;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class User_01_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }


    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://demo.guru99.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Register_Empty_Data()
    {
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        String errorMessage = driver.findElement(By.xpath("//label[@id='message9']")).getText();
        Assert.assertEquals(errorMessage,"Email ID must not be blank");

        System.out.println(errorMessage);

    }

    @Test
    public void Invalid_Email()
    {
        String email = "abc";
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);

        String errorMessage = driver.findElement(By.xpath("//label[@id='message9']")).getText();
        Assert.assertEquals(errorMessage, "Email ID is not valid");
        System.out.println(errorMessage);

    }


    @Test
    public void Valid_Data()
    {
        String email = "long.ph145@gmail.com";
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        String successMessage = driver.findElement(By.xpath("//h2[(text()='Access details to demo site.')]")).getText();

        Assert.assertEquals(successMessage,"Access details to demo site.");
        System.out.println(successMessage);

    }
}
