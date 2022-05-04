package browserDriverManager;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.openqa.selenium.WebDriver;

public class LocalFactory {
    private WebDriver driver;
    private String browserName;

    public LocalFactory (String browserName)
    {
        this.browserName = browserName;
    }

    public WebDriver createDriver()
    {
        BrowserType browser = BrowserType.valueOf(browserName.toLowerCase());
        switch (browser){
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case HEADLESS:
                driver = new HeadlessDriverManager().getBrowserDriver();
                break;
            default:
                throw new IllegalArgumentException("invalid browser name");
        }
        return driver;
    }


}
