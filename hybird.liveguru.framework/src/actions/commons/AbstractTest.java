package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractTest {
    WebDriver driver;

    protected WebDriver getBrowserName (String browserName)
    {
        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("headless"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver= new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;

    }
}
