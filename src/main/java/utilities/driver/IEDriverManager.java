package utilities.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public class IEDriverManager extends  DriverManager {
    @Override
    public WebDriver createDriver(){
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }
}
