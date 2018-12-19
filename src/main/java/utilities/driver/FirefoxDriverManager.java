package utilities.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public class FirefoxDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
