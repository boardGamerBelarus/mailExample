package seleniumCore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class DriverContainer {

    public enum DriverType {
        Firefox,
        Chrome,
        Safary
    }

    static DriverType driverType = DriverType.Chrome;
    static WebDriver driver = null;

    public void setDriverType(DriverType type) {
        driverType = type;
    }

    public static WebDriver getDriver() {
        if (driver == null) {

            switch (driverType) {
                case Firefox:
        /*System.setProperty("webdriver.gecko.driver", "D:\\programs\\AutomatedTesting\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);*/
                    return driver = new FirefoxDriver();
                case Chrome:
                    System.setProperty("webdriver.chrome.driver", "C:\\JavaPrograms\\workspaceSEeclipse\\chromedriver.exe");
                    return driver = new ChromeDriver();
                case Safary:
                    return driver = new SafariDriver();
            }
        }
        return driver;
    }

}


