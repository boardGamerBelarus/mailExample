package seleniumCore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

public class TestBase {
    protected static WebDriver driver;
    public static String BaseURL = "https://mail.google.com";

    @BeforeGroups("seleniumTests")
    public static void setup() {
        driver = DriverContainer.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BaseURL);
    }
    
    @AfterGroups("seleniumTests")
    public static void tearDown(){
    	driver.quit();
    	
    	
    }
}
